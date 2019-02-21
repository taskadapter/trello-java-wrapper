package com.julienvey.trello

import java.io.File
import java.time.LocalDateTime
import java.util.UUID
import java.util.UUID.randomUUID

import com.julienvey.trello.domain.{Card, Label, Member}
import com.julienvey.trello.utils.ArgUtils.arg
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class CardIt extends FunSpec with Matchers {
  val trello = TrelloTestFactory.trello

  val validListIdFromSomeoneElsesAccount = "518baad5b05dbf4703004853"
  val unknownListId = "someUnknownId"

  describe("create API") {
    it("gets user-friendly error when list id unknown") {
      val thrown = intercept[ListNotFoundException] {
        trello.createCard(unknownListId, new Card())
      }
      thrown.getMessage should include("Trello list with ID 'someUnknownId' is not found")
    }

    it("gets proper exception and a user-friendly error with valid but inaccessible List Id") {
      val thrown = intercept[NotAuthorizedException] {
        val card = new Card()
        card.setName("card created in integration test without board id")
        card.setIdBoard("some unknown board id")
        trello.createCard(validListIdFromSomeoneElsesAccount, card)
      }
      thrown.getMessage should include("unauthorized card permission requested")
    }

    it("creates a card without board id") {
      val card = new Card()
      card.setName("card created without board id")
      card.setIdBoard("some unknown board id")
      val created = trello.createCard(TrelloConfig.toDoListId, card)
      created.getName shouldBe "card created without board id"
    }

    it("assigns label to card") {
      val card = new Card()
      card.setName("card that will have a label")
      val created = trello.createCard(TrelloConfig.toDoListId, card)
      val label = randomUUID().toString
      trello.addLabelsToCard(created.getId, Array(label))
      val loaded = trello.getCard(created.getId)
      val labels = loaded.getLabels.toArray(new Array[Label](0)).map(l => l.getName)
      labels should contain(label)
    }
  }

  describe("update API") {
    it("gets user-friendly exception when updating a deleted card") {
      val thrown = intercept[NotFoundException] {
        val card = new Card()
        card.setId("5b2345c87e570f053e606ebf") // deleted card ID
        card.setName("deleted card that should not be updated")
        card.setIdList(TrelloConfig.toDoListId)
        trello.updateCard(card)
      }
      thrown.getMessage should include("not found")
    }

    it("add, get, delete member full cycle") {
      val cardId = "5c4d89b5bd5a2640f5fcb32c"
      val idExtractor: Member => String = member => member.getId

      trello.addMemberToCard(cardId, TrelloConfig.memberId).asScala
        .map(idExtractor) should contain(TrelloConfig.memberId)

      trello.getCardMembers(cardId).asScala
        .map(idExtractor) should contain(TrelloConfig.memberId)

      trello.getMemberCards(TrelloConfig.memberId).asScala
        .map(card => card.getId) should contain(cardId)

      trello.removeMemberFromCard(cardId, TrelloConfig.memberId).asScala
        .map(idExtractor) shouldNot contain(TrelloConfig.memberId)

      trello.getCardMembers(cardId).asScala
        .map(idExtractor) shouldNot contain(TrelloConfig.memberId)

      trello.getMemberCards(TrelloConfig.memberId).asScala
        .map(card => card.getId) shouldNot contain(cardId)
    }
  }

  describe("Attachment API") {
    it("add attachment to card") {
      val cardId = "5c4d89b5bd5a2640f5fcb32c"
      val attachmentFile = new File(Thread.currentThread().getContextClassLoader.getResource("1.jpg").toURI)

      val beforeAdd = trello.getCardAttachments(cardId).asScala.map(attachment => attachment.getId)

      trello.addAttachmentToCard(cardId, attachmentFile)
      val attachmentIdsAfterUpdate = trello.getCardAttachments(cardId).asScala.map(attachment => attachment.getId)

      attachmentIdsAfterUpdate should have length (beforeAdd.size + 1)

      val attachmentId = attachmentIdsAfterUpdate.filterNot(beforeAdd.contains(_)).toList.head

      trello.deleteAttachment(cardId, attachmentId)
      val afterDelete = trello.getCardAttachments(cardId)

      afterDelete should have length beforeAdd.size
    }

    it("delete attachment with wrong id") {
      val cardId = "5c4d89b5bd5a2640f5fcb32c"
      val thrown = the [NotFoundException] thrownBy trello.deleteAttachment(cardId, "5c56febb4e84e50254c2c54d")
    }
  }

  describe("Comment API") {
    it("Add comment to card") {
      val card = new Card()
      card.setName("Card with comment")

      val cardId = trello.createCard(TrelloConfig.doingListId, card).getId
      val comment = LocalDateTime.now().toString

      trello.addCommentToCard(cardId, comment)
      val actions = trello.getCardActions(cardId, arg("limit", "1"), arg("filter", "commentCard")).asScala

      actions should have length 1
      actions.head.getData.getText should be(comment)
    }

    it("Update existing comment") {
      val card = new Card()
      card.setName("Card with existing comment")
      val cardId = trello.createCard(TrelloConfig.doingListId, card).getId

      trello.addCommentToCard(cardId, "comment")
      val actionId = trello.getCardActions(cardId).asScala.head.getId
      val newComment = UUID.randomUUID().toString

      val comment = trello.updateComment(cardId, actionId, newComment)

      comment.getData.getText should be(newComment)
    }

    it("Update existing comment created by other user") {
      val cardId = "5c4d89b5bd5a2640f5fcb32c"
      val actionId = "5c5ecdc6f642c77012bb41c1"

      the [NotAuthorizedException] thrownBy trello.updateComment(cardId, actionId, "does't matter")
    }
  }

  describe("Delete API") {
    it("Delete successfully") {
      var card = new Card
      card.setName("Card to delete")

      card = trello.createCard(TrelloConfig.doingListId, card)

      trello.deleteCard(card.getId)
      the [NotFoundException] thrownBy trello.getCard(card.getId)
    }

    it("Delete not existing card") {
      the [NotFoundException] thrownBy trello.deleteCard("5c5ecdc6f642c77012bb41c1")
    }
  }
}

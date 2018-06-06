package com.julienvey.trello

import com.julienvey.trello.domain.Card
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class CardIt extends FunSpec with Matchers {
  val trello = TrelloTestFactory.trello

  val validListIdFromSomeoneElsesAccount = "518baad5b05dbf4703004853"
  val unknownListId = "someUnknownId"

  describe("card API") {
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
      thrown.getMessage should include("Not authorized")
    }

    it("creates a card without board id") {
      val card = new Card()
      card.setName("card created without board id")
      card.setIdBoard("some unknown board id")
      val created = trello.createCard(TrelloConfig.toDoListId, card)
      created.getName shouldBe "card created without board id"
    }
  }
}

package com.julienvey.trello

import java.util.UUID.randomUUID

import com.julienvey.trello.domain.MemberType
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class BoardIt extends FunSpec with Matchers {
  val trello = TrelloTestFactory.trello

  it("loads board for member") {
    val boards = trello.getMemberBoards(TrelloConfig.memberName)
    val board = boards.asScala.head
    board.getName shouldBe "Trello Java Wrapper integration tests"
  }

  it("add member to board") {
    val memberId = "taco"

    trello.addMemberToBoard(TrelloConfig.boardId, memberId, MemberType.NORMAL)
      .getMembers
      .asScala
      .map(member => member.getUsername) should contain(memberId)

    trello.getBoardMembers(TrelloConfig.boardId)
      .asScala
      .map(member => member.getUsername) should contain(memberId)

    trello.removeMemberFromBoard(TrelloConfig.boardId, memberId)
      .getId should be (TrelloConfig.boardId)

    trello.getBoardMembers(TrelloConfig.boardId)
      .asScala
      .map(member => member.getUsername) should not contain memberId
  }

  it("remove not existing user from board") {
    val memberId = randomUUID().toString
    val thrown = the [NotFoundException] thrownBy trello.removeMemberFromBoard(TrelloConfig.boardId, memberId)

    thrown.getMessage should equal (s"User with memberId or username $memberId is not found.")
  }

  it("remove user that is not member of the board") {
    val memberId = "taco"
    val thrown = the [NotFoundException] thrownBy trello.removeMemberFromBoard(TrelloConfig.boardId, memberId)

    thrown.getMessage should equal (s"User with member id $memberId is not member of ${TrelloConfig.boardId} board.")
  }
}
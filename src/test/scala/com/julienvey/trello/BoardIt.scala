package com.julienvey.trello

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
}
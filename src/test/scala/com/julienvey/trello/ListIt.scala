package com.julienvey.trello

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class ListIt extends FunSpec with Matchers {
  val trello = TrelloTestFactory.trello

  it("loads lists for board") {
    val boards = trello.getMemberBoards(TrelloConfig.memberName)
    val boardId = boards.asScala.head.getId
    val lists = trello.getBoardLists(boardId)
    val list = lists.asScala.head
    list.getName shouldBe "To Do"
  }
}
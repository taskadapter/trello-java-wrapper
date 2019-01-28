package com.julienvey.trello

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class MemberIt extends FunSpec with Matchers {
  val trello = TrelloTestFactory.trello

  it("loads member boards") {
    val boards = trello.getMemberBoards(TrelloConfig.memberName)
    boards shouldNot have size 0
  }

  it("loads member actions") {
    val actions = trello.getMemberActions(TrelloConfig.memberName)
    actions shouldNot have size 0
  }
}

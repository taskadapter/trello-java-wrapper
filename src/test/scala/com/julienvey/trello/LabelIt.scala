package com.julienvey.trello

import com.julienvey.trello.domain.Label
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class LabelIt extends FunSpec with Matchers {
  val trello = TrelloTestFactory.trello

  it("get label by id") {
    val labelId = trello.createLabel(new Label()
      .setColor("green")
      .setIdBoard(TrelloConfig.boardId)
      .setName("New Label"))
      .getId

    val label = trello.getLabel(labelId)

    label.getName should be("New Label")
  }

  it("create and delete label") {
    val label = new Label()
    label.setColor("green")
    label.setIdBoard(TrelloConfig.boardId)
    label.setName("New Label")

    val createdLabel = trello.createLabel(label)

    createdLabel.getName should be(label.getName)
    createdLabel.getColor should be(label.getColor)
    createdLabel.getIdBoard should be(label.getIdBoard)
    createdLabel.getId should not be null

    trello.deleteLabel(createdLabel.getId)

    the[NotFoundException] thrownBy trello.getLabel(createdLabel.getId)
  }

  it("should throw not found when deleting not existing label") {
    the[NotFoundException] thrownBy trello.deleteLabel("5c5fde2ffb23ac3506a9d6b9")
  }

  it("should throw not found when creating label on not existing board") {
    val label = new Label()
    label.setColor("green")
    label.setIdBoard("5b15c8a233e68e7cc54ca235")
    label.setName("New Label")

    the[NotFoundException] thrownBy trello.createLabel(label)
  }

  it("update label") {
    val label = new Label()
      .setColor("green")
      .setIdBoard(TrelloConfig.boardId)
      .setName("New Label")

    val created = trello.createLabel(label)
    created.setColor("red")
    created.setName("Updated Label")

    val updated = trello.updateLabel(created)

    updated.getColor should be(created.getColor)
    updated.getName should be(created.getName)

    trello.deleteLabel(updated.getId)
  }

  it("CRUD using fluent API") {
    val label = new Label()
      .setInternalTrello(trello)
      .setColor("blue")
      .setIdBoard(TrelloConfig.boardId)
      .setName("New Label")

    label.create() should be theSameInstanceAs label
    label.getId should not be null

    label.setColor("black").update().getColor should be(label.getColor)
    label.setName("Fluently updated Label name").update().getName should be(label.getName)

    label.delete()
    the[NotFoundException] thrownBy trello.getLabel(label.getId)
  }
}

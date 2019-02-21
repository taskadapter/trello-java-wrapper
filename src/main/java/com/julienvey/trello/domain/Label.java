package com.julienvey.trello.domain;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.julienvey.trello.Trello;

/**
 * Represents the Trello's Label resource.
 * <p>
 * More information about labels can be found at <a href="https://developers.trello.com/v1.0/reference#labels">here</a>.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Label extends TrelloEntity {

    private String id;
    private String idBoard;
    private String color;
    private String name;

    /**
     * @see Trello#createLabel(Label)
     */
    public Label create() {
        Label label = getTrelloService().createLabel(this);
        id = label.id;

        return this;
    }

    /**
     * @see Trello#updateLabel(Label)
     */
    public Label update() {
        Label updateLabel = getTrelloService().updateLabel(this);

        id = updateLabel.id;
        idBoard = updateLabel.idBoard;
        color = updateLabel.color;
        name = updateLabel.name;

        return this;
    }

    /**
     * @see Trello#addLabelToCard(String, String)
     */
    public Label addToCard(String cardId) {
        getTrelloService().addLabelToCard(cardId, id);
        return this;
    }

    /**
     * @see Trello#addLabelToCard(String, String)
     */
    public Label addToCard(Card card) {
        Objects.requireNonNull(card);

        getTrelloService().addLabelToCard(card.getId(), id);

        List<Label> labels = card.getLabels();
        if (!labels.contains(this)) {
            labels.add(this);
        }

        return this;
    }

    /**
     * @see Trello#deleteLabel(String)
     */
    public void delete() {
        getTrelloService().deleteLabel(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Label setInternalTrello(Trello trelloService) {
        return super.setInternalTrello(trelloService);
    }

    /* Accessors */
    public String getId() {
        return id;
    }

    public Label setId(String id) {
        this.id = id;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Label setColor(String color) {
        this.color = color;
        return this;
    }

    public String getName() {
        return name;
    }

    public Label setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public Label setIdBoard(String idBoard) {
        this.idBoard = idBoard;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Label)) return false;
        Label label = (Label) o;
        return Objects.equals(id, label.id) &&
                Objects.equals(idBoard, label.idBoard) &&
                Objects.equals(color, label.color) &&
                Objects.equals(name, label.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idBoard, color, name);
    }

    @Override
    public String toString() {
        return "Label{" + "id='" + id + '\'' +
                ", idBoard='" + idBoard + '\'' +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

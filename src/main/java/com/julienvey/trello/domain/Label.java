package com.julienvey.trello.domain;

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
}

package com.julienvey.trello.domain;

public class Label extends TrelloEntity {

    private String color;
    private String name;

    /* Accessors */
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

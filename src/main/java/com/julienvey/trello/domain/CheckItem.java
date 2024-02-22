package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.julienvey.trello.impl.domaininternal.CheckItemState;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckItem {

    private CheckItemState state = CheckItemState.INCOMPLETE;
    private String id;
    private String name;
    private int pos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public CheckItemState getState() {
        return state;
    }

    public void setState(CheckItemState state) {
        this.state = state;
    }
}

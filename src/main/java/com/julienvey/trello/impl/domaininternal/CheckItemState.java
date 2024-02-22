package com.julienvey.trello.impl.domaininternal;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CheckItemState {
    COMPLETE("complete"),
    INCOMPLETE("incomplete");

    private final String state;

    CheckItemState(String state) {
        this.state = state;
    }

    @JsonValue
    public String getState() {
        return state;
    }
}

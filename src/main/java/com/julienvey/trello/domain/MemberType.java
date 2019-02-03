package com.julienvey.trello.domain;

public enum MemberType {
    ADMIN("admin"),
    NORMAL("normal"),
    OBSERVER("observer");

    private final String value;

    MemberType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

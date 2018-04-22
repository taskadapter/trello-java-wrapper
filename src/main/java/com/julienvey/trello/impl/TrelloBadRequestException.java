package com.julienvey.trello.impl;

public class TrelloBadRequestException extends RuntimeException {
    public TrelloBadRequestException(String message) {
        super(message);
    }
}
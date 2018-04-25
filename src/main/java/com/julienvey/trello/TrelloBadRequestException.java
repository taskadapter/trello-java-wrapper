package com.julienvey.trello;

public class TrelloBadRequestException extends RuntimeException {
    public TrelloBadRequestException(String message) {
        super(message);
    }
}
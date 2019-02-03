package com.julienvey.trello;

public class TrelloBadRequestException extends RuntimeException {
    public TrelloBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrelloBadRequestException(String message) {
        super(message);
    }

    public TrelloBadRequestException(Throwable cause) {
        super(cause);
    }
}
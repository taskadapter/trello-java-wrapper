package com.julienvey.trello;

public final class NotFoundException extends TrelloBadRequestException {
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }
}

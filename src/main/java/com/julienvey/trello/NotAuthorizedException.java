package com.julienvey.trello;

public final class NotAuthorizedException extends TrelloBadRequestException{
    public NotAuthorizedException() {
        super("Not authorized");
    }
}

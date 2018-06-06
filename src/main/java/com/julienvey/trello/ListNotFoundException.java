package com.julienvey.trello;

public final class ListNotFoundException extends TrelloBadRequestException {
    private final String listId;

    public ListNotFoundException(String listId) {
        super("Trello list with ID '" + listId + "' is not found");
        this.listId = listId;
    }

    public String getListId() {
        return listId;
    }
}

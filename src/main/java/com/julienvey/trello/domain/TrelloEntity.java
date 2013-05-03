package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.julienvey.trello.Trello;

public class TrelloEntity {

    @JsonIgnore
    protected Trello trelloService;

    public void setInternalTrello(Trello trelloService) {
        this.trelloService = trelloService;
    }
}

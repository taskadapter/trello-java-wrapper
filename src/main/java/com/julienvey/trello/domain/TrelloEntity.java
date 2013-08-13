package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.julienvey.trello.Trello;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloEntity {

    @JsonIgnore
    protected Trello trelloService;

    public void setInternalTrello(Trello trelloService) {
        this.trelloService = trelloService;
    }
}

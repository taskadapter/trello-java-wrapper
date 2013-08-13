package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardWithActions extends Card {

	private List<Action> actions;

	public List<Action> getActions() {
		return actions;
	}

}

package com.julienvey.trello.domain;

import java.util.List;

public class CardWithActions extends Card {

	private List<Action> actions;

	public List<Action> getActions() {
		return actions;
	}

}

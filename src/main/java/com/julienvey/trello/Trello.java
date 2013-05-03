package com.julienvey.trello;

import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;

import java.util.List;

public interface Trello {

    Board getBoard(String boardId);

    List<TList> getLists(String boardId);

    void createCard(String listId, Card card);
}

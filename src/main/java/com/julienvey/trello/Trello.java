package com.julienvey.trello;

import com.julienvey.trello.domain.Board;

public interface Trello {

    Board getBoard(String boardId);
}

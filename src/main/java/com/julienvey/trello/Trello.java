package com.julienvey.trello;

import com.julienvey.trello.domain.*;

import java.util.List;

public interface Trello {

    Board getBoard(String boardId, Argument... args);

    List<TList> getLists(String boardId, Argument... args);

    Member getBasicMemberInformation(String username);

    Card createCard(String listId, Card card);

    void addLabelsToCard(String idCard, String[] labels);

    List<Member> getMembers(String boardId);

}

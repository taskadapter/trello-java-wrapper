package com.julienvey.trello;

import com.julienvey.trello.domain.*;

import java.util.List;

public interface Trello {

    /* Board */
    Board getBoard(String boardId, Argument... args);

    // String getBoardField(String boardId, String field); //TODO

    List<Action> getBoardActions(String boardId, Argument... args);


    /////////////////
    List<TList> getLists(String boardId, Argument... args);

    Card createCard(String listId, Card card);

    void addLabelsToCard(String idCard, String[] labels);

    List<Member> getMembers(String boardId);

    Member getBasicMemberInformation(String username);

}

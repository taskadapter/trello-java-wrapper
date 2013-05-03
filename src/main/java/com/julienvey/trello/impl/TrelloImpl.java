package com.julienvey.trello.impl;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.TList;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.julienvey.trello.impl.TrelloURLConstants.*;

public class TrelloImpl implements Trello {

    private RestTemplate restTemplate = new RestTemplate();
    private String applicationKey;
    private String accessToken;

    public TrelloImpl(String applicationKey, String accessToken) {
        this.applicationKey = applicationKey;
        this.accessToken = accessToken;
    }

    @Override
    public Board getBoard(String boardId) {
        Board board = get(GET_BOARD_BY_ID, Board.class, boardId);
        board.setInternalTrello(this);
        return board;
    }

    @Override
    public List<TList> getLists(String boardId) {
        List<TList> tLists = Arrays.asList(get(GET_LISTS_BY_BOARD_ID, TList[].class, boardId));
        for (TList list : tLists) {
            list.setInternalTrello(this);
        }
        return tLists;
    }

    @Override
    public void createCard(String listId, Card card) {
        card.setIdList(listId);
        post(TrelloURLConstants.CREATE_CARD, card);
    }

    @Override
    public Member getBasicMemberInformation(String username) {
        Member member = get(GET_BASIC_MEMBER, Member.class, username);
        member.setInternalTrello(this);
        return member;
    }

    private <T> URI post(String url, T object) {
        return post(url, object, new String[]{});
    }

    private <T> URI post(String url, T object, String... params) {
        return restTemplate.postForLocation(url, object, enrichParams(params));
    }

    private <T> T get(String url, Class<T> objectClass, String... params) {
        return restTemplate.getForObject(url, objectClass, enrichParams(params));
    }

    private Object[] enrichParams(String[] params) {
        List<String> paramList = new ArrayList<>(Arrays.asList(params));
        paramList.add(applicationKey);
        paramList.add(accessToken);
        return paramList.toArray();
    }
}

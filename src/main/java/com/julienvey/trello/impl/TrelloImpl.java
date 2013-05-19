package com.julienvey.trello.impl;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.*;
import com.julienvey.trello.impl.domaininternal.Label;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.julienvey.trello.impl.TrelloUrl.*;

public class TrelloImpl implements Trello {

    private RestTemplate restTemplate = new RestTemplate();
    private String applicationKey;
    private String accessToken;

    public TrelloImpl(String applicationKey, String accessToken) {
        this.applicationKey = applicationKey;
        this.accessToken = accessToken;
    }

    @Override
    public Board getBoard(String boardId, Argument... args) {
        Board board = get(createUrl(GET_BOARD_BY_ID).params(args).asString(), Board.class, boardId);
        board.setInternalTrello(this);
        return board;
    }

    @Override
    public List<Action> getBoardActions(String boardId, Argument... args) {
        List<Action> actions = Arrays.asList(get(createUrl(GET_BOARD_ACTIONS).params(args).asString(), Action[].class, boardId));
        return actions;
    }

    @Override
    public List<TList> getLists(String boardId, Argument... args) {
        List<TList> tLists = Arrays.asList(get(createUrl(GET_LISTS_BY_BOARD_ID).params(args).asString(), TList[].class, boardId));
        for (TList list : tLists) {
            list.setInternalTrello(this);
        }
        return tLists;
    }

    @Override
    public Card createCard(String listId, Card card) {
        card.setIdList(listId);
        Card createdCard = postForObject(CREATE_CARD, card, Card.class);
        createdCard.setInternalTrello(this);
        return createdCard;
    }

    @Override
    public Member getBasicMemberInformation(String username) {
        Member member = get(GET_BASIC_MEMBER, Member.class, username);
        member.setInternalTrello(this);
        return member;
    }

    public void addLabelsToCard(String idCard, String[] labels) {
        for (String label : labels) {
            postForLocation(ADD_LABEL_TO_CARD, new Label(label), idCard);
        }
    }

    @Override
    public List<Member> getMembers(String boardId) {
        return Arrays.asList(get(GET_BOARD_MEMBERS, Member[].class, boardId));
    }

    private <T> T postForObject(String url, T object, Class<T> objectClass, String... params) {
        return restTemplate.postForObject(url, object, objectClass, enrichParams(params));
    }

    private void postForLocation(String url, Object object, String... params) {
        restTemplate.postForLocation(url, object, enrichParams(params));
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

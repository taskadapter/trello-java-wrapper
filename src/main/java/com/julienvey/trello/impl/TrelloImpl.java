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
        Board board = get(createUrl(GET_BOARD).params(args).asString(), Board.class, boardId);
        board.setInternalTrello(this);
        return board;
    }

    @Override
    public List<Action> getBoardActions(String boardId, Argument... args) {
        return Arrays.asList(get(createUrl(GET_BOARD_ACTIONS).params(args).asString(), Action[].class, boardId));
    }

    @Override
    public List<Card> getBoardCards(String boardId, Argument... args) {
        return Arrays.asList(get(createUrl(GET_BOARD_CARDS).params(args).asString(), Card[].class, boardId));
    }

    @Override
    public Card getBoardCard(String boardId, String cardId, Argument... args) {
        return get(createUrl(GET_BOARD_CARD).params(args).asString(), Card.class, boardId, cardId);
    }

    @Override
    public List<CheckList> getBoardChecklists(String boardId, Argument... args) {
        return Arrays.asList(get(createUrl(GET_BOARD_CHECKLISTS).params(args).asString(), CheckList[].class, boardId));
    }

    @Override
    public List<TList> getBoardLists(String boardId, Argument... args) {
        List<TList> tLists = Arrays.asList(get(createUrl(GET_BOARD_LISTS).params(args).asString(), TList[].class, boardId));
        for (TList list : tLists) {
            list.setInternalTrello(this);
        }
        return tLists;
    }

    @Override
    public List<Member> getBoardMembers(String boardId, Argument... args) {
        return Arrays.asList(get(createUrl(GET_BOARD_MEMBERS).params(args).asString(), Member[].class, boardId));
    }

    @Override
    public List<Card> getBoardMemberCards(String boardId, String memberId, Argument... args) {
        return Arrays.asList(get(createUrl(GET_BOARD_MEMBER_CARDS).params(args).asString(), Card[].class, boardId, memberId));
    }

    @Override
    public List<Member> getBoardMembersInvited(String boardId, Argument... args) {
        return Arrays.asList(get(createUrl(GET_BOARD_MEMBERS_INVITED).params(args).asString(), Member[].class, boardId));
    }

    @Override
    public MyPrefs getBoardMyPrefs(String boardId) {
        return get(createUrl(GET_BOARD_MYPREFS).asString(), MyPrefs.class, boardId);
    }

    @Override
    public Organization getBoardOrganization(String boardId, Argument... args) {
        return get(createUrl(GET_BOARD_ORGANIZATION).params(args).asString(), Organization.class, boardId);
    }

    @Override
    public Action getAction(String actionId, Argument... args) {
        return get(createUrl(GET_ACTION).params(args).asString(), Action.class, actionId);
    }

    @Override
    public Board getActionBoard(String actionId, Argument... args) {
        return get(createUrl(GET_ACTION_BOARD).params(args).asString(), Board.class, actionId);
    }

    @Override
    public Card getActionCard(String actionId, Argument... args) {
        return get(createUrl(GET_ACTION_CARD).params(args).asString(), Card.class, actionId);
    }

    @Override
    public List<Entity> getActionEntities(String actionId) {
        return Arrays.asList(get(createUrl(GET_ACTION_ENTITIES).asString(), Entity[].class, actionId));
    }

    @Override
    public TList getActionList(String actionId, Argument... args) {
        return get(createUrl(GET_ACTION_LIST).params(args).asString(), TList.class, actionId);
    }

    @Override
    public Member getActionMember(String actionId, Argument... args) {
        return get(createUrl(GET_ACTION_MEMBER).params(args).asString(), Member.class, actionId);
    }

    @Override
    public Card createCard(String listId, Card card) {
        card.setIdList(listId);
        Card createdCard = postForObject(createUrl(CREATE_CARD).asString(), card, Card.class);
        createdCard.setInternalTrello(this);
        return createdCard;
    }

    @Override
    public Member getBasicMemberInformation(String username) {
        Member member = get(createUrl(GET_BASIC_MEMBER).asString(), Member.class, username);
        member.setInternalTrello(this);
        return member;
    }

    public void addLabelsToCard(String idCard, String[] labels) {
        for (String label : labels) {
            postForLocation(createUrl(ADD_LABEL_TO_CARD).asString(), new Label(label), idCard);
        }
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

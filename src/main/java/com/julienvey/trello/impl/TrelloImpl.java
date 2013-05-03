package com.julienvey.trello.impl;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.TList;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.julienvey.trello.impl.TrelloURLConstants.GET_BOARD_BY_ID;
import static com.julienvey.trello.impl.TrelloURLConstants.GET_LISTS_BY_BOARD_ID;

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
        return Arrays.asList(get(GET_LISTS_BY_BOARD_ID, TList[].class, boardId));
    }

    private <T> T get(String url, Class<T> objectClass, String... params) {
        List<String> paramList = new ArrayList<String>(Arrays.asList(params));
        paramList.add(applicationKey);
        paramList.add(accessToken);
        return restTemplate.getForObject(url, objectClass, paramList.toArray());
    }
}

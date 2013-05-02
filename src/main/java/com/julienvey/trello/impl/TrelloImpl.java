package com.julienvey.trello.impl;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.julienvey.trello.impl.TrelloURLConstants.GET_BOARD_BY_ID;

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
        return get(GET_BOARD_BY_ID, Board.class, boardId);
    }

    private <T> T get(String url, Class<T> objectClass, String... params) {
        List<String> paramList = new ArrayList<String>(Arrays.asList(params));
        paramList.add(applicationKey);
        paramList.add(accessToken);
        System.out.println("Trying to restTemplate " + url + params + ":::" + applicationKey + ":::" + accessToken);
        return restTemplate.getForObject(url, objectClass, paramList.toArray());
    }
}

package com.julienvey.trello.impl;

import com.julienvey.trello.domain.Argument;

public class TrelloUrl {

    public static final String API_URL = "https://api.trello.com/1";
    public static final String API_KEY_TOKEN_PARAM = "key={applicationKey}&token={userToken}";
    public static final String GET_BOARD_BY_ID = API_URL + "/boards/{boardId}?" + API_KEY_TOKEN_PARAM;
    public static final String GET_LISTS_BY_BOARD_ID = API_URL + "/boards/{boardId}/lists?" + API_KEY_TOKEN_PARAM;
    public static final String CREATE_CARD = API_URL + "/cards?pos=top&" + API_KEY_TOKEN_PARAM;
    public static final String GET_BASIC_MEMBER = API_URL + "/members/{username}?fields=username,fullName&" + API_KEY_TOKEN_PARAM;
    public static final String ADD_LABEL_TO_CARD = API_URL + "/cards/{cardId}/labels?" + API_KEY_TOKEN_PARAM;
    public static final String GET_BOARD_MEMBERS = API_URL + "/boards/{boardId}/members?" + API_KEY_TOKEN_PARAM;
    private static Argument[] args;

    private String baseUrl;

    private TrelloUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static TrelloUrl createUrl(String baseUrl){
        return new TrelloUrl(baseUrl);
    }

    public TrelloUrl params(Argument... args){
        TrelloUrl.args = args;
        return this;
    }

    public String asString() {
        StringBuilder builder = new StringBuilder(baseUrl);
        for(Argument arg : args){
            builder.append("&");
            builder.append(arg.getArgName());
            builder.append("=");
            builder.append(arg.getArgValue());
        }
        String url = builder.toString();
        System.out.println("URL : " + url);
        return url;
    }
}
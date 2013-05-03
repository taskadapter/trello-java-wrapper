package com.julienvey.trello.impl;

public interface TrelloURLConstants {

    public static final String API_URL = "https://api.trello.com/1";
    public static final String API_KEY_TOKEN_PARAM = "key={applicationKey}&token={userToken}";
    public static final String GET_BOARD_BY_ID = API_URL + "/boards/{boardId}?" + API_KEY_TOKEN_PARAM;
    public static final String GET_LISTS_BY_BOARD_ID = API_URL + "/boards/{boardId}/lists?cards=open&" + API_KEY_TOKEN_PARAM;
    public static final String CREATE_CARD = API_URL + "/cards?" + API_KEY_TOKEN_PARAM;
    public static final String GET_BASIC_MEMBER = API_URL + "/members/{username}?fields=username,fullName&" + API_KEY_TOKEN_PARAM;
    public static final String ADD_LABEL_TO_CARD = API_URL + "/cards/{cardId}/labels?" + API_KEY_TOKEN_PARAM;
    public static final String GET_BOARD_MEMBERS = API_URL + "/boards/{boardId}/members?" + API_KEY_TOKEN_PARAM;


}

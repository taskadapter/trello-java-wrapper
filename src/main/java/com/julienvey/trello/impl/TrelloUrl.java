package com.julienvey.trello.impl;

import com.julienvey.trello.domain.Argument;

public class TrelloUrl {

    public static final String API_URL = "https://api.trello.com/1";
    public static final String API_KEY_TOKEN_PARAM = "key={applicationKey}&token={userToken}";

    public static final String GET_BOARD = "/boards/{boardId}?";
    public static final String GET_BOARD_ACTIONS = "/boards/{boardId}/actions?";
    public static final String GET_BOARD_CARDS = "/boards/{boardId}/cards?";
    public static final String GET_BOARD_CARD = "/boards/{boardId}/cards/{cardId}?";
    public static final String GET_BOARD_CHECKLISTS = "/boards/{boardId}/checklists?";
    public static final String GET_BOARD_MEMBERS = "/boards/{boardId}/members?";
    public static final String GET_BOARD_MEMBER_CARDS = "/boards/{boardId}/members/{memberId}/cards?";
    public static final String GET_BOARD_LISTS = "/boards/{boardId}/lists?";
    public static final String GET_BOARD_MEMBERS_INVITED = "/boards/{boardId}/membersInvited?";
    public static final String GET_BOARD_MYPREFS = "/boards/{boardId}/myPrefs?";
    public static final String GET_BOARD_ORGANIZATION = "/boards/{boardId}/organization?";

    public static final String GET_ACTION = "/actions/{actionId}?";
    public static final String GET_ACTION_BOARD = "/actions/{actionId}/board?";
    public static final String GET_ACTION_CARD = "/actions/{actionId}/card?";
    public static final String GET_ACTION_ENTITIES = "/actions/{actionId}/entities?";
    public static final String GET_ACTION_LIST = "/actions/{actionId}/list?";
    public static final String GET_ACTION_MEMBER = "/actions/{actionId}/member?";
    public static final String GET_ACTION_MEMBER_CREATOR = "/actions/{actionId}/memberCreator?";
    public static final String GET_ACTION_ORGANIZATION = "/actions/{actionId}/organization?";

    public static final String GET_CARD = "/cards/{cardId}?";
    public static final String GET_CARD_ACTIONS = "/cards/{cardId}/actions?";
    public static final String GET_CARD_ATTACHMENTS = "/cards/{cardId}/attachments?";
    public static final String GET_CARD_ATTACHMENT = "/cards/{cardId}/attachments/{attachmentId}?";
    public static final String GET_CARD_BOARD = "/cards/{cardId}/board?";

    public static final String GET_LIST = "/lists/{listId}?";

    public static final String GET_CHECK_LIST = "/checklists/{checkListId}?";
    public static final String CREATE_CHECKLIST = "/checklists?";
    public static final String ADD_CHECKITEMS_TO_CHECKLIST = "/checklists/{checkListId}/checkitems?";

    public static final String CREATE_CARD = "/cards?pos=top&";
    public static final String GET_MEMBER = "/members/{username}?";
    public static final String ADD_LABEL_TO_CARD = "/cards/{cardId}/labels?";
    public static final String ADD_COMMENT_TO_CARD = "/cards/{cardId}/actions/comments?";

    public static final String UPDATE_CARD = "/cards/{cardId}?";



    private String baseUrl;
    private Argument[] args = {};

    private TrelloUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static TrelloUrl createUrl(String baseUrl){
        return new TrelloUrl(baseUrl);
    }

    public TrelloUrl params(Argument... args){
        this.args = args;
        return this;
    }

    public String asString() {
        StringBuilder builder = new StringBuilder(API_URL);
        builder.append(baseUrl);
        builder.append(API_KEY_TOKEN_PARAM);
        for(Argument arg : args){
            builder.append("&");
            builder.append(arg.getArgName());
            builder.append("=");
            builder.append(arg.getArgValue());
        }
        return builder.toString();
    }
}
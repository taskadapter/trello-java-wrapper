package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyPrefs extends TrelloEntity {
    private boolean showSidebar;
    private boolean showSidebarMembers;
    private boolean showSidebarBoardActions;
    private boolean showSidebarActivity;
    private boolean showListGuide;
    private String emailKey;
    private String idEmailList;
    private String emailPosition;

    public boolean isShowListGuide() {
        return showListGuide;
    }

    public void setShowListGuide(boolean showListGuide) {
        this.showListGuide = showListGuide;
    }

    public boolean isShowSidebar() {
        return showSidebar;
    }

    public void setShowSidebar(boolean showSidebar) {
        this.showSidebar = showSidebar;
    }

    public boolean isShowSidebarActivity() {
        return showSidebarActivity;
    }

    public void setShowSidebarActivity(boolean showSidebarActivity) {
        this.showSidebarActivity = showSidebarActivity;
    }

    public boolean isShowSidebarBoardActions() {
        return showSidebarBoardActions;
    }

    public void setShowSidebarBoardActions(boolean showSidebarBoardActions) {
        this.showSidebarBoardActions = showSidebarBoardActions;
    }

    public boolean isShowSidebarMembers() {
        return showSidebarMembers;
    }

    public void setShowSidebarMembers(boolean showSidebarMembers) {
        this.showSidebarMembers = showSidebarMembers;
    }

    public String getEmailKey() {
        return emailKey;
    }

    public void setEmailKey(String emailKey) {
        this.emailKey = emailKey;
    }

    public String getIdEmailList() {
        return idEmailList;
    }

    public void setIdEmailList(String idEmailList) {
        this.idEmailList = idEmailList;
    }

    public String getEmailPosition() {
        return emailPosition;
    }

    public void setEmailPosition(String emailPosition) {
        this.emailPosition = emailPosition;
    }
}

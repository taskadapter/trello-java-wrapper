package com.julienvey.trello.domain;

public class MyPrefs {
    private boolean showSidebar;
    private boolean showSidebarMembers;
    private boolean showSidebarBoardActions;
    private boolean showSidebarActivity;
    private boolean showListGuide;

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
}

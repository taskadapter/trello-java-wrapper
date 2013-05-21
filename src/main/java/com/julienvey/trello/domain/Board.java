package com.julienvey.trello.domain;

import java.util.List;
import java.util.Map;

public class Board extends TrelloEntity {

    private String id;
    private String name;
    private String desc;
    private boolean closed;
    private String idOrganization;
    private boolean pinned;
    private String url;
    private Map<String, String> prefs;
    private Map<String, String> labelNames;

    /* API */

    public List<Action> fetchActions(Argument... args) {
        return trelloService.getBoardActions(id, args);
    }

    public List<Card> fetchCards(Argument... args){
        return trelloService.getBoardCards(id, args);
    }

    public Card fetchCard(String cardId, Argument... args){
        return trelloService.getBoardCard(id, cardId, args);
    }

    public List<CheckList> fetchCheckLists(Argument... args){
        return trelloService.getBoardChecklists(id, args);
    }

    public List<TList> fetchLists(Argument... args) {
        return trelloService.getBoardLists(id, args);
    }

    public List<Member> fetchMembers() {
        return trelloService.getBoardMembers(id);
    }

    public List<Card> fetchMemberCards(String idMember, Argument... args){
        return trelloService.getBoardMemberCards(id, idMember, args);
    }

    public List<Member> fetchMembersInvited(Argument... args){
        return trelloService.getBoardMembersInvited(id, args);
    }

    public Prefs fetchMyPrefs(){
        return trelloService.getBoardMyPrefs(id);
    }

    public Organization fetchOrganization(Argument... args){
        return trelloService.getBoardOrganization(id, args);
    }

    /* Accessors */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(String idOrganization) {
        this.idOrganization = idOrganization;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getPrefs() {
        return prefs;
    }

    public void setPrefs(Map<String, String> prefs) {
        this.prefs = prefs;
    }

    public Map<String, String> getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(Map<String, String> labelNames) {
        this.labelNames = labelNames;
    }
}

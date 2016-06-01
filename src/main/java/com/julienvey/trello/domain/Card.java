package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card extends TrelloEntity {

    private String id;
    private String name;
    private String idList;
    private String desc;
    private String url;
    private Date due;
    private List<String> idMembers;
    private List<Label> labels;
    private Badges badges;
    private List<CardCheckItem> checkItemStates;
    private boolean closed;
    private Date dateLastActivity;
    private String idBoard;
    private List<String> idChecklists;
    private List<String> idMembersVoted;
    private String idShort;
    private String idAttachmentCover;
    private boolean manualCoverAttachment;
    private int pos;
    private String shortLink;
    private String shortUrl;
    private boolean subscribed;

    /* API */
    public void addLabels(String... labels) {
        trelloService.addLabelsToCard(id, labels);
    }

    public void addComment(String comment) {
        trelloService.addCommentToCard(id, comment);
    }

    public List<Action> getActions(Argument... filters) {
        return trelloService.getCardActions(id, filters);
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

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public List<String> getIdMembers() {
        return idMembers;
    }

    public void setIdMembers(List<String> idMembers) {
        this.idMembers = idMembers;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CardCheckItem> getCheckItemStates() {
        return checkItemStates;
    }

    public void setCheckItemStates(List<CardCheckItem> checkItemStates) {
        this.checkItemStates = checkItemStates;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Date getDateLastActivity() {
        return dateLastActivity;
    }

    public void setDateLastActivity(Date dateLastActivity) {
        this.dateLastActivity = dateLastActivity;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }

    public List<String> getIdChecklists() {
        return idChecklists;
    }

    public void setIdChecklists(List<String> idChecklists) {
        this.idChecklists = idChecklists;
    }

    public List<String> getIdMembersVoted() {
        return idMembersVoted;
    }

    public void setIdMembersVoted(List<String> idMembersVoted) {
        this.idMembersVoted = idMembersVoted;
    }

    public String getIdShort() {
        return idShort;
    }

    public void setIdShort(String idShort) {
        this.idShort = idShort;
    }

    public String getIdAttachmentCover() {
        return idAttachmentCover;
    }

    public void setIdAttachmentCover(String idAttachmentCover) {
        this.idAttachmentCover = idAttachmentCover;
    }

    public boolean isManualCoverAttachment() {
        return manualCoverAttachment;
    }

    public void setManualCoverAttachment(boolean manualCoverAttachment) {
        this.manualCoverAttachment = manualCoverAttachment;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Badges getBadges() {
        return badges;
    }

    public void setBadges(Badges badges) {
        this.badges = badges;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Card update() {
        return trelloService.updateCard(this);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CardCheckItem {

        private String idCheckItem;
        private String state;

        public String getIdCheckItem() {
            return idCheckItem;
        }

        public void setIdCheckItem(String idCheckItem) {
            this.idCheckItem = idCheckItem;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

    }
}

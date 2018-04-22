package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Board extends TrelloEntity {

    private String id;
    private String name;
    private String desc;
    private boolean closed;
    private String idOrganization;
    private boolean pinned;
    private String url;
    private Map<String, String> labelNames;
    private boolean invited;
    private List<String> invitations;
    private List<Membership> memberships;
    private String shortUrl;
    private boolean subscribed;
    private Prefs prefs;
    private Date dateLastActivity;
    private Date dateLastView;
    private String shortLink;
    private List<String> powerUps;
    private List<TList> lists  = new ArrayList<>();

    /* API */

    public List<Action> fetchActions(Argument... args) {
        return trelloService.getBoardActions(id, args);
    }

    public List<Card> fetchCards(Argument... args) {
        return trelloService.getBoardCards(id, args);
    }

    public Card fetchCard(String cardId, Argument... args) {
        return trelloService.getBoardCard(id, cardId, args);
    }

    public List<CheckList> fetchCheckLists(Argument... args) {
        return trelloService.getBoardChecklists(id, args);
    }

    public List<TList> fetchLists(Argument... args) {
        return trelloService.getBoardLists(id, args);
    }

    public List<Member> fetchMembers() {
        return trelloService.getBoardMembers(id);
    }

    public List<Card> fetchMemberCards(String idMember, Argument... args) {
        return trelloService.getBoardMemberCards(id, idMember, args);
    }

    public List<Member> fetchMembersInvited(Argument... args) {
        return trelloService.getBoardMembersInvited(id, args);
    }

    public MyPrefs fetchMyPrefs() {
        return trelloService.getBoardMyPrefs(id);
    }

    public Organization fetchOrganization(Argument... args) {
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

    public Map<String, String> getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(Map<String, String> labelNames) {
        this.labelNames = labelNames;
    }

    public boolean isInvited() {
        return invited;
    }

    public void setInvited(boolean invited) {
        this.invited = invited;
    }

    public List<String> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<String> invitations) {
        this.invitations = invitations;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Date getDateLastActivity() {
        return dateLastActivity;
    }

    public void setDateLastActivity(Date dateLastActivity) {
        this.dateLastActivity = dateLastActivity;
    }

    public Date getDateLastView() {
        return dateLastView;
    }

    public void setDateLastView(Date dateLastView) {
        this.dateLastView = dateLastView;
    }

    public Prefs getPrefs() {
        return prefs;
    }

    public void setPrefs(Prefs prefs) {
        this.prefs = prefs;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public List<String> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(List<String> powerUps) {
        this.powerUps = powerUps;
    }

    public List<TList> getLists() {
        return lists;
    }

    public void setLists(List<TList> lists) {
        this.lists = lists;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Prefs {
        private String permissionLevel;
        private String voting;
        private String comments;
        private String invitations;
        private String cardAging;
        private boolean selfJoin;
        private boolean cardCovers;
        private boolean canBePublic;
        private boolean canBeOrg;
        private boolean canBePrivate;
        private boolean canInvite;
        private boolean calendarFeedEnabled;

        public boolean isCalendarFeedEnabled() {
            return calendarFeedEnabled;
        }

        public void setCalendarFeedEnabled(boolean calendarFeedEnabled) {
            this.calendarFeedEnabled = calendarFeedEnabled;
        }

        public String getCardAging() {
            return cardAging;
        }

        public void setCardAging(String cardAging) {
            this.cardAging = cardAging;
        }

        public String getPermissionLevel() {
            return permissionLevel;
        }

        public void setPermissionLevel(String permissionLevel) {
            this.permissionLevel = permissionLevel;
        }

        public String getVoting() {
            return voting;
        }

        public void setVoting(String voting) {
            this.voting = voting;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getInvitations() {
            return invitations;
        }

        public void setInvitations(String invitations) {
            this.invitations = invitations;
        }

        public boolean isSelfJoin() {
            return selfJoin;
        }

        public void setSelfJoin(boolean selfJoin) {
            this.selfJoin = selfJoin;
        }

        public boolean isCardCovers() {
            return cardCovers;
        }

        public void setCardCovers(boolean cardCovers) {
            this.cardCovers = cardCovers;
        }

        public boolean isCanBePublic() {
            return canBePublic;
        }

        public void setCanBePublic(boolean canBePublic) {
            this.canBePublic = canBePublic;
        }

        public boolean isCanBeOrg() {
            return canBeOrg;
        }

        public void setCanBeOrg(boolean canBeOrg) {
            this.canBeOrg = canBeOrg;
        }

        public boolean isCanBePrivate() {
            return canBePrivate;
        }

        public void setCanBePrivate(boolean canBePrivate) {
            this.canBePrivate = canBePrivate;
        }

        public boolean isCanInvite() {
            return canInvite;
        }

        public void setCanInvite(boolean canInvite) {
            this.canInvite = canInvite;
        }
    }
}

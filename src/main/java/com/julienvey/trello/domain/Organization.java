package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization extends TrelloEntity {

    private String id;
    private String name;
    private String displayName;
    private String desc;
    private List<String> idBoards;
    private boolean invited;
    private List<String> invitations;
    private List<Membership> memberships;
    private List<String> powerUps;
    private String url;
    private String website;
    private String logoHash;
    private List<String> premiumFeatures;
    private Prefs prefs;
    private List<String> products;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIdBoards() {
        return idBoards;
    }

    public void setIdBoards(List<String> idBoards) {
        this.idBoards = idBoards;
    }

    public List<String> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<String> invitations) {
        this.invitations = invitations;
    }

    public boolean isInvited() {
        return invited;
    }

    public void setInvited(boolean invited) {
        this.invited = invited;
    }

    public String getLogoHash() {
        return logoHash;
    }

    public void setLogoHash(String logoHash) {
        this.logoHash = logoHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(List<String> powerUps) {
        this.powerUps = powerUps;
    }

    public List<String> getPremiumFeatures() {
        return premiumFeatures;
    }

    public void setPremiumFeatures(List<String> premiumFeatures) {
        this.premiumFeatures = premiumFeatures;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Prefs getPrefs() {
        return prefs;
    }

    public void setPrefs(Prefs prefs) {
        this.prefs = prefs;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Prefs {
        private String permissionLevel;
        private List<String> orgInviteRestrict;
        private boolean externalMembersDisabled;
        private String associatedDomain;
        private BoardVisibilityRestrict boardVisibilityRestrict;

        public String getAssociatedDomain() {
            return associatedDomain;
        }

        public void setAssociatedDomain(String associatedDomain) {
            this.associatedDomain = associatedDomain;
        }

        public boolean isExternalMembersDisabled() {
            return externalMembersDisabled;
        }

        public void setExternalMembersDisabled(boolean externalMembersDisabled) {
            this.externalMembersDisabled = externalMembersDisabled;
        }

        public List<String> getOrgInviteRestrict() {
            return orgInviteRestrict;
        }

        public void setOrgInviteRestrict(List<String> orgInviteRestrict) {
            this.orgInviteRestrict = orgInviteRestrict;
        }

        public String getPermissionLevel() {
            return permissionLevel;
        }

        public void setPermissionLevel(String permissionLevel) {
            this.permissionLevel = permissionLevel;
        }

        public BoardVisibilityRestrict getBoardVisibilityRestrict() {
            return boardVisibilityRestrict;
        }

        public void setBoardVisibilityRestrict(BoardVisibilityRestrict boardVisibilityRestrict) {
            this.boardVisibilityRestrict = boardVisibilityRestrict;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class BoardVisibilityRestrict {
            @JsonProperty("private")
            private String privateVisibility;

            @JsonProperty("org")
            private String orgVisibility;

            @JsonProperty("public")
            private String publicVisibility;

            public String getOrgVisibility() {
                return orgVisibility;
            }

            public void setOrgVisibility(String orgVisibility) {
                this.orgVisibility = orgVisibility;
            }

            public String getPrivateVisibility() {
                return privateVisibility;
            }

            public void setPrivateVisibility(String privateVisibility) {
                this.privateVisibility = privateVisibility;
            }

            public String getPublicVisibility() {
                return publicVisibility;
            }

            public void setPublicVisibility(String publicVisibility) {
                this.publicVisibility = publicVisibility;
            }
        }
    }
}

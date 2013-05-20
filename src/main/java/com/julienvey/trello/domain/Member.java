package com.julienvey.trello.domain;

public class Member extends TrelloEntity {

    private String id;
    private String username;
    private String fullName;
    private String avatarHash;
    private String bio;
    private String idPremOrgsAdmin;
    private String initials;
    private String memberType;
    private String status;
    private String url;

    /* Accessors */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarHash() {
        return avatarHash;
    }

    public void setAvatarHash(String avatarHash) {
        this.avatarHash = avatarHash;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getIdPremOrgsAdmin() {
        return idPremOrgsAdmin;
    }

    public void setIdPremOrgsAdmin(String idPremOrgsAdmin) {
        this.idPremOrgsAdmin = idPremOrgsAdmin;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

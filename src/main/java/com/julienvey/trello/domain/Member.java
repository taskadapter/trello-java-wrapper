package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Member extends TrelloEntity {

    private String id;
    private String username;
    private String fullName;
    private String avatarHash;
    private String avatarSource;
    private String bio;
    private List<String> idPremOrgsAdmin;
    private String initials;
    private String memberType;
    private String status;
    private String url;
    private boolean confirmed;
    private String email;
    private String gravatarHash;
    private List<String> idBoards;
    private List<String> idBoardsInvited;
    private List<String> idBoardsPinned;
    private List<String> idOrganizations;
    private List<String> idOrganizationsInvited;
    private List<String> loginTypes;
    private String newEmail;
    private List<String> oneTimeMessagesDismissed;
    private Map<String, Object> prefs;
    private List<String> trophies;
    private String uploadedAvatarHash;

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

    public List<String> getIdPremOrgsAdmin() {
        return idPremOrgsAdmin;
    }

    public void setIdPremOrgsAdmin(List<String> idPremOrgsAdmin) {
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

    public String getAvatarSource() {
        return avatarSource;
    }

    public void setAvatarSource(String avatarSource) {
        this.avatarSource = avatarSource;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGravatarHash() {
        return gravatarHash;
    }

    public void setGravatarHash(String gravatarHash) {
        this.gravatarHash = gravatarHash;
    }

    public List<String> getIdBoards() {
        return idBoards;
    }

    public void setIdBoards(List<String> idBoards) {
        this.idBoards = idBoards;
    }

    public List<String> getIdBoardsInvited() {
        return idBoardsInvited;
    }

    public void setIdBoardsInvited(List<String> idBoardsInvited) {
        this.idBoardsInvited = idBoardsInvited;
    }

    public List<String> getIdBoardsPinned() {
        return idBoardsPinned;
    }

    public void setIdBoardsPinned(List<String> idBoardsPinned) {
        this.idBoardsPinned = idBoardsPinned;
    }

    public List<String> getIdOrganizations() {
        return idOrganizations;
    }

    public void setIdOrganizations(List<String> idOrganizations) {
        this.idOrganizations = idOrganizations;
    }

    public List<String> getIdOrganizationsInvited() {
        return idOrganizationsInvited;
    }

    public void setIdOrganizationsInvited(List<String> idOrganizationsInvited) {
        this.idOrganizationsInvited = idOrganizationsInvited;
    }

    public List<String> getLoginTypes() {
        return loginTypes;
    }

    public void setLoginTypes(List<String> loginTypes) {
        this.loginTypes = loginTypes;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public List<String> getOneTimeMessagesDismissed() {
        return oneTimeMessagesDismissed;
    }

    public void setOneTimeMessagesDismissed(List<String> oneTimeMessagesDismissed) {
        this.oneTimeMessagesDismissed = oneTimeMessagesDismissed;
    }

    public Map<String, Object> getPrefs() {
        return prefs;
    }

    public void setPrefs(Map<String, Object> prefs) {
        this.prefs = prefs;
    }

    public List<String> getTrophies() {
        return trophies;
    }

    public void setTrophies(List<String> trophies) {
        this.trophies = trophies;
    }

    public String getUploadedAvatarHash() {
        return uploadedAvatarHash;
    }

    public void setUploadedAvatarHash(String uploadedAvatarHash) {
        this.uploadedAvatarHash = uploadedAvatarHash;
    }
}

package com.julienvey.trello.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Membership {

    private String id;
    private String idMember;
    private String memberType;
    private boolean unconfirmed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public boolean isUnconfirmed() {
        return unconfirmed;
    }

    public void setUnconfirmed(boolean unconfirmed) {
        this.unconfirmed = unconfirmed;
    }
}

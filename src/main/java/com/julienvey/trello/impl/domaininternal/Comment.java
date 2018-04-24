package com.julienvey.trello.impl.domaininternal;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.julienvey.trello.domain.Action.MemberShort;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    private String id;
    private String text;
    private String idMemberCreator;
    private Date date;
    private MemberShort memberCreator;
    private MemberShort member;

    public Comment() {
    }

    public Comment(String text) {
        super();
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIdMemberCreator() {
        return idMemberCreator;
    }

    public void setIdMemberCreator(String idMemberCreator) {
        this.idMemberCreator = idMemberCreator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MemberShort getMemberCreator() {
        return memberCreator;
    }

    public void setMemberCreator(MemberShort memberCreator) {
        this.memberCreator = memberCreator;
    }

    public MemberShort getMember() {
        return member;
    }

    public void setMember(MemberShort member) {
        this.member = member;
    }

}

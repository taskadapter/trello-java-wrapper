package com.julienvey.trello.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.julienvey.trello.Trello;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddMemberToBoardResult extends TrelloEntity {
    private String id;
    private List<Member> members = new ArrayList<>();
    private List<Membership> memberships = new ArrayList<>();

    @Override
    @SuppressWarnings("unchecked")
    public AddMemberToBoardResult setInternalTrello(Trello trelloService) {
        this.trelloService = trelloService;
        members.forEach(member -> member.setInternalTrello(trelloService));
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }
}

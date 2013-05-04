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

    public void addLabels(String... labels) {
        trelloService.addLabelsToCard(id, labels);
    }

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
}

package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.julienvey.trello.Trello;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {

    private String id;
    private String name;
    private String desc;
    private boolean closed;
    private String idOrganization;
    private boolean pinned;
    private String url;
    private Map<String, Object> prefs;
    private Map<String, Object> labelNames;

    @JsonIgnore
    private Trello trelloService;

    public void setInternalTrello(Trello trelloService) {
        this.trelloService = trelloService;
    }

    public List<TList> getLists() {
        return trelloService.getLists(id);
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

    public Map<String, Object> getPrefs() {
        return prefs;
    }

    public void setPrefs(Map<String, Object> prefs) {
        this.prefs = prefs;
    }

    public Map<String, Object> getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(Map<String, Object> labelNames) {
        this.labelNames = labelNames;
    }
}

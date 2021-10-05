package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.julienvey.trello.Trello;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Webhook extends TrelloEntity {
    private String id;
    private String description;
    private String idModel;
    private String callbackURL;
    private boolean active;
    private int consecutiveFailures;
    private String firstConsecutiveFailDate;

    public String getId() {
        return id;
    }

    public Webhook setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Webhook setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getIdModel() {
        return idModel;
    }

    public Webhook setIdModel(String idModel) {
        this.idModel = idModel;
        return this;
    }

    public String getCallbackURL() {
        return callbackURL;
    }

    public Webhook setCallbackURL(String callbackUrl) {
        this.callbackURL = callbackUrl;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Webhook setActive(boolean active) {
        this.active = active;
        return this;
    }

    public int getConsecutiveFailures() {
        return consecutiveFailures;
    }

    public Webhook setConsecutiveFailures(int consecutiveFailures) {
        this.consecutiveFailures = consecutiveFailures;
        return this;
    }

    public String getFirstConsecutiveFailDate() {
        return firstConsecutiveFailDate;
    }

    public Webhook setFirstConsecutiveFailDate(String firstConsecutiveFailDate) {
        this.firstConsecutiveFailDate = firstConsecutiveFailDate;
        return this;
    }

    public Webhook create() {
        Webhook webhook = getTrelloService().createWebhook(this);
        id = webhook.id;

        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Webhook setInternalTrello(Trello trelloService) {
        return super.setInternalTrello(trelloService);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Webhook webhook = (Webhook) o;
        return active == webhook.active &&
                consecutiveFailures == webhook.consecutiveFailures &&
                id.equals(webhook.id) &&
                Objects.equals(description, webhook.description) &&
                idModel.equals(webhook.idModel) &&
                callbackURL.equals(webhook.callbackURL) &&
                Objects.equals(firstConsecutiveFailDate, webhook.firstConsecutiveFailDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, idModel, callbackURL, active, consecutiveFailures, firstConsecutiveFailDate);
    }

    @Override
    public String toString() {
        return "Webhook{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", idModel='" + idModel + '\'' +
                ", callbackUrl='" + callbackURL + '\'' +
                ", active=" + active +
                ", consecutiveFailures=" + consecutiveFailures +
                ", firstConsecutiveFailDate='" + firstConsecutiveFailDate + '\'' +
                '}';
    }
}

package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cover extends TrelloEntity {

    private String idAttachment;
    private String color;
    private String idUploadedBackground;
    private String size;
    private String brightness;
    private String idPlugin;

    public Cover(String color, String size, String brightness) {
        this.color = color;
        this.size = size;
        this.brightness = brightness;
    }

    /* Accessors */
    public String getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(String idAttachment) {
        this.idAttachment = idAttachment;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIdUploadedBackground() {
        return idUploadedBackground;
    }

    public void setIdUploadedBackground(String idUploadedBackground) {
        this.idUploadedBackground = idUploadedBackground;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public String getIdPlugin() {
        return idPlugin;
    }

    public void setIdPlugin(String idPlugin) {
        this.idPlugin = idPlugin;
    }
}

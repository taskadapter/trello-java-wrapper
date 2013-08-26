package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment extends TrelloEntity {

    private String id;
    private int bytes;
    private Date date;
    private String idMember;
    private boolean isUpload;
    private String mimeType;
    private String name;
    private List<Preview> previews;
    private String url;

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

    public boolean getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(boolean upload) {
        isUpload = upload;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Preview> getPreviews() {
        return previews;
    }

    public void setPreviews(List<Preview> previews) {
        this.previews = previews;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Preview {
        private int width;
        private int height;
        private String url;
        private String _id;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}

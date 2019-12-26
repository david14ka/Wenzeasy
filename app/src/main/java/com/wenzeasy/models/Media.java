package com.wenzeasy.models;

import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("id")

    private String id;
    @SerializedName("name")

    private String name;
    @SerializedName("url")

    private String url;
    @SerializedName("thumb")

    private String thumb;
    @SerializedName("icon")

    private String icon;
    @SerializedName("size")

    private String size;

    public Media() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

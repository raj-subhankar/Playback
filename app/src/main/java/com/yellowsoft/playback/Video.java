package com.yellowsoft.playback;

/**
 * Created by subhankar on 11/23/2016.
 */

public class Video {
    private String title, thumbnailUrl;

    public Video() {
    }

    public Video(String name, String thumbnailUrl) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

}
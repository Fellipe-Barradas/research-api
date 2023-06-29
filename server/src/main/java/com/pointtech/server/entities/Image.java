package com.pointtech.server.entities;

public class Image {
    private String imgURL;
    private String alt;

    public Image() {

    }

    public Image(String imgURL, String alt) {
        this.imgURL = imgURL;
        this.alt = alt;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

}

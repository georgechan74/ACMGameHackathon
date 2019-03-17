package com.example.acmgamehackathon.menu;


public class Character {
    public Character(String name, int firstImage, int secondImage, int nameImage, String urlImage, String urlName) {
        this.name = name;
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.nameImage = nameImage;
        this.urlImage = urlImage;
        this.urlName = urlName;
    }

    private String urlImage;
    private String urlName;
    private String name;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public int getNameImage() {
        return nameImage;
    }

    public void setNameImage(int nameImage) {
        this.nameImage = nameImage;
    }

    private int nameImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(int firstImage) {
        this.firstImage = firstImage;
    }

    public int getSecondImage() {
        return secondImage;
    }

    public void setSecondImage(int secondImage) {
        this.secondImage = secondImage;
    }

    private int firstImage;
    private int secondImage;

}

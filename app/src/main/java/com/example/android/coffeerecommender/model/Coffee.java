package com.example.android.coffeerecommender.model;

import com.google.gson.annotations.SerializedName;

public class Coffee {
    @SerializedName("Description")
    private String description;
    @SerializedName("Coffee_Name")
    private String name;
    @SerializedName("Coffee_Shop")
    private String shop;
    @SerializedName("Coffee_Type")
    private String type;
    @SerializedName("Image_URL")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

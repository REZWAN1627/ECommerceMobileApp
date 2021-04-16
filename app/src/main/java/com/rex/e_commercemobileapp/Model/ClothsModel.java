package com.rex.e_commercemobileapp.Model;

public class ClothsModel {
    private String Cloth_Image;
    private String Cloth_Name;
    private String Cloth_Price;
    private String Cloth_Rating;
    private String Cloth_color;
    private String Cloth_ID;

    public ClothsModel() {
    }

    public ClothsModel(String cloth_Image, String cloth_Name, String cloth_Price, String cloth_Rating, String cloth_color, String cloth_ID) {
        Cloth_Image = cloth_Image;
        Cloth_Name = cloth_Name;
        Cloth_Price = cloth_Price;
        Cloth_Rating = cloth_Rating;
        Cloth_color = cloth_color;
        Cloth_ID = cloth_ID;
    }

    public String getCloth_Image() {
        return Cloth_Image;
    }

    public String getCloth_Name() {
        return Cloth_Name;
    }

    public String getCloth_Price() {
        return Cloth_Price;
    }

    public String getCloth_Rating() {
        return Cloth_Rating;
    }

    public String getCloth_color() {
        return Cloth_color;
    }

    public String getCloth_ID() {
        return Cloth_ID;
    }
}

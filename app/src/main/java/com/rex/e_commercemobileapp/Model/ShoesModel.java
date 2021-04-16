package com.rex.e_commercemobileapp.Model;

public class ShoesModel {
    private String Shoe_Color;
    private String Shoe_Image;
    private String Shoe_Name;
    private String Shoe_Price;
    private String Shoe_Rating;
    private String Shoe_ID;

    public ShoesModel(String shoe_Color, String shoe_Image, String shoe_Name, String shoe_Price, String shoe_Rating, String shoe_ID) {
        Shoe_Color = shoe_Color;
        Shoe_Image = shoe_Image;
        Shoe_Name = shoe_Name;
        Shoe_Price = shoe_Price;
        Shoe_Rating = shoe_Rating;
        Shoe_ID = shoe_ID;
    }

    public ShoesModel() {
    }

    public String getShoe_Color() {
        return Shoe_Color;
    }

    public String getShoe_Image() {
        return Shoe_Image;
    }

    public String getShoe_Name() {
        return Shoe_Name;
    }

    public String getShoe_Price() {
        return Shoe_Price;
    }

    public String getShoe_Rating() {
        return Shoe_Rating;
    }

    public String getShoe_ID() {
        return Shoe_ID;
    }
}

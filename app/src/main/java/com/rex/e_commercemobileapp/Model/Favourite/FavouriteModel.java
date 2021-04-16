package com.rex.e_commercemobileapp.Model.Favourite;

public class FavouriteModel {

    private String Product_ID;
    private String Product_Image;
    private String Product_Name;
    private String Product_Price;
    private String Product_Rating;
    private String Product_Color;

    public FavouriteModel(String product_ID, String product_Image, String product_Name, String product_Price, String product_Rating, String product_Color) {
        Product_ID = product_ID;
        Product_Image = product_Image;
        Product_Name = product_Name;
        Product_Price = product_Price;
        Product_Rating = product_Rating;
        Product_Color = product_Color;
    }

    public FavouriteModel() {
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public String getProduct_Image() {
        return Product_Image;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public String getProduct_Price() {
        return Product_Price;
    }

    public String getProduct_Rating() {
        return Product_Rating;
    }

    public String getProduct_Color() {
        return Product_Color;
    }
}

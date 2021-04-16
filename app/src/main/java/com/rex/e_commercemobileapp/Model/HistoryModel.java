package com.rex.e_commercemobileapp.Model;

public class HistoryModel {

    private String Product_Color;
    private String Product_Date;
    private String Product_Image;
    private String Product_Name;
    private String Product_Price;
    private String Product_Size;
    private String Product_Time;

    public HistoryModel(String product_Color, String product_Date, String product_Image, String product_Name, String product_Price, String product_Size, String product_Time) {
        Product_Color = product_Color;
        Product_Date = product_Date;
        Product_Image = product_Image;
        Product_Name = product_Name;
        Product_Price = product_Price;
        Product_Size = product_Size;
        Product_Time = product_Time;
    }

    public HistoryModel() {
    }

    public String getProduct_Color() {
        return Product_Color;
    }

    public String getProduct_Date() {
        return Product_Date;
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

    public String getProduct_Size() {
        return Product_Size;
    }

    public String getProduct_Time() {
        return Product_Time;
    }
}

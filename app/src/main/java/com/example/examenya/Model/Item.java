package com.example.examenya.Model;

import android.graphics.Bitmap;

public class Item {
    public Item(Bitmap Image, String Title) {
        this.Image = Image;

        this.Title = Title;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public  String Title;
    public Bitmap Image;


}

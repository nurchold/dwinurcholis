package com.example.dwi_n.sampleapplication.Model;

import java.io.Serializable;
import java.util.List;

public class CatDetails implements Serializable{
    private String type;
    private List<String> imageList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}

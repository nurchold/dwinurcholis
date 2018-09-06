package com.example.dwi_n.sampleapplication.Model;

import java.io.Serializable;
import java.util.List;

public class CatImageList implements Serializable{
    private List<CatDetails> catImageList;

    public List<CatDetails> getCatImageList() {
        return catImageList;
    }

    public void setCatImageList(List<CatDetails> catImageList) {
        this.catImageList = catImageList;
    }
}

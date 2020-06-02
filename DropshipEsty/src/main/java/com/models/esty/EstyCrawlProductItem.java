/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.esty;

/**
 *
 * @author duy.phan
 */
public class EstyCrawlProductItem {
    public String id;
    public String title;
    public String imageUrl;
    public String detailUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
    
    

    @Override
    public String toString() {
        return id + ", " + title + ", " + imageUrl; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

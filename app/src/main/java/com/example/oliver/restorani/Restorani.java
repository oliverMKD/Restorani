package com.example.oliver.restorani;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Oliver on 12/18/2017.
 */

public class Restorani implements Serializable {

    String logo;
    String city;
    String name;
    String rating;
    ArrayList<Meni> menu;

    public Restorani() {
    }

    public Restorani(String logo, String city, String name, String rating) {
        this.logo = logo;
        this.city = city;
        this.name = name;
        this.rating = rating;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

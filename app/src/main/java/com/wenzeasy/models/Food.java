package com.wenzeasy.models;

import java.util.List;

public class Food {
    private String id;
    private String name;
    private double price;
    private double discountPrice;
    private Media image;
    private String description;
    private String ingredients;
    private String weight;
    private boolean featured;
    private Restaurant restaurant;
    private Category category;
    private List<Extra> extras;
    private List<Review> foodReviews;
    private List<Nutrition> nutritions;
}

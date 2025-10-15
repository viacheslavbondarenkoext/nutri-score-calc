package com.nutriscorecalc.logic.dto;

public class Product {
    public Long id;
    public String name;
    public double energyKj; // per 100g
    public double sugar; // g per 100g
    public double saturatedFat; // g per 100g
    public double sodium; // mg per 100g
    public double fiber; // g per 100g
    public double protein; // g per 100g
    public double fruitsPercentage; // 0-100
    public String nutriScore; // computed, optional

    public Product() {}

    public Product(Long id, String name, double energyKj, double sugar, double saturatedFat, double sodium, double fiber, double protein, double fruitsPercentage) {
        this.id = id;
        this.name = name;
        this.energyKj = energyKj;
        this.sugar = sugar;
        this.saturatedFat = saturatedFat;
        this.sodium = sodium;
        this.fiber = fiber;
        this.protein = protein;
        this.fruitsPercentage = fruitsPercentage;
    }
}


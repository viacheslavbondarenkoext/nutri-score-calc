package com.nutriscorecalc.database;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public double energyKj;
    public double sugar;
    public double saturatedFat;
    public double sodium;
    public double fiber;
    public double protein;
    public double fruitsPercentage;

    public ProductEntity() {}

    public ProductEntity(String name, double energyKj, double sugar, double saturatedFat, double sodium, double fiber, double protein, double fruitsPercentage) {
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


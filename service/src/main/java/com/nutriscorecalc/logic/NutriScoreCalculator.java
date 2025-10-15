package com.nutriscorecalc.logic;

import com.nutriscorecalc.logic.dto.Product;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;

@Named
@ApplicationScoped
public class NutriScoreCalculator {

    // Simplified nutri-score algorithm for demonstration purposes
    public String calculate(Product p) {
        int negative = 0;
        negative += pointsForEnergy(p.energyKj);
        negative += pointsForSugar(p.sugar);
        negative += pointsForSaturatedFat(p.saturatedFat);
        negative += pointsForSodium(p.sodium);

        int positive = 0;
        positive += pointsForFruits(p.fruitsPercentage);
        positive += pointsForFiber(p.fiber);
        positive += pointsForProtein(p.protein);

        int score = negative - positive;

        if (score <= 0) return "A";
        if (score <= 2) return "B";
        if (score <= 10) return "C";
        if (score <= 18) return "D";
        return "E";
    }

    private int pointsForEnergy(double kj) {
        if (kj <= 335) return 0;
        if (kj <= 670) return 1;
        if (kj <= 1005) return 2;
        if (kj <= 1340) return 3;
        if (kj <= 1675) return 4;
        if (kj <= 2010) return 5;
        if (kj <= 2345) return 6;
        if (kj <= 2680) return 7;
        if (kj <= 3015) return 8;
        if (kj <= 3350) return 9;
        return 10;
    }

    private int pointsForSugar(double sugar) {
        if (sugar <= 4.5) return 0;
        if (sugar <= 9) return 1;
        if (sugar <= 13.5) return 2;
        if (sugar <= 18) return 3;
        if (sugar <= 22.5) return 4;
        if (sugar <= 27) return 5;
        if (sugar <= 31) return 6;
        if (sugar <= 36) return 7;
        if (sugar <= 40) return 8;
        if (sugar <= 45) return 9;
        return 10;
    }

    private int pointsForSaturatedFat(double satFat) {
        if (satFat <= 1) return 0;
        if (satFat <= 2) return 1;
        if (satFat <= 3) return 2;
        if (satFat <= 4) return 3;
        if (satFat <= 5) return 4;
        return 5;
    }

    private int pointsForSodium(double sodium) {
        // sodium in mg
        if (sodium <= 90) return 0;
        if (sodium <= 180) return 1;
        if (sodium <= 270) return 2;
        if (sodium <= 360) return 3;
        if (sodium <= 450) return 4;
        return 5;
    }

    private int pointsForFruits(double perc) {
        if (perc >= 80) return 5;
        if (perc >= 60) return 2;
        if (perc >= 40) return 1;
        return 0;
    }

    private int pointsForFiber(double fiber) {
        if (fiber <= 0.9) return 0;
        if (fiber <= 1.9) return 1;
        if (fiber <= 2.8) return 2;
        return 3;
    }

    private int pointsForProtein(double protein) {
        if (protein <= 1.6) return 0;
        if (protein <= 3.2) return 1;
        if (protein <= 4.8) return 2;
        return 3;
    }
}


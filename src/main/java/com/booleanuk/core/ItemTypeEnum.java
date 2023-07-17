package com.booleanuk.core;


public enum ItemTypeEnum {
    BGLO("Bagel", "Onion", 0.49),
    BGLP("Bagel", "Plain", 0.39),
    BGLE("Bagel", "Everything", 0.49),
    BGLS("Bagel", "Sesame", 0.49),
    COFB("Coffee", "Black", 0.99),
    COFW("Coffee", "White", 1.19),
    COFC("Coffee", "Capuccino", 1.29),
    COFL("Coffee", "Lattee", 1.29),
    FILB("Filling", "Bacon", 0.12),
    FILE("Filling", "Egg", 0.12),
    FILC("Filling", "Cheese", 0.12),
    FILX("Filling", "Cream Cheese", 0.12),
    FILS("Filling", "Smoked Salmon", 0.12),
    FILH("Filling", "Ham", 0.12);


    private final String name;
    private final String variant;
    private final double price;


    ItemTypeEnum(String name, String variant, double price) {
        this.name = name;
        this.variant = variant;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getVariant() {
        return variant;
    }

    public double getPrice() {
        return price;
    }
}

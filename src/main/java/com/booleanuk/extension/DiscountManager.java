package com.booleanuk.extension;

import java.util.HashMap;

public class DiscountManager {

    private HashMap<ItemTypeEnum, double[]> discountMap;

    private HashMap<Item, Double> finalPrize = new HashMap<>();
    private Item coffee;

    public DiscountManager() {
        discountMap = initializeDiscounts();
    }

    private HashMap<ItemTypeEnum, double[]> initializeDiscounts() {
        HashMap<ItemTypeEnum, double[]> discounts = new HashMap<>();
        discounts.put(ItemTypeEnum.BGLO, new double[] { 6, 2.49 });
        discounts.put(ItemTypeEnum.BGLP, new double[] { 12, 3.99 });
        discounts.put(ItemTypeEnum.BGLE, new double[] { 6, 2.49 });
        discounts.put(ItemTypeEnum.COFB, new double[] { 1, 1.25 });
        return discounts;
    }

    public double calculateDiscount(Basket basket) {
        double totalDiscount = 0;
        int coffeeCount = 0;
        int undiscountedBagelCount = 0;

        for (Item item : basket.getBasket()) {
            ItemTypeEnum itemType = item.getType();

            if (!discountMap.containsKey(itemType)) {
                continue;
            }

            if (itemType == ItemTypeEnum.COFB) {
                coffeeCount += item.getCount();
                this.coffee = item;
                continue;
            }

            double[] discount = discountMap.get(itemType);

            if (!hasMinimumCount(item, discount) && itemType.getName() == "Bagel") {
                undiscountedBagelCount += item.getCount();
                continue;
            }

            int discountMultiplier = calculateDiscountMultiplier(item, discount);
            double remainingCount = item.getCount() - (discount[0] * discountMultiplier);
            System.out.println(item);
            this.finalPrize.put(item, calculateDiscountAmount(discount, discountMultiplier));
            totalDiscount += calculateDiscountAmount(discount, discountMultiplier);

            if (remainingCount > 0 && itemType.getName() == "Bagel") {
                undiscountedBagelCount += remainingCount;
            }
        }

        if (undiscountedBagelCount > 0 && coffeeCount > 0) {
            totalDiscount += calculateCoffeeDiscount(coffeeCount);
            this.finalPrize.put(this.coffee, Double.parseDouble(calculateCoffeeDiscount(coffeeCount) + ""));
        }

        totalDiscount = roundAvoid(totalDiscount, 2);

        return totalDiscount;
    }

    private boolean hasMinimumCount(Item item, double[] discount) {
        return item.getCount() >= discount[0];
    }

    private int calculateDiscountMultiplier(Item item, double[] discount) {
        return (int) (item.getCount() / discount[0]);
    }

    private double calculateDiscountAmount(double[] discount, int discountMultiplier) {
        return discount[1] * discountMultiplier;
    }

    private double calculateCoffeeDiscount(int coffeeCount) {
        return coffeeCount * discountMap.get(ItemTypeEnum.COFB)[1];
    }

    private static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public HashMap<Item, Double> getFinalPrize() {
        return this.finalPrize;
    }

    public  HashMap<Item, Double> discountAmounts(){
        HashMap<Item, Double> discounts = new HashMap<>();
        finalPrize.forEach((key, value)->{
            double discount = discountMap.get(key.getType())[0] * key.getType().getPrice() - value;
            if (discount>0){
                discounts.put(key, discount);
            }
        });
        return discounts;
    }
}

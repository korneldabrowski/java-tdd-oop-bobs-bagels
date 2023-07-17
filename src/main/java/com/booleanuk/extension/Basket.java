package com.booleanuk.extension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Basket implements Receipt {

    private static final int DEFAULT_CAPACITY = 10;

    private List<Item> basket;
    private int capacity = DEFAULT_CAPACITY;
    private double totalCost;

    private double discountOnCoffee;

    public Basket() {
        basket = new ArrayList<Item>();
    }

    public Basket(int capacity) {
        this.capacity = capacity;
        basket = new ArrayList<Item>();
    }

    public List<Item> getBasket() {
        return basket;
    }

    public void addItem(ItemTypeEnum itemType) {
        addItem(itemType, 1);
    }

    public void addItem(ItemTypeEnum itemType, int count) {
        if (!checkBasket() || count > checkCapacity()) {
            System.out.println("Basket is full");
            return;
        }

        Optional<Item> existingItem = findItemByType(itemType);

        existingItem.ifPresent(item -> updateItemCount(itemType, count));

        if (!existingItem.isPresent()) {
            basket.add(new Item(itemType, count));
        }

        totalCost += (itemType.getPrice() * count);
    }

    public void removeItem(ItemTypeEnum itemType) {
        Optional<Item> existingItem = findItemByType(itemType);

        existingItem.ifPresent(item -> {
            if (item.getCount() > 1) {
                updateItemCount(itemType, -1);
            } else {
                basket.remove(item);
            }
        });

        totalCost -= itemType.getPrice();
    }

    public int getBasketSize() {
        return basket.stream().mapToInt(Item::getCount).sum();
    }

    public int checkCapacity() {
        return capacity - getBasketSize();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    private boolean checkBasket() {
        return getBasketSize() < this.capacity;
    };

    private void updateItemCount(ItemTypeEnum itemType, int countChange) {
        Optional<Item> existingItem = findItemByType(itemType);

        existingItem.ifPresent(item -> item.setCount(item.getCount() + countChange));

        if (!existingItem.isPresent()) {
            int newCount = countChange > 0 ? countChange : 1;
            basket.add(new Item(itemType, newCount));
        }
    }

    private Optional<Item> findItemByType(ItemTypeEnum itemType) {
        return basket.stream()
                .filter(item -> item.getType() == itemType)
                .findFirst();
    }

    public double bagelsWithoutDiscount(){
        return basket.stream().filter(i-> i.getType().getName().equals("Bagel")).mapToDouble(Item::getWithoutDiscount).sum();
    }

    private double totalCostWithDiscountAndCoffee(){
        return basket.stream().mapToDouble(Item::getPrice).sum();
    }

    public double totalCostWithDiscount(){
        this.discountOnCoffee = 0.0;
        this.basket.stream().forEach(Item::update);
        double costWithoutCoffeeDisc = totalCostWithDiscountAndCoffee();
        double discountOnCoffee = discountOnCoffee();
        costWithoutCoffeeDisc -= discountOnCoffee;

        return Math.round(costWithoutCoffeeDisc * 100.0) / 100.0;
    }

    private double discountOnCoffee(){

        double discountOnCoffee = 0.0;
        Optional<Item> coffeeBlackItem = basket.stream()
                .filter(item -> item.getType() == ItemTypeEnum.COFB)
                .findFirst();
        if (coffeeBlackItem.isPresent()){
            // apply discount to the cheapest bagels
            Optional<Item> bagelPlainItem = basket.stream()
                    .filter(item -> item.getType() == ItemTypeEnum.BGLP)
                    .findFirst();
            if(bagelPlainItem.isPresent()){
                discountOnCoffee += 0.13 * Math.min(bagelPlainItem.get().getWithoutDiscount(),  coffeeBlackItem.get().getWithoutDiscount());
                int bagelWithoutDiscount = bagelPlainItem.get().getWithoutDiscount();
                bagelPlainItem.get().setWithoutDiscount(Math.max(bagelWithoutDiscount - coffeeBlackItem.get().getWithoutDiscount(), 0));
                coffeeBlackItem.get().setWithoutDiscount(Math.max(coffeeBlackItem.get().getWithoutDiscount() - bagelWithoutDiscount, 0));
            }
            // apply to the rest

            for (ItemTypeEnum bagelType : ItemTypeEnum.values()) {
                if (bagelType.name().startsWith("BGL")) {
                    Optional<Item> bagelItem = basket.stream()
                            .filter(item -> item.getType() == bagelType)
                            .findFirst();

                    if (bagelItem.isPresent()) {
                        discountOnCoffee += 0.23 * Math.min(bagelItem.get().getWithoutDiscount(), coffeeBlackItem.get().getWithoutDiscount());
                        int bagelDiscountCount = bagelItem.get().getWithoutDiscount();
                        bagelItem.get().setWithoutDiscount(Math.max(bagelDiscountCount - coffeeBlackItem.get().getWithoutDiscount(), 0));
                        coffeeBlackItem.get().setWithoutDiscount(Math.max(coffeeBlackItem.get().getWithoutDiscount() - bagelDiscountCount, 0));
                    }
                }
            }
        }
        discountOnCoffee = Math.round(discountOnCoffee * 100.0) / 100.0;

        this.discountOnCoffee = discountOnCoffee;
        return discountOnCoffee;
    }

    public double getDiscountOnCoffee() {
        return discountOnCoffee;
    }


}
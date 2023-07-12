package com.booleanuk.core;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private static final int DEFAULT_CAPACITY = 10;

    private List<Item> basket;
    private int capacity = DEFAULT_CAPACITY;
    private int totalCost;

    public Basket() {
        basket = new ArrayList<Item>();
    }

    public Basket(int capacity) {
        this.capacity = capacity;
        basket = new ArrayList<Item>();
    }

    public List<Item> getList() {
        return basket;
    }

    public void addBagel(Item item) {
        if (checkBasket()) {
            basket.add(item);
            totalCost += item.getCost();
        } else {
            System.out.println("Basket is full");
        }
    }

    public void removeBagel(Item item) {
        if (basket.contains(item)) {
            basket.remove(item);
            totalCost -= item.getCost();
        } else {
            System.out.println("Bagel not in basket");
        }
    }

    public int getBasketSize() {
        return basket.size();
    }

    public int checkCapacity() {
        return capacity - basket.size();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getTotalCost() {
        return totalCost;
    }

    private boolean checkBasket() {
        return basket.size() == 0;
    };

}

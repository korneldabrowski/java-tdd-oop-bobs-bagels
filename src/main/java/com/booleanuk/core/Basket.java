package com.booleanuk.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Basket {

    private static final int DEFAULT_CAPACITY = 10;

    private List<Item> basket;
    private int capacity;
    private double totalCost;

    public Basket() {
        this(DEFAULT_CAPACITY);
    }

    public Basket(int capacity) {
        this.capacity = capacity;
        basket = new ArrayList<>();
    }

    public List<Item> getList() {
        return basket;
    }

    public void addBagel(ItemTypeEnum itemType) {
        if (checkBasket()) {
            Optional<Item> existingItem = basket.stream()
                    .filter(item -> item.getType().equals(itemType))
                    .findFirst();

            if (existingItem.isPresent()) {
                existingItem.get().setCount(existingItem.get().getCount() + 1);
            } else {
                basket.add(new Item(itemType, 1));
            }
            totalCost += itemType.getPrice();
        } else {
            System.out.println("Basket is full");
        }
    }

    public void removeBagel(ItemTypeEnum itemType) {
        Optional<Item> existingItem = basket.stream()
                .filter(item -> item.getType().equals(itemType))
                .findFirst();
        if (existingItem.isPresent()) {
            if(existingItem.get().getCount() > 1) {
                existingItem.get().setCount(existingItem.get().getCount() - 1);
            }else{
                basket.remove(existingItem.get());
            }
            totalCost -= itemType.getPrice();

        } else {
            System.out.println("Bagel not in basket");
        }

    }

    public int getBasketSize() {
        return basket.stream().mapToInt(Item::getCount).sum();
    }

    public int checkCapacity() {
        return capacity - getBasketSize();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        adjustBasketCapacity();
    }

    public int getTotalCost() {
        return totalCost;
    }

    private boolean checkBasket() {
        return getBasketSize() < this.capacity;
    };

    private void adjustBasketCapacity() {
        basket = basket.stream()
                .limit(capacity)
                .collect(Collectors.toList());
    }
}

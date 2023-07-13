package com.booleanuk.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Basket {

    private static final int DEFAULT_CAPACITY = 10;

    private List<Item> basket;
    private int capacity = DEFAULT_CAPACITY;
    private double totalCost;

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
        if (!checkBasket()) {
            System.out.println("Basket is full");
            return;
        }

        Optional<Item> existingItem = findItemByType(itemType);

        existingItem.ifPresent(item -> updateItemCount(itemType, 1));

        if (!existingItem.isPresent()) {
            basket.add(new Item(itemType, 1));
        }

        totalCost += itemType.getPrice();
    }

    public void addItem(ItemTypeEnum itemType, int count) {
        if (!checkBasket()) {
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

}
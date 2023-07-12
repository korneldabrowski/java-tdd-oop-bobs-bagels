package com.booleanuk.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return new ArrayList<>(basket); // Return a copy of the list to avoid external modifications
    }

    public void addBagel(ItemTypeEnum itemType) {
        if (!checkBasketCapacity()) {
            System.out.println("Basket is full");
            return;
        }

        Optional<Item> existingItem = findItemByType(itemType);

        existingItem.ifPresentOrElse(
                item -> updateItemCount(item, item.getCount() + 1),
                () -> basket.add(new Item(itemType, 1)));

        totalCost += itemType.getPrice();
    }

    public void removeBagel(ItemTypeEnum itemType) {
        Optional<Item> existingItem = findItemByType(itemType);

        existingItem.ifPresent(item -> {
            if (item.getCount() > 1) {
                updateItemCount(item, item.getCount() - 1);
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
        adjustBasketCapacity();
    }

    public double getTotalCost() {
        return totalCost;
    }

    private boolean checkBasketCapacity() {
        return getBasketSize() < capacity;
    }

    private void updateItemCount(Item item, int countChange) {
        item.setCount(countChange);
    }

    private Optional<Item> findItemByType(ItemTypeEnum itemType) {
        return basket.stream()
                .filter(item -> item.getType() == itemType)
                .findFirst();
    }

    private void adjustBasketCapacity() {
        basket = basket.stream()
                .limit(capacity)
                .collect(Collectors.toList());
    }
}

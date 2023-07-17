package com.booleanuk.core;



public class Item {
    private ItemTypeEnum type;
    private int count;

    public Item(ItemTypeEnum type, int count) {
        this.type = type;
        this.count = count;
    }

    public Item() {
    }

    public ItemTypeEnum getType() {
        return type;
    }

    public void setType(ItemTypeEnum type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

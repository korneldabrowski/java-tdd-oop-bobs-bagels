package com.booleanuk.extension;


public class Item {
    private ItemTypeEnum type;
    private int count;
    private double price;
    private int withoutDiscount;

    public Item(ItemTypeEnum type, int count) {
        this.type = type;
        this.setCount(count);
    }

    public void setCount(int count){
       this.count = count;
       this.update();
    }

    public void update(){
        if (this.type.equals(ItemTypeEnum.BGLE) | this.type.equals(ItemTypeEnum.BGLO)){
            this.withoutDiscount = count % 6;
            this.price = Math.floorDiv(count, 6) * 2.49 + this.withoutDiscount * this.type.getPrice();
        } else if(this.type.equals(ItemTypeEnum.BGLP)){
            this.withoutDiscount = count % 12;
            this.price = Math.floorDiv(count, 12) * 3.99 + this.withoutDiscount * this.type.getPrice();
        }else{
            this.withoutDiscount = count;
            this.price = count * this.type.getPrice();
        }
    }

    public Item(ItemTypeEnum type, int count, double price, int withoutDiscount) {
        this.type = type;
        this.count = count;
        this.price = price;
        this.withoutDiscount = withoutDiscount;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWithoutDiscount() {
        return withoutDiscount;
    }

    public void setWithoutDiscount(int withoutDiscount) {
        this.withoutDiscount = withoutDiscount;
    }
}

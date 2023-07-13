package com.booleanuk.extension;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
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

    public static void main(String[] args) {
        Item item = new Item(ItemTypeEnum.BGLE, 7);
        System.out.println(item);
        Item item1 = new Item(ItemTypeEnum.COFB, 7);
        System.out.println(item1);
    }

}

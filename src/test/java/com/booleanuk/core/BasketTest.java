package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasketTest {

    Basket basket = new Basket();

    @Test
    public void testAddBagel() {

        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addItem(item.BGLE);

        Assertions.assertEquals(1, basket.getBasketSize());
    }

    @Test
    public void testGetList() {
        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addItem(item.BGLE);
        basket.addItem(item.BGLP);
        basket.addItem(item.BGLO);
        Assertions.assertEquals(3, basket.getBasketSize());
    }

    @Test
    public void testRemoveBagel() {
        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addItem(item.BGLE);
        basket.addItem(item.BGLP);
        basket.addItem(item.BGLO);
        basket.removeItem(item);

        Assertions.assertEquals(2, basket.getBasketSize());
    }

    @Test
    public void testRemoveBagelWhenOne() {
        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addItem(item.BGLE);
        basket.removeItem(item);
        Assertions.assertEquals(0, basket.getBasketSize());
    }

    @Test
    public void testCheckBasketOverflow() {

        basket.setCapacity(2);

        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addItem(item.BGLE);
        basket.addItem(item.BGLP);
        basket.addItem(item.BGLO);

        Assertions.assertEquals(2, basket.getBasketSize());
    }

    @Test
    public void testCheckCapacity() {

        basket.setCapacity(2);

        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addItem(item.BGLE);
        basket.addItem(item.BGLP);
        basket.addItem(item.BGLO);

        Assertions.assertEquals(0, basket.checkCapacity());
    }

    @Test
    public void testTotalCost() {

        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addItem(item.BGLE);
        basket.addItem(item.BGLP);
        basket.addItem(item.BGLO);
        basket.addItem(item.BGLO);
        basket.removeItem(item.BGLO);

        Assertions.assertEquals(1.37, basket.getTotalCost());
    }

    @Test
    public void testAddBagelWithCount(){
        basket.addItem(ItemTypeEnum.BGLP, 3);
        Assertions.assertEquals(3, basket.getBasketSize());
    }

    @Test
    public void testAddBagelWithCountWhenExist(){
        basket.addItem(ItemTypeEnum.BGLP, 3);
        basket.addItem(ItemTypeEnum.BGLP, 2);
        Assertions.assertEquals(basket.getBasket().size(), 1);
        Assertions.assertEquals(5, basket.getBasketSize());
    }

}

package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasketTest {

    Basket basket = new Basket();

    @Test
    public void testAddBagel() {

        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addBagel(item.BGLE);

        Assertions.assertEquals(1, basket.getBasketSize());
    }

    @Test
    public void testGetList() {
        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addBagel(item.BGLE);
        basket.addBagel(item.BGLP);
        basket.addBagel(item.BGLO);
        Assertions.assertEquals(3, basket.getBasketSize());
    }

    @Test
    public void testRemoveBagel() {
        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addBagel(item.BGLE);
        basket.addBagel(item.BGLP);
        basket.addBagel(item.BGLO);
        basket.removeBagel(item);

        Assertions.assertEquals(2, basket.getBasketSize());
    }

    @Test
    public void testCheckBasketOverflow() {

        basket.setCapacity(2);

        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addBagel(item.BGLE);
        basket.addBagel(item.BGLP);
        basket.addBagel(item.BGLO);

        Assertions.assertEquals(2, basket.getBasketSize());
    }

    @Test
    public void testCheckCapacity() {

        basket.setCapacity(2);

        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addBagel(item.BGLE);
        basket.addBagel(item.BGLP);
        basket.addBagel(item.BGLO);

        Assertions.assertEquals(0, basket.checkCapacity());
    }

    @Test
    public void testTotalCost() {

        ItemTypeEnum item = ItemTypeEnum.BGLE;
        basket.addBagel(item.BGLE);
        basket.addBagel(item.BGLP);
        basket.addBagel(item.BGLO);
        basket.addBagel(item.BGLO);
        basket.removeBagel(item.BGLO);

        Assertions.assertEquals(1.37, basket.getTotalCost());
    }

}

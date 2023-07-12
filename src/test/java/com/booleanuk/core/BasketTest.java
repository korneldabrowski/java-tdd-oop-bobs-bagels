package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasketTest {

    Basket basket = new Basket();

    @Test
    public void testAddBagel() {

        Item item = new Item("Bagel", 1);
        basket.addBagel(item);
        Assertions.assertEquals(1, basket.getBasketSize());
    }

    @Test
    public void testGetList() {
        Item item = new Item("Bagel", 1);
        basket.addBagel(item);
        basket.addBagel(item);
        basket.addBagel(item);

        Assertions.assertEquals(3, basket.getBasketSize());
    }

    @Test
    public void testRemoveBagel() {
        Item item = new Item("Bagel", 1);
        basket.addBagel(item);
        basket.addBagel(item);
        basket.addBagel(item);
        basket.removeBagel(item);

        Assertions.assertEquals(2, basket.getBasketSize());
    }

    @Test
    public void testCheckBasketOverflow() {

        basket.setCapacity(2);

        Item item = new Item("Bagel", 1);

        basket.addBagel(item);
        basket.addBagel(item);
        basket.addBagel(item);

        Assertions.assertEquals(2, basket.getBasketSize());
    }

    @Test
    public void testCheckCapacity() {

        basket.setCapacity(2);

        Item item = new Item("Bagel", 1);

        basket.addBagel(item);
        basket.addBagel(item);

        Assertions.assertEquals(0, basket.checkCapacity());
    }

    @Test
    public void testTotalCost() {

        Item item = new Item("Bagel", 1);

        basket.addBagel(item);
        basket.addBagel(item);
        basket.addBagel(item);

        Assertions.assertEquals(3, basket.getTotalCost());
    }

}

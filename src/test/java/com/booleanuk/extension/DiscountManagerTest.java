package com.booleanuk.extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DiscountManagerTest {

    Basket basket = new Basket();
    DiscountManager discountManager = new DiscountManager();

    @Test
    public void testCalculateDiscountWith5Bagels() {

        for (int i = 0; i < 5; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        Assertions.assertEquals(0, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWith6Bagels() {

        for (int i = 0; i < 6; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        Assertions.assertEquals(2.49, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWith7Bagels() {

        for (int i = 0; i < 7; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        Assertions.assertEquals(2.49, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWith13Bagels() {

        basket.setCapacity(13);

        for (int i = 0; i < 13; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        Assertions.assertEquals(4.98, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWith26Bagels() {

        basket.setCapacity(26);

        for (int i = 0; i < 24; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        Assertions.assertEquals(9.96, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWithCofeAndBagelAnd26bagels() {
        basket.setCapacity(30);

        for (int i = 0; i < 24; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        basket.addItem(ItemTypeEnum.COFB);
        basket.addItem(ItemTypeEnum.BGLE);

        Assertions.assertEquals(11.21, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWithCofeAndBagel() {

        basket.addItem(ItemTypeEnum.COFB);
        basket.addItem(ItemTypeEnum.BGLE);
        basket.addItem(ItemTypeEnum.FILB, 4);

        Assertions.assertEquals(1.25, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWith2CofeAndBagel3() {

        basket.addItem(ItemTypeEnum.COFB);
        basket.addItem(ItemTypeEnum.COFB);
        basket.addItem(ItemTypeEnum.BGLE);
        basket.addItem(ItemTypeEnum.BGLE);
        basket.addItem(ItemTypeEnum.BGLE);

        Assertions.assertEquals(2.50, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWithNoItems() {
        Assertions.assertEquals(0, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWithOneCoffee() {
        basket.addItem(ItemTypeEnum.COFB);
        Assertions.assertEquals(0, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWithMultipleCoffees() {
        basket.addItem(ItemTypeEnum.COFB);
        basket.addItem(ItemTypeEnum.COFB);
        basket.addItem(ItemTypeEnum.COFB);
        Assertions.assertEquals(0, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWithDifferentBagelTypes() {
        basket.addItem(ItemTypeEnum.BGLO);
        basket.addItem(ItemTypeEnum.BGLP);
        basket.addItem(ItemTypeEnum.BGLE);
        Assertions.assertEquals(0, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testCalculateDiscountWith14Bagels2Cofees() {
        basket.setCapacity(20);
        basket.addItem(ItemTypeEnum.BGLE, 14);
        basket.addItem(ItemTypeEnum.COFB, 2);

        Assertions.assertEquals(7.48, discountManager.calculateDiscount(basket));
    }

    @Test
    public void testPartialCost(){
        basket.setCapacity(20);
        basket.addItem(ItemTypeEnum.BGLE, 14);
        basket.addItem(ItemTypeEnum.COFB, 2);

        discountManager.calculateDiscount(basket);
        System.out.println(discountManager.getFinalPrize());

    }

}

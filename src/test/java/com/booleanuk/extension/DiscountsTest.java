package com.booleanuk.extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiscountsTest {

    Basket basket = new Basket();

    @Test
    public void testCalculateDiscountWith5Bagels() {

        for (int i = 0; i < 5; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        Assertions.assertEquals(2.45, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWith6Bagels() {

        for (int i = 0; i < 6; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        Assertions.assertEquals(2.49, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWith7Bagels() {

        for (int i = 0; i < 7; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        Assertions.assertEquals(2.98, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWith13Bagels() {

        basket.setCapacity(14);

        for (int i = 0; i < 13; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        Assertions.assertEquals(5.47, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWith24Bagels() {

        basket.setCapacity(26);

        for (int i = 0; i < 24; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        Assertions.assertEquals(9.96, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWithCofeAndBagelAnd26bagels() {

        basket.setCapacity(30);

        for (int i = 0; i < 24; i++) {
            basket.addItem(ItemTypeEnum.BGLO);
        }

        basket.addItem(ItemTypeEnum.COFB);
        basket.addItem(ItemTypeEnum.BGLE);

        Assertions.assertEquals(11.21, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWithCoffeeAndBagelAndFilling() {

        basket.addItem(ItemTypeEnum.COFB);
        basket.addItem(ItemTypeEnum.BGLE);
        basket.addItem(ItemTypeEnum.FILB, 4);

        Assertions.assertEquals(1.73, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWith2CofeAndBagel3() {

        basket.addItem(ItemTypeEnum.COFB);
        basket.addItem(ItemTypeEnum.COFB);
        basket.addItem(ItemTypeEnum.BGLE);
        basket.addItem(ItemTypeEnum.BGLE);
        basket.addItem(ItemTypeEnum.BGLE);
        basket.addItem(ItemTypeEnum.COFL);

        Assertions.assertEquals(4.28, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWithNoItems() {
        Assertions.assertEquals(0, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWithOneCoffee() {
        basket.addItem(ItemTypeEnum.COFB);
        Assertions.assertEquals(0.99, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWithMultipleCoffeesAndBagels() {
        basket.addItem(ItemTypeEnum.COFB, 4);
        basket.addItem(ItemTypeEnum.BGLE, 2);
        basket.addItem(ItemTypeEnum.BGLP,2);
        Assertions.assertEquals(5, basket.totalCostWithDiscount());
    }

    @Test
    public void testPlainBagelFirst() {
        basket.addItem(ItemTypeEnum.COFB, 1);
        basket.addItem(ItemTypeEnum.BGLE, 1);
        basket.addItem(ItemTypeEnum.BGLP,1);
        Assertions.assertEquals(1.74, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWithDifferentBagelTypes() {
        basket.addItem(ItemTypeEnum.BGLO);
        basket.addItem(ItemTypeEnum.BGLP);
        basket.addItem(ItemTypeEnum.BGLE);
        Assertions.assertEquals(1.37, basket.totalCostWithDiscount());
    }

    @Test
    public void testCalculateDiscountWith14Bagels2Cofees() {

        basket.setCapacity(20);
        basket.addItem(ItemTypeEnum.BGLE, 14);
        basket.addItem(ItemTypeEnum.COFB, 3);

        Assertions.assertEquals(8.47, basket.totalCostWithDiscount());
    }

    @Test
    public void differentTypesOfBagels(){
        basket.setCapacity(50);
        basket.addItem(ItemTypeEnum.BGLS, 2);
        basket.addItem(ItemTypeEnum.BGLP, 15);
        basket.addItem(ItemTypeEnum.BGLE, 7);
        basket.addItem(ItemTypeEnum.COFB, 8);
        Assertions.assertEquals(15.96, basket.totalCostWithDiscount());
    }

}

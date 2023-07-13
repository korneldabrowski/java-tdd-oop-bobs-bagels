package com.booleanuk.extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class ReceiptTest {
    @Test
    public void receiptTest(){
               Basket basket = new Basket();
        basket.setCapacity(30);
        basket.addItem(ItemTypeEnum.BGLO, 2);
        basket.addItem(ItemTypeEnum.BGLP, 12);
        basket.addItem(ItemTypeEnum.BGLE, 6);
        basket.addItem(ItemTypeEnum.COFB, 3);
        String actualReceipt = basket.normalReceipt(basket);
        System.out.println(actualReceipt);
        Assertions.assertTrue(actualReceipt.contains("Onion Bagel         2   $0.98"));
        Assertions.assertTrue(actualReceipt.contains("Plain Bagel        12   $4.68"));
        Assertions.assertTrue(actualReceipt.contains("Everything Bagel    6   $2.94"));
        Assertions.assertTrue(actualReceipt.contains("Black Coffee        3   $2.97"));
    }
}

package com.booleanuk.extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ReceiptTest {
    @Test
    public void receiptTest(){
        String receipt = "    ~~~ Bob's Bagels ~~~\n" +
                "\n" +
                "    2021-03-16 21:37:44\n" +
                "\n" +
                "----------------------------\n" +
                "\n" +
                "Onion Bagel        2   £0.98\n" +
                "Plain Bagel        12  £3.99\n" +
                "Everything Bagel   6   £2.49\n" +
                "Coffee             3   £2.97\n" +
                "\n" +
                "----------------------------\n" +
                "\n" +
                "Total                 £10.43\n" +
                "\n" +
                "        Thank you\n" +
                "      for your order!";


        Basket basket = new Basket();
        basket.setCapacity(30);
        basket.addItem(ItemTypeEnum.BGLO, 2);
        basket.addItem(ItemTypeEnum.BGLP, 12);
        basket.addItem(ItemTypeEnum.BGLE, 6);
        basket.addItem(ItemTypeEnum.COFB, 3);
        String myReceipt = basket.normalReceipt(basket, LocalDateTime.parse("2021-03-16 21:37:44"));
        Assertions.assertEquals(myReceipt , receipt);
    }
}

package com.booleanuk.extension;

import java.time.LocalDateTime;

public interface Receipt {
    default String normalReceipt(Basket basket, LocalDateTime date){

        String receipt1 = "    ~~~ Bob's Bagels ~~~\n" +
                "\n" +
                "    2021-03-16 21:38:44\n" +
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
        String receipt ="    ~~~ Bob's Bagels ~~~\n" +
        "\n";
        receipt+="    " + LocalDateTime.now() +"\n" +



        return receipt;
    }
}

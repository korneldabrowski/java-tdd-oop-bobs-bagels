package com.booleanuk.extension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Receipt {
    default String normalReceipt(Basket basket){
        StringBuilder receiptBuilder = new StringBuilder();

        receiptBuilder.append("    ~~~ Bob's Bagels ~~~\n\n");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        receiptBuilder.append(now.format(formatter)).append("\n\n");

        receiptBuilder.append("----------------------------\n\n");

        for (Item item : basket.getBasket()) {
            String itemName = item.getType().getVariant() + " " + item.getType().getName();
            int count = item.getCount();
            double itemCost = item.getType().getPrice();
            //TODO ten normalny koszt
            double itemTotalCost = count * itemCost;
            receiptBuilder.append(String.format("%-18s %2d   $%.2f\n", itemName, count, itemTotalCost));
        }

        receiptBuilder.append("\n----------------------------\n\n");
        receiptBuilder.append(String.format("Total                 $%.2f\n\n", basket.getTotalCost()));
        receiptBuilder.append("        Thank you\n");
        receiptBuilder.append("      for your order!");

        return receiptBuilder.toString();
    }
}

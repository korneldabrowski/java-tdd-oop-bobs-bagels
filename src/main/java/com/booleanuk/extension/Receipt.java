package com.booleanuk.extension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

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
            double itemTotalCost = count * itemCost;
            receiptBuilder.append(String.format("%-18s %2d   $%.2f\n", itemName, count, itemTotalCost));
        }

        receiptBuilder.append("\n----------------------------\n\n");
        receiptBuilder.append(String.format("Total                 $%.2f\n\n", basket.getTotalCost()));
        receiptBuilder.append("        Thank you\n");
        receiptBuilder.append("      for your order!");

        return receiptBuilder.toString();
    }

    default String receiptWithDiscount(Basket basket) {
        DiscountManager discountManager = new DiscountManager();
        discountManager.calculateDiscount(basket);
        HashMap<Item, Double> finalPrize = discountManager.getFinalPrize();
        StringBuilder receiptBuilder = new StringBuilder();

        receiptBuilder.append("    ~~~ Bob's Bagels ~~~\n\n");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        receiptBuilder.append(now.format(formatter)).append("\n\n");

        receiptBuilder.append("----------------------------\n\n");

        double totalSavings = 0.0;

        for (Item item : basket.getBasket()) {
            String itemName = item.getType().getVariant() + " " + item.getType().getName();
            int count = item.getCount();
            double itemCost = item.getType().getPrice();
            double itemTotalCost = count * itemCost;

            System.out.println(finalPrize);
            System.out.println(item);

            double discountAmount = 0.0;
            if (finalPrize.containsKey(item)){
            discountAmount = item.getCount() * item.getType().getPrice() - finalPrize.get(item);}

            if (discountAmount > 0.0) {
                receiptBuilder.append(String.format("%-18s %2d   $%.2f\n", itemName, count, itemTotalCost));
                receiptBuilder.append(String.format("%-20s (-$%.2f)\n", "", discountAmount));
                totalSavings += discountAmount;
            } else {
                receiptBuilder.append(String.format("%-18s %2d   $%.2f\n", itemName, count, itemTotalCost));
            }
        }

        receiptBuilder.append("\n----------------------------\n\n");
        receiptBuilder.append(String.format("Total                 $%.2f\n\n", basket.getTotalCost() - totalSavings));
        receiptBuilder.append(String.format(" You saved a total of $%.2f\n", totalSavings));
        receiptBuilder.append("       on this shop\n\n");
        receiptBuilder.append("        Thank you\n");
        receiptBuilder.append("      for your order!");

        return receiptBuilder.toString();
    }


}

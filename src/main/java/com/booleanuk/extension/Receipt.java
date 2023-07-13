package com.booleanuk.extension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public interface Receipt {

    default String normalReceipt(Basket basket){
        StringBuilder receiptBuilder = this.begginning();

        for (Item item : basket.getBasket()) {
            String itemName = item.getType().getVariant() + " " + item.getType().getName();
            int count = item.getCount();
            double itemCost = item.getType().getPrice();
            double itemTotalCost = count * itemCost;
            receiptBuilder.append(String.format("%-18s %2d   $%.2f\n", itemName, count, itemTotalCost));
        }

        receiptBuilder = ending(receiptBuilder, false, 0.0, basket.getTotalCost());
        return receiptBuilder.toString();
    }

    default String receiptWithDiscount(Basket basket) {

        StringBuilder receiptBuilder = this.begginning();
        double total = basket.totalCostWithDiscount();

        double totalSavings = basket.getTotalCost() - total;

        for (Item item : basket.getBasket()) {
            String itemName = item.getType().getVariant() + " " + item.getType().getName();
            int count = item.getCount();
            double itemCost = item.getType().getPrice();
            double itemTotalCost = count * itemCost;

            double discountAmount = itemTotalCost - item.getPrice();
            if (item.getType().equals(ItemTypeEnum.COFB)){
                discountAmount = basket.getDiscountOnCoffee();
            }

            if (discountAmount > 0.0) {
                receiptBuilder.append(String.format("%-18s %2d   $%.2f\n", itemName, count, itemTotalCost));
                receiptBuilder.append(String.format("%-20s (-$%.2f)\n", "", discountAmount));
            } else {
                receiptBuilder.append(String.format("%-18s %2d   $%.2f\n", itemName, count, itemTotalCost));
            }
        }

        receiptBuilder = ending(receiptBuilder, true, totalSavings, total);

        return receiptBuilder.toString();
    }

    default StringBuilder begginning(){
        StringBuilder receiptBuilder = new StringBuilder();

        receiptBuilder.append("    ~~~ Bob's Bagels ~~~\n\n");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        receiptBuilder.append(now.format(formatter)).append("\n\n");

        receiptBuilder.append("----------------------------\n\n");
        return receiptBuilder;
    }

    default StringBuilder ending(StringBuilder receiptBuilder, boolean withDiscount, double totalSavings, double total){
        receiptBuilder.append("\n----------------------------\n\n");
        receiptBuilder.append(String.format("Total                 $%.2f\n\n", total));
        if (withDiscount){
            receiptBuilder.append(String.format(" You saved a total of $%.2f\n", totalSavings));
            receiptBuilder.append("       on this shop\n\n");
        }
        receiptBuilder.append("        Thank you\n");
        receiptBuilder.append("      for your order!");
        return receiptBuilder;
    }




}

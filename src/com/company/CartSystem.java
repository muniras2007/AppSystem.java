package com.company;

import java.util.Set;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CartSystem extends TheSystem {
    CartSystem() {
    }

    @Override
    public void display() {
        Set<String> keySet = getItemCollection().keySet();
        DecimalFormat dF = new DecimalFormat();
        dF.setMaximumFractionDigits(2);
        dF.setMinimumFractionDigits(2);
        System.out.println("Cart:");
        System.out.println("Name                 Description          Price      Quantity   Sub Total ");
        Double preTax = 0.0;
        Double tax = 0.0;
        Double total = 0.0;
        for (String string : keySet )
        {
            StringBuilder stringBuilder = new StringBuilder();
            Double subTotal = getItemCollection().get(string).getItemPrice()*getItemCollection().get(string).getQuantity();
            preTax = preTax + subTotal;

            stringBuilder.append(getItemCollection().get(string).getItemName()).append(whiteSpaceBuilder(20-getItemCollection().get(string).getItemName().length()));
            stringBuilder.append(getItemCollection().get(string).getItemDesc()).append(whiteSpaceBuilder(20-getItemCollection().get(string).getItemDesc().length()));
            stringBuilder.append(dF.format(getItemCollection().get(string).getItemPrice())).append(whiteSpaceBuilder(10-dF.format(getItemCollection().get(string).getItemPrice()).length()));
            stringBuilder.append(getItemCollection().get(string).getQuantity()).append(whiteSpaceBuilder(10-getItemCollection().get(string).getQuantity().toString().length()));
            stringBuilder.append(dF.format(subTotal)).append(whiteSpaceBuilder(10 - dF.format(subTotal).toString().length()));
            System.out.println(stringBuilder);
        }
        tax = preTax * .05;
        total = preTax + tax;
        System.out.println("Pre-tax Total "+whiteSpaceBuilder(7) + dF.format(preTax) +whiteSpaceBuilder(20-dF.format(preTax).toString().length()) );
        System.out.println("Tax "+whiteSpaceBuilder(17) + dF.format(tax) +whiteSpaceBuilder(20-dF.format(tax).toString().length()) );
        System.out.println("Total "+whiteSpaceBuilder(15) + dF.format(total) + whiteSpaceBuilder(20-dF.format(total).toString().length()) );

    }
    private String whiteSpaceBuilder (int wSpaceB)
    {
        StringBuffer whiteSpace = new StringBuffer();

        for (int i = 0; i < wSpaceB; i++) {
            whiteSpace.append(" ");
        }
        return whiteSpace.toString();
    }
}

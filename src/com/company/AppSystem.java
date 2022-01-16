package com.company;

import java.util.Set;
import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;




public class AppSystem extends TheSystem {
    AppSystem() {
    }

    @Override
    public void display() {

        Set<String> keySet = getItemCollection().keySet();
        DecimalFormat myNum = new DecimalFormat();
        myNum.setMaximumFractionDigits(2);
        myNum.setMinimumFractionDigits(2);


        System.out.println("AppSystem Inventory:");
        System.out.println("Name                 Description          Price      Available Quantity");

        StringBuilder stringBuilder = new StringBuilder();
        for (String string : keySet) {

            stringBuilder.append(getItemCollection().get(string).getItemName()).append(emptySpaceBuilder(21 - getItemCollection().get(string).getItemName().length()));
            stringBuilder.append(getItemCollection().get(string).getItemDesc()).append(emptySpaceBuilder(21 - getItemCollection().get(string).getItemDesc().length()));
            stringBuilder.append(myNum.format(getItemCollection().get(string).getItemPrice())).append(emptySpaceBuilder(11 - myNum.format(getItemCollection().get(string).getItemPrice()).toString().length()));
            stringBuilder.append(getItemCollection().get(string).getAvailableQuantity()).append(emptySpaceBuilder(10 - getItemCollection().get(string).getAvailableQuantity().toString().length()));

            stringBuilder.append("\n");
        }
        System.out.println(StringUtils.chop(stringBuilder.toString()));

    }

    @Override
    public Boolean add(Item item) {
        if (item == null) {
            return false;
        } else if (getItemCollection().containsKey(item.getItemName())) {
            System.out.println(item.getItemName() + "is already in the App System");
            return false;
        } else {
            getItemCollection().put(item.getItemName(), item);
            return true;
        }
    }

    public Item reduceAvailableQuantity(String item_name) {
        if (getItemCollection().containsKey(item_name)) {

            getItemCollection().get(item_name).setAvailableQuantity(getItemCollection().get(item_name).getAvailableQuantity() - 1);
            if (getItemCollection().get(item_name).getAvailableQuantity() == 0) {
                getItemCollection().remove(item_name);
            }
            return getItemCollection().get(item_name);
        } else {
            return null;
        }
    }

    public String emptySpaceBuilder(int eSB) {
        StringBuffer emptySpace = new StringBuffer();

        for (int i = 0; i < eSB; i++) {
            emptySpace.append(" ");
        }
        return emptySpace.toString();
    }
}
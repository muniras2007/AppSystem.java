package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public abstract class TheSystem {
    private HashMap<String,Item> itemCollection;

    TheSystem() {
        itemCollection = new HashMap<>();
        if(getClass().getSimpleName().equals("AppSystem")) {
            try {
                File myItems = new File("./resources/sample.txt");
                Scanner myReader = new Scanner(myItems);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] itemInfo = data.split("\\s ");
                    System.out.println(data);

                    Item item = new Item(itemInfo[0],itemInfo[1],
                            Double.parseDouble(itemInfo[2]), Integer.parseInt(itemInfo[3]));
                    itemCollection.put(itemInfo[0], item);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();

            }
        }
    }
    
    public HashMap<String, Item> getItemCollection(){
        return itemCollection;
    }
    public void setItemCollection(HashMap<String, Item> ItemCollection){
        this.itemCollection = itemCollection;
    }
    public Boolean checkAvailability(Item item) {

        if (item.getQuantity() >= item.getAvailableQuantity()){
            System.out.println("System is unable to add" + item.getItemName() +
                    "to the card. " +
                    "System only has" + item.getAvailableQuantity() + item.getItemName() );
            return false;
        } else{
            return true;
        }
    }
    
    public Boolean add(Item item) {
        if (item == null)
        {
        return false;
        } else if (itemCollection.containsKey(item.getItemName()))
        {
            item.setQuantity(item.getQuantity() + 1);
            return true;

        } else if (!itemCollection.containsKey(item.getItemName()))
        {
           itemCollection.put(item.getItemName(),item);
           return true;
        } else
        {
            return false;
        }
    }

    public Item remove(String itemName) {
        if (itemCollection.containsKey(itemName))
        {
            return itemCollection.remove(itemName);

        } else
        {
            return null;
        }
    }

    public abstract void display();
}

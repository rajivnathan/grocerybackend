package application;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    static JsonArray items;
    static String dataFilePath = "/home/default/app/db/data.json";

    public Utils() {
        try{
        JsonReader jsonReader = Json.createReader(new FileInputStream(dataFilePath));
        JsonArray a = jsonReader.readArray();
        items = a;
        }catch(Exception e){
            System.out.println("Excetion on reading from data.json file: " + e);
        }
    }


    public static String getItems() {
            return items.toString();
    }

    public static JsonArray addItem(String id, int quantity, double price){
        try{
        JsonObject newItem = Json.createObjectBuilder()
        .add("id", id)
        .add("quantity", quantity)
        .add("price", price).build();

        JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
        itemsBuilder.add(newItem);
        items.forEach( e -> itemsBuilder.add(e));
        items = itemsBuilder.build();
        Files.write(Paths.get(dataFilePath), items.toString().getBytes());
        return items;
        }catch(Exception e){
           System.out.println("Excetion on adding to data.json file: " + e);
         return items; 
        }
    }

    public static void deleteItem(String id){
        try{
        JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
        for(int i=0; i< items.size(); i++){
            if(!items.getJsonObject(i).get("id").toString().replace("\"", "").equalsIgnoreCase(id)){
                itemsBuilder.add(items.getJsonObject(i));
            }
        }
        items = itemsBuilder.build();
        System.out.println(items);
        Files.write(Paths.get(dataFilePath), items.toString().getBytes());
        }catch(Exception e){
           System.out.println("Excetion on deleting & write to data.json file: " + e);
        }
    }

    public static void updatePrice(String id, double price){
        System.out.println("received price!!" + price);
        try{
        JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
        for(int i=0; i< items.size(); i++){
            if(items.getJsonObject(i).get("id").toString().replace("\"", "").equalsIgnoreCase(id)){
                int quantity = Integer.parseInt(items.getJsonObject(i).get("quantity").toString());
                JsonObject newItem = Json.createObjectBuilder()
                    .add("id", id)
                    .add("quantity", quantity)
                    .add("price", price).build();
                itemsBuilder.add(newItem);
            }else{
                itemsBuilder.add(items.getJsonObject(i));
            }
        }
        items = itemsBuilder.build();
        Files.write(Paths.get(dataFilePath), items.toString().getBytes());
        System.out.println(items);
        }catch(Exception e){
           System.out.println("Excetion on updating price & write to data.json file: " + e);
        }
    }

        public static void updateQuantity(String id, int quantity){
            try{
            JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
            for(int i=0; i< items.size(); i++){
                if(items.getJsonObject(i).get("id").toString().replace("\"", "").equalsIgnoreCase(id)){
                    double price = Double.parseDouble(items.getJsonObject(i).get("price").toString());
                    JsonObject newItem = Json.createObjectBuilder()
                        .add("id", id)
                        .add("quantity", quantity)
                        .add("price", price).build();
                    itemsBuilder.add(newItem);
                }else{
                    itemsBuilder.add(items.getJsonObject(i));
                }
            }
            items = itemsBuilder.build();
            Files.write(Paths.get(dataFilePath), items.toString().getBytes());
            System.out.println(items);
        }catch(Exception e){
           System.out.println("Excetion on updating price & write to data.json file: " + e);
        }
    }

    public static int getQuantity(String id) {
        int quantity = 0;
        for(int i=0; i< items.size(); i++){
            if(items.getJsonObject(i).get("id").toString().replace("\"", "").equalsIgnoreCase(id))
                quantity = Integer.parseInt(items.getJsonObject(i).get("quantity").toString());
        }
        return quantity;
    }

    public static double getPrice(String id) {
        double price = 0;
        for(int i=0; i< items.size(); i++){
            if(items.getJsonObject(i).get("id").toString().replace("\"", "").equalsIgnoreCase(id))
                price = Double.parseDouble(items.getJsonObject(i).get("price").toString());
        }
        return price;
    }


}

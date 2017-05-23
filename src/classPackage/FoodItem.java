/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classPackage;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Meng
 */
public class FoodItem {
    private final int itemId;
    private static int Id = 1;
    private int itemPrice; 
    private String itemName;                
    private String itemDescription;
    private List<Ingredient> ingredientList;
    private List<Integer> weightList;

    public FoodItem(String itemName, int itemPrice, String itemDescription, List<Ingredient> ingredientList, List<Integer> weightList) {
        itemId = Id; //assign ID
        Id++;
       
        this.ingredientList = new LinkedList<Ingredient>();
        this.weightList = new LinkedList<Integer>();
        this.ingredientList = ingredientList;
        this.weightList = weightList;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        
    }

    public int getId() {
        return itemId;
    }

    public String getName() {
        return itemName;
    }

    public int getPrice() {
        return itemPrice;
    }

    public String getDescription() {
        return itemDescription;
    }

    public void setName(String name) {
        itemName = name;
    }

    public void setPrice(int price) {
        itemPrice = price;
    }

    public void setDescription(String description) {
        itemDescription = description;
    }
    public String[] getIngredientNames(){
        String[] names= new String[ingredientList.size()];
        for(int i=0;i<names.length;i++){
            names[i]= ingredientList.get(i).getName();
        }
        return names;
    }
    public String[] getIngredientWeights(){
        String[] weights= new String[weightList.size()];
        for(int i=0;i<weights.length;i++){
            weights[i]= weightList.get(i).toString();
        }
        return weights;
    }
    public int getCalories() {
        int totalCalories=0;
        for(int i =0;i<ingredientList.size();i++){
            totalCalories += ingredientList.get(i).getCalorie()*weightList.get(i)/100;
        }
        return totalCalories;
    }
  

}

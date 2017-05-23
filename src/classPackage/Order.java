/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classPackage;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Meng
 */
public class Order {
    private int id;
    private boolean status;
    private static int ID =1;
    private String date;
    private User customer;
    private List<FoodItem> foodItemList;
    public Order(User customer){
        this.foodItemList= new LinkedList<FoodItem>(); // initialize LinkedList
        this.customer=customer;  //assign value
        Calendar calendar = Calendar.getInstance(); // get current time
        date=calendar.getTime().toString();
        id=ID;
        ID++;
        status = true;         //when the order is created, the status is true;
    }
   
    public void addFoodItemToOrder(FoodItem foodItem){
       this.foodItemList.add(foodItem);
    }
    public double getTotalPrice(){
       double totalPrice=0;
       for (FoodItem fItem : this.foodItemList){
           totalPrice += fItem.getPrice();
       }
       return totalPrice;
    }
    public int getTotalCalories(){
       int totalCalories=0;
        for (FoodItem fItem : this.foodItemList){
           totalCalories += fItem.getCalories();
       }
       return totalCalories;
  
    }
    public String getDate(){
        return this.date;
    }
    public List<FoodItem> getFoodItemList(){
        return this.foodItemList;
    }
    public User getCustomer(){
        return this.customer;
    }
    public boolean getStatus(){
        return this.status;
    }
    public String[] getFoodItemListNames(){
        String[] names= new String[this.foodItemList.size()];
        for(int i =0;i<names.length;i++){
            names[i] = this.foodItemList.get(i).getName();
        }
        return names;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setFoodItemList(List<FoodItem> fList){
        this.foodItemList = fList;
    }
    public void setStatus(boolean b){
      status=b; 
    }

}
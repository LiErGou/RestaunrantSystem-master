/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classPackage;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Meng
 */
public class InventoryRecord {
  /*  private List<Integer> totalQuantityList;
    private List<Integer> consumptionQuantityList;
    private List<Integer> restQuantityList;
    private int date;
    private int year;
    private int id;
    private static int ID=1;
    private int length;
    public InventoryRecord(List<Integer> totalQuantityList){
        this.id=ID;
        ID++;
        Calendar calendar = Calendar.getInstance();
        this.date = calendar.get(Calendar.DAY_OF_YEAR);
        this.year = calendar.get(Calendar.YEAR);
        this.totalQuantityList=new LinkedList<>();
        this.totalQuantityList=totalQuantityList;
        this.length=totalQuantityList.size();
        this.consumptionQuantityList=new LinkedList<>();
        this.restQuantityList=new LinkedList<>();

    }
    public List<Integer> getRestQuantityList(){
        for(int i=0;i<length;i++){
            restQuantityList.set(i, totalQuantityList.get(i)-consumptionQuantityList.get(i));
        }
        return restQuantityList;
    }
    public void modifyInventoryRecord(List<FoodItem> foodItemList){ // corresponded index should be the key -1. the key is in map of footitem object
        for(int i =0; i<foodItemList.size();i++){
           Map<Integer, Integer> ingredientMap =  foodItemList.get(i).getIngredientMap(); // get ingredientMap of each FoodItem
              if(ingredientMap.containsKey(i+1)){ //index +1 = ingredient_id
                  int consumption = ingredientMap.get(i+1);
                  if(checkInventory(consumption, totalQuantityList.get(i))){ // quantity is enough to make the fooditem
                  totalQuantityList.set(i, totalQuantityList.get(i)-consumption); // update totalQuantityList
                  consumptionQuantityList.set(i, consumptionQuantityList.get(i)+consumption); //consumption is accumulated
                  }else{ 
                      //open an alert and consumptionQuantityList will not be changed.
                  }
            }
        }
        getRestQuantityList();//update restQuantityList;
        
    }
    public boolean checkInventory(int total, int consumption){
        if(total>=consumption){
            return true;
        }else{
            return false;
        }
    }*/
}

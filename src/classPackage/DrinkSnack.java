/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classPackage;

/**
 *
 * @author Meng
 */
import java.util.List;

/**
 *
 * @author Ting
 */
public class DrinkSnack extends FoodItem {

    private int size;

    public DrinkSnack(String itemName, int itemPrice, String itemDescription, List<Ingredient> ingredientList, List<Integer> weightList, int size) {
        super(itemName, itemPrice, itemDescription, ingredientList, weightList);
        this.size = size;
    }

    public void setSize(int size) {
        this.size = size; //should be 1-3
        if(size==1){
            this.setPrice((int)(this.getPrice()*0.8));
        }
        else if(size == 2){
            this.setPrice(this.getPrice());
    }
        else{
        this.setPrice((int)(this.getPrice()*1.5));
    }
    }
    public int getSize(){
        return this.size;
    }
    public String getStringSize(int size){
        switch (this.size){
                case 1:return "Small";
                case 2:return "Medium";
                case 3:return "Large";
                default:return "Medium";
        }           
    }

}

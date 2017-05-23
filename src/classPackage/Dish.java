/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classPackage;

import java.util.List;

/**
 *
 * @author Meng
 */
public class Dish extends FoodItem {
	
    private int flavor=0; 
    private String pic;
    private String materialId;
    public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Dish(String itemName, int itemPrice, String itemDescription, List<Ingredient> ingredientList, List<Integer> weightList, int flavor,String pic) {
        super(itemName, itemPrice, itemDescription, ingredientList, weightList);
        this.flavor = flavor;
        this.pic=pic;
    }
    public Dish(String itemName, int itemPrice, String itemDescription, List<Ingredient> ingredientList, List<Integer> weightList, int flavor) {
        super(itemName, itemPrice, itemDescription, ingredientList, weightList);
        this.flavor = flavor;
    }
    public void setFlavor(int flavor) {
        this.flavor = flavor;  //the value should be 1 - 3
    }
    public int getFlavor(){
        return this.flavor;
    }
    public String getStringFlavor(){
        switch (this.flavor){
                case 1:return "Light";
                case 2:return "Medium";
                case 3:return "Hard";
                default:return "Medium";
        }           
    }
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String string) {
		this.materialId = string;
	}

}

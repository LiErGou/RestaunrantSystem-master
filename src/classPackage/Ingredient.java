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
public class Ingredient {
    private String name;
    private int calorie;
    private int id;
    public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
	private static int ID =1;
    public Ingredient(String name, int c){
        this.name=name;
        this.calorie=c;
        this.id=ID;
        ID++;
    }
   
    public Ingredient() {
		// TODO Auto-generated constructor stub
	}

	public int getCalorie(){
        return this.calorie;
    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
}

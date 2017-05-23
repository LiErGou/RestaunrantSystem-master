package guiPackage.administrator;


import java.awt.*;


public class CellInfo {
    public String svgPath;
    public String iconName;
    public int id;

    //public Color color=Color.BLACK;
    public Color bgColor=Color.WHITE;
    
    public CellInfo(int id, String svg, String nm, Color bgColor){
        this.id = id;
        this.svgPath=svg;
        this.iconName=nm;
        this.bgColor = bgColor;
    }
    public CellInfo(String svgPath,String iconName){
    	this.svgPath=svgPath;
    	this.iconName=iconName;
    }
}

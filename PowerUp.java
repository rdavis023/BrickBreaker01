/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Rahshann
 */
public class PowerUp {
    
    //Fields
    
    private int x, y, dy, type, width, height;
    
    private boolean onScreen;
    
    private boolean wasUsed;
    
    private Color color;
    
    
    public final static int WIDEPADDLE = 4;
    public final static int FASTBALL = 5;
    public final static Color WIDECOLOR = new Color(50,50,125);
    public final static Color FASTCOLOR = new Color(25, 100,200);
    
    public PowerUp(int xStart, int yStart, int theType, int theWidth, int theHeight){
      x = xStart;
      y = yStart;
      type = theType;
      width = theWidth;
      height = theHeight;
      
      if(type < 4){
          type = 4;
      }
      
      if(type > 5){
          type = 5;
      }
      
      if(type == WIDEPADDLE){
          color = WIDECOLOR;
      }
      
      if(type == FASTBALL){
          color = FASTCOLOR;
      }
        
      dy = (int) (Math.random() * 6 +1);
      
      wasUsed = false;
    }
    
    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    public void update(){
        
        y += dy;
        
        if(y > BrickBreaker.HEIGHT){
            onScreen = false;
        }
        
    }
    
    public int getX(){
        return x;
    }
    
    public void  setX(int newX){
        x = newX;
}
   public int getY(){
        return y;
    } 
   
   public void  setY(int newY){
        y = newY;
}
   
    public int getDY(){
        return dy;
    }
    
     public void  setDY(int newDY){
        dy = newDY;
}
     
     public int getType(){
        return type;
    } 
     
     public void  setType(int newType){
        type = newType;
     }
    
      public boolean getOnScreen(){
        return onScreen;
      }
      
       public void  setOnScreen(boolean IsOnScreen){
        onScreen = IsOnScreen;
     }
       
       public Rectangle getRect(){
           return new Rectangle(x,y,width,height);
           
       }
       
       public boolean getWasUsed(){
           return wasUsed;
       }
       
       public void setWasUsed(boolean Used){
           wasUsed = Used;
       }
}
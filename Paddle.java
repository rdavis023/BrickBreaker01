/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Rahshann
 */
public class Paddle {
    //Fields
    
   
    private double x;
    private int width, height, startWidth;
    
    private long widthTimer;
    
    private boolean altWidth;
    
    
    
    public final int YPosition = BrickBreaker.HEIGHT - 100;
    
    //Constructor
    public Paddle(int theWidth, int theHeight){
        
        altWidth = false;
        width = theWidth;
        startWidth = theWidth;
        height = theHeight;
        x = BrickBreaker.WIDTH/2 - width/2;
    }
    
    
    //update
    public void update(){
        
        if(System.nanoTime() - widthTimer /1000 > 9000000){
            width = startWidth;
            altWidth = false;
        }
    }
    
    
    
    //draw
public void draw(Graphics2D g){

    g.setColor(Color.blue);
    g.fillRect((int) x, YPosition, width, height);
    
    if(altWidth == true){
        g.setColor(Color.green);
        g.setFont(new Font("Colonna MT",Font.BOLD,20));
        g.drawString("Shrank" + (9 - (System.nanoTime()- widthTimer) / 1000000000), (int)x, YPosition);
    }
}

public void MouseMover(int mouseXPos){
    x = mouseXPos;
    
    if(x > BrickBreaker.WIDTH - width){
        x = BrickBreaker.WIDTH - width;
    }
    
  
}

public Rectangle getRect(){
    return new Rectangle((int)x, YPosition, width, height );
}

public int getWidth(){
    return width;
}

public void setWidth(int newWidth){
    altWidth = true;
    width = newWidth;
    setWidthTimer();
}

public void setWidthTimer(){
    widthTimer = System.nanoTime();
}
}

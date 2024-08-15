/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Rahshann
 */
public class Ball {
    
    
    private double x, y, dx, dy;
    private int ballSize = 30;
    
    
    
    
    public Ball(int theX, int theY, int theDX, int theDY, int size){
        x = theX;
        y = theY;
        dx = theDX;
        dy = theDY;
        ballSize = size;
        
    }
    
    public void update(){
       setPosition(); 
    }
    
    public void setPosition(){
        
        x += dx;
        y += dy;
        
        if(x < 0){
            dx = -dx;
        }
        
        if(y < 0){
            dy = -dy;
        }
        
        if(x > BrickBreaker.WIDTH - ballSize){
            dx = -dx;
        }
        
        if(y > BrickBreaker.HEIGHT - ballSize){
            dy = -dy;
        }
    }
    
    public void draw(Graphics2D g){
        g.setColor(Color.red);
        g.setStroke(new BasicStroke(4));
        g.drawOval((int) x, (int) y, ballSize, ballSize);
    }
    
    public Rectangle getRect(){
        return new Rectangle((int) x, (int) y, ballSize, ballSize);
    }
    
    public void setDY(double theDY){
        dy = theDY;
    }
    
    public double getDY(){
        return dy;
    }
    
    public void setDX(double theDX){
        dx = theDX;
        
    }
    
    public double getDX(){
        return dx;
    }
    
    public double getX(){
        return x;
    }
    
    public boolean youLose(){
        boolean loser = false;
        
        if(y > BrickBreaker.HEIGHT - ballSize * 2){
            loser = true;
        }
        return loser;
    }
    
}

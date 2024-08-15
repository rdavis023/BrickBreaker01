/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Rahshann
 */
public class Map {
    
    private int [][] theMap;
    private int brickWidth, brickHeight;
    
    public final int HDR_PAD = 80, VERT_PAD = 50;
    
    public Map(int row, int col){
        initMap(row,col);
        
        brickWidth = (BrickBreaker.WIDTH - 2 * HDR_PAD)/ col;
        brickHeight = (BrickBreaker.HEIGHT/2 - VERT_PAD * 2)/row;
    }
    
    public void initMap(int row, int col){
        theMap = new int[row][col];
        
        for(int i = 0; i< theMap.length; i++){
            for(int j = 0; j < theMap[0].length; j++){
                int r = (int) (Math.random() * 3 + 1);
                theMap[i][j] = r;
            }
        }
        
        theMap[1][2]=4;
        theMap[2][4]=5;
            
    }
    public void draw(Graphics2D g){
        
     
        for(int row = 0; row < theMap.length; row++){
            for(int col = 0; col < theMap[0].length; col++){
                
                if(theMap[row][col] > 0){
                    
                if(theMap[row][col] == 1){
                    g.setColor(new Color(200,200,200));
                }
                
                 if(theMap[row][col] == 2){
                    g.setColor(new Color(150,150,150));
                }
                 
                  if(theMap[row][col] == 3){
                    g.setColor(new Color(100,100,100));
                }
                  
                   if(theMap[row][col] == PowerUp.WIDEPADDLE){
                    g.setColor(PowerUp.WIDECOLOR);
                }
                    if(theMap[row][col] == PowerUp.FASTBALL){
                    g.setColor(PowerUp.FASTCOLOR);
                }
                
                g.fillRect(col * brickWidth + HDR_PAD, row * brickHeight + VERT_PAD, brickWidth, brickHeight);
                g.setStroke(new BasicStroke(2));
                g.setColor(Color.white);
                g.drawRect(col * brickWidth + HDR_PAD, row * brickHeight + VERT_PAD, brickWidth, brickHeight);
                }
                
                }
        }
    }
    
    public boolean Win(){
      boolean YesWin = false;
        
      int remainingBricks = 0;
      
      for(int row = 0; row < theMap.length; row++){
          for(int col = 0; col < theMap[0].length; col++){
              remainingBricks += theMap[row][col];
          }
      }
      
      if(remainingBricks == 0){
          YesWin = true;
      }
        
        return YesWin;
    }
    
    public int [][] getMapArray(){
        return theMap;
    }
    
    public void setBrick(int row, int col, int value){
        theMap[row][col]= value;
    }
    
  public int getBrickWidth(){
      return brickWidth;
  }
  
  public int getBrickHeight(){
      return brickHeight;
  }
  
  public void hitBrick(int row, int col){
      theMap[row][col]-= 1;
      
      if(theMap[row][col] < 0){
          theMap[row][col] = 0;
      }
      
  }
}

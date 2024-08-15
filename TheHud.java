/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Rahshann
 */
public class TheHud {
    
    //Field
    
    private int score;
    
    //constructor
    public TheHud(){
        init();
    }
    
    public void init(){
        score = 0;
    }
    
    public void draw(Graphics2D g){
        g.setFont(new Font("Colonna MT",Font.PLAIN,20));
        g.setColor(Color.red);
        g.drawString("Score " + score, 20, 20);
    }
    
    public int getScore(){
        return score;
    }
    
    public void addScore(int newScore){
        score += newScore;
    }
}


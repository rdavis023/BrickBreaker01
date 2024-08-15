/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package brickbreaker;

import javax.swing.JFrame;

/**
 *
 * @author Rahshann
 */
public class BrickBreaker {

    /**
     * @param args the command line arguments
     */
    public static final int WIDTH = 640, HEIGHT = 480;
    
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame theFrame = new JFrame("Brick Breaker");
        GamePanel thePanel = new GamePanel();
        Thread theThread = new Thread(thePanel);
        
        
        
        theFrame.setLocationRelativeTo(null);
        theFrame.setResizable(false);
        theFrame.setSize(WIDTH, HEIGHT);
        
        
        theFrame.add(thePanel);
        
        theThread.start();
        
        
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
    }
    
}

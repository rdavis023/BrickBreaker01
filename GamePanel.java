/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Rahshann
 */
public class GamePanel extends JPanel implements Runnable {
    private boolean runs;
    private BufferedImage image;
    private Graphics2D g;
    private MyMouseMotionListener theMouseListener;
    
    private int mouseX;
    
    
    //add entities
   private Ball theBall;
    private Paddle thePaddle;
    private Map theMap;
    private TheHud theHud;
    private ArrayList<PowerUp> powerUps;
    
    public GamePanel(){
        init();
    }
    
    public void init(){
        mouseX = 0;
        
        theBall = new Ball(200, 200, 1, 4, 20);
        
        thePaddle = new Paddle(100,20);
        
        theMap = new Map(6,6);
        
        theHud = new TheHud();
        
        theMouseListener = new MyMouseMotionListener();
        
        powerUps = new ArrayList<PowerUp>();
        
        addMouseMotionListener(theMouseListener);
        
        runs = true;
        
        image = new BufferedImage(BrickBreaker.WIDTH,BrickBreaker.HEIGHT, BufferedImage.TYPE_INT_RGB);
        
        g = (Graphics2D) image.getGraphics();
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public void run() {
        
        
        while(runs){
            //update
            update();
            
            //draw
            draw();
            
            
            //display
            repaint();
            
            try {
                Thread.sleep(10);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    public void checkCollisions(){
        Rectangle ballRect = theBall.getRect();
        Rectangle paddleRect = thePaddle.getRect();
        
        for(int i = 0; i < powerUps.size(); i++){
            
            Rectangle puRect = powerUps.get(i).getRect();
            
            if(paddleRect.intersects(puRect)){
                
                if(powerUps.get(i).getType() == PowerUp.WIDEPADDLE && !powerUps.get(i).getWasUsed()){
                    thePaddle.setWidth(thePaddle.getWidth() * 2);
                    powerUps.get(i).setWasUsed(true);
                }
            }
        }
        
        
        if(ballRect.intersects(paddleRect)){
            theBall.setDY(-theBall.getDY());
            
            if(theBall.getX() < mouseX + thePaddle.getWidth() / 4){
                theBall.setDX(theBall.getDX() - .5);
            }
            
            if(theBall.getX() < mouseX + thePaddle.getWidth() && theBall.getX() > mouseX + thePaddle.getWidth() / 4){
                theBall.setDX(theBall.getDX() + .5);
            }
        }
        
       
        
        A: for(int row = 0; row < theMap.getMapArray().length; row++){
            for(int col = 0; col < theMap.getMapArray()[0].length; col++){
                
                if(theMap.getMapArray()[row][col]>0){
                int brickX = col * theMap.getBrickWidth() + theMap.HDR_PAD;
                int brickY = row * theMap.getBrickHeight() + theMap.VERT_PAD;
                int brickWidth= theMap.getBrickWidth();
                int brickHeight = theMap.getBrickHeight();
                
                Rectangle brickRect = new Rectangle(brickX, brickY,brickWidth, brickHeight);
                
                if(ballRect.intersects(brickRect)){
                    
                    if(theMap.getMapArray()[row][col] > 3){
                        powerUps.add(new PowerUp(brickX,brickY,theMap.getMapArray()[row][col], brickWidth, brickHeight));
                        theMap.setBrick(row, col, 3);
                    }
                    else{
                        theMap.hitBrick(row, col);
                    }
                    theMap.hitBrick(row, col);
                    
                    theBall.setDY(-theBall.getDY());
                    
                    theHud.addScore(50);
                    
                    break A;
                }
                }
            }
        }
    }
    
    public void update(){
        
        checkCollisions();
        theBall.update();
        
        for(PowerUp pu : powerUps){
            pu.update();
        }
    }
    
    public void draw(){
        
        //drawing background
        g.setColor(Color.white);
        
        g.fillRect(0, 0, BrickBreaker.WIDTH, BrickBreaker.HEIGHT);
        
        theBall.draw(g);
        
        thePaddle.draw(g);
        
        theMap.draw(g);
        
        theHud.draw(g);
        
        drawPowerUps();
        
        if(theMap.Win() == true){
            drawWin();
            runs = false;
        }
        
        if(theBall.youLose()){
            drawLoser();
            runs = false;
        }
    }
    
    public void drawWin(){
        g.setColor(Color.red);
        g.setFont(new Font("Colonna MT",Font.BOLD,100));
        g.drawString("Winner!", 200, 200);
    }
    
    public void drawPowerUps(){
        for(PowerUp pu : powerUps){
            pu.draw(g);
        }
    }
    
     public void drawLoser(){
        g.setColor(Color.red);
        g.setFont(new Font("Colonna MT",Font.BOLD,100));
        g.drawString("Loser!", 200, 200);
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.drawImage(image, 0, 0, BrickBreaker.WIDTH, BrickBreaker.HEIGHT, null);
        
        g2.dispose();
    }
    
    private class MyMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            thePaddle.MouseMover(e.getX());// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
    }
    
}

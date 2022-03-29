/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import entity.Player;
import main.tiles.TileManager;

/**
 *
 * @author Mabiyev
 */
public class GamePanel extends JPanel implements Runnable {
    //SET WINDOW SETTINGS
    final int originalTileSize = 16;
    final int scale = 1;
    public int tileSize = originalTileSize * scale;
    
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 24;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
      
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;
    
    
    int FPS = 30;
    
    TileManager tileM = new TileManager(this);
    public Player pl = new Player(this);
    
    Pathfinder pf = new Pathfinder(this, tileM);
    
    public CollisionChecker cChecker = new CollisionChecker(this, tileM);
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;  
    int playerSpeed = 2;
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        addMouseListener(new MouseAdapter() {
        	
            @Override
            public void mousePressed(MouseEvent e) {
            	pf.findTilePath(pl, pl.worldX+e.getX()-pl.screenX,pl.worldY+e.getY()-pl.screenY);
            }
            
           });
        }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
   
        while(gameThread != null){

            //FPS is 30
            
            //Update soldiers' positions
            update();
            //Draw the screen with the updated charPos;
            repaint();
            try{
            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime/1000000;
            
            if (remainingTime<0){
                remainingTime=0;
            }
            Thread.sleep((long) remainingTime);
            nextDrawTime +=drawInterval;
            }
          
            catch(Exception exc){
            System.out.println(exc.getCause());
            }
        }
    }
public void update(){

    cChecker.persistentCollisionDetectionForCamera(pl);
        if (keyH.upPressed==true){
        	pl.direction="up";
        	pl.worldY -= pl.speed;
            System.out.println(pl.worldY);
        }
        else if (keyH.downPressed==true){
        	pl.direction="down";
        	pl.worldY += pl.speed;
            
            
        }
        else if (keyH.leftPressed==true){
        	pl.direction="left";
        	pl.worldX -= pl.speed;
            
        }
        else if (keyH.rightPressed==true){
        	pl.direction="right";
        	pl.worldX += pl.speed;
            
        }
        
       
    }
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        tileM.drawMap(g2, "D:\\CODING\\DecentralizedCommand\\src\\maps\\map1.txt");
        
        g2.setColor(Color.white);
                
        pl.drawPlayer(g2);

        g2.dispose();
            
    }
    public void paintOnce(Graphics g){
        
    }

    
}

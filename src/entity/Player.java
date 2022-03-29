/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

/**
 *
 * @author Mabiyev
 */
public class Player extends Entity{
    BufferedImage image;
    GamePanel gp;
    
    public int screenX, screenY;
    KeyHandler keyH = new KeyHandler();
    public Player(GamePanel gp){
        this.speed = 4;
        try{
            this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/commander.png"));
        }
        catch (IOException ex) {
            System.out.println("Error while finding image of commander");
        }
        direction="up";
    this.gp = gp;
    worldX = gp.tileSize*1;
    worldY = gp.tileSize*1;
    screenY = gp.screenHeight/2;
    screenX = gp.screenWidth/2;
    
    }
    
    public void drawPlayer(Graphics2D g2){
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
    
    
}

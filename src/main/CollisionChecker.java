/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import entity.Entity;
import entity.Player;
import main.tiles.TileManager;

/**
 *
 * @author Mabiyev
 */
public class CollisionChecker {
    GamePanel gp;
    TileManager tm;
    public CollisionChecker(GamePanel gp, TileManager tm){
        this.gp = gp;
        this.tm = tm;
    }
    public void persistentCollisionDetectionForCamera(Entity entity){
        
	    int currentTileCoordinateX = (entity.worldX+(gp.tileSize/2))/gp.tileSize;
	    int currentTileCoordinateY = (entity.worldY-(gp.tileSize/2))/gp.tileSize;
        
	    
	    if(entity.direction=="up") {
	    	if(tm.getTileCollision(currentTileCoordinateX, currentTileCoordinateY)) {
	    		entity.worldY+=entity.speed;
	    	}
	    	}
	    if(entity.direction=="left") {
	    	if(tm.getTileCollision(currentTileCoordinateX, currentTileCoordinateY)) {
	    		entity.worldX+=entity.speed;
	    	}}
	    if(entity.direction=="down") {
	    	if(tm.getTileCollision(currentTileCoordinateX, currentTileCoordinateY)) {
	    		entity.worldY-=entity.speed;
	    	}
	    }
	    if(entity.direction=="right") {
	    	if(tm.getTileCollision(currentTileCoordinateX, currentTileCoordinateY)) {
	    		entity.worldX-=entity.speed;
	    	}
	    	
	    	}

	    
	   	 }
    }


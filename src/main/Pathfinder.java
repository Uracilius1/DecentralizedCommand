package main;

import entity.Entity;
import main.tiles.TileManager;

public class Pathfinder {
	GamePanel gp;
    TileManager tm;
    public Pathfinder(GamePanel gp, TileManager tm){
        this.gp = gp;
        this.tm = tm;
    }
    
    public String findTilePath(Entity entity, int x, int y){
    	int currentTileCoordinateX = (entity.worldX+(gp.tileSize/2))/gp.tileSize;
	    int currentTileCoordinateY = (entity.worldY-(gp.tileSize/2))/gp.tileSize;
        
    	String directions = "";
    	
    	int deltaX = (x+(gp.tileSize/2))/gp.tileSize-currentTileCoordinateX;
    	if(deltaX<0) {
    		directions+="l="+Math.abs(deltaX);
    		directions+=" ";
    	}
    	else if(deltaX>0) {
    		directions+="r="+Math.abs(deltaX);
    		directions+=" ";
    	}
    	
    	int deltaY = (y+(gp.tileSize/2))/gp.tileSize-currentTileCoordinateY;
    	if(deltaY<0) {
    		directions+="u="+Math.abs(deltaY);
    		directions+=" ";
    	}
    	else if(deltaY>0) {
    		directions+="d="+Math.abs(deltaY);
    		directions+=" ";
    	}
    	System.out.println(directions);
    	return directions;
    	
    }

}

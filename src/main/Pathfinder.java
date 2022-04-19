package main;

import java.util.Arrays;
import java.util.List;

import entity.Entity;
import main.tiles.TileManager;

public class Pathfinder {
	GamePanel gp;
    TileManager tm;
    public Pathfinder(GamePanel gp, TileManager tm){
        this.gp = gp;
        this.tm = tm;
    }
    
    public List<Point> findTilePath(Entity entity, int x, int y){
    	int currentTileCoordinateX = (entity.worldX+(gp.tileSize/2))/gp.tileSize;
	    int currentTileCoordinateY = (entity.worldY-(gp.tileSize/2))/gp.tileSize;
        
	    int finalTileCoordinateX = (x-(gp.tileSize/2))/gp.tileSize;
	    int finalTileCoordinateY = (y-(gp.tileSize/2))/gp.tileSize;
	    System.out.println("CurrentTiles are:"+ currentTileCoordinateX+" And "+currentTileCoordinateY);
	    System.out.println("FinalTiles are:"+ finalTileCoordinateX+" And "+finalTileCoordinateY);
	    Point start=new Point(currentTileCoordinateX, currentTileCoordinateY, null);
	    Point finish=new Point(finalTileCoordinateX, finalTileCoordinateY, null);
	    List<Point> path = tm.FindPath(start, finish);
	    if (path != null) {
            for (Point point : path) {
                System.out.println(point);
            }
        }
		
	    return path;
    	
    }

}

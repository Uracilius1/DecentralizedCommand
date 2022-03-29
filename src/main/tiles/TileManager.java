/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Mabiyev
 */
public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	int[][] currentMap;
	int size = -1;
	int log10 = 0;
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		try {
			loadMap("D:\\CODING\\DecentralizedCommand\\src\\maps\\map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(currentMap);
		
	}

	private void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/obstacle.png"));
			tile[0].collision = true;
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cover.png"));
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rifleman.png"));
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/elevation.png"));
		} catch (IOException ex) {
			System.out.println("Error getting images;");
		}

	}

	// https://stackoverflow.com/questions/22185683/read-txt-file-into-2d-array
	// adapted.
	public void loadMap(String map) throws IOException {
		File initialFile = new File(map);
	    InputStream targetStream = new FileInputStream(initialFile);
	    
		BufferedReader buffer = new BufferedReader(new InputStreamReader(targetStream));
		
		
		String line;
		int row = 0;

		while ((line = buffer.readLine()) != null) {
			String[] vals = line.trim().split(" ");
			// Lazy instantiation.
			if (currentMap == null) {
				size = vals.length;
				currentMap = new int[size][size];
				log10 = (int) Math.floor(Math.log10(size * size)) + 1;
			}

			for (int col = 0; col < size; col++) {
				currentMap[row][col] = Integer.parseInt(vals[col]);
			}

			row++;
		}
	}

	public void drawMap(Graphics2D g2, String map) {
        try {
        	int worldX = 0;
            int worldY = 0;

            int screenX;
            int screenY;
            
                for (int i = 0; i < currentMap.length; i++) {
                    
                	worldX = i * gp.tileSize;
                    screenX = worldX - gp.pl.worldX + gp.pl.screenX;
      
                    for (int j = 0; j < currentMap[i].length; j++) {
                        worldY = j * gp.tileSize;
                        screenY = worldY - gp.pl.worldY + gp.pl.screenY;

                        if (worldX +gp.tileSize> gp.pl.worldX - gp.pl.screenX
                                && worldX - gp.tileSize < gp.pl.worldX + gp.pl.screenX
                                && worldY +gp.tileSize> gp.pl.worldY - gp.pl.screenY
                                && worldY-gp.tileSize < gp.pl.worldY + gp.pl.screenY) {
                            g2.drawImage(tile[currentMap[j][i]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                        }
                    }
                }
            
        }catch(Exception exc)
	{
		System.out.println(exc);
	}

	}
	

public boolean getTileCollision(int row, int column) {
	return tile[currentMap[column+1][row]].collision;
}

	public ArrayList<int[]> fixPathing(int currentX, int currentY, int finalX, int finalY, List<int[]> path) {
		int moveX = finalX - currentX;
			while(findPathBeforeCollisionX(currentX, currentY, moveX)) {
				
			}
			return "None";
		}
		
	
	
	public List<int[]> findPathBeforeCollisionX(List<int[]> previousPath, int moveX, int currentX, int currentY) {
		//Move on the x axis until hit obstacle or reached path.
		int signX = (int) Math.signum(currentX);
		List <int[]> path = new ArrayList<int[]>();
		for(int i = 0; i<moveX; i++) {
			currentX=currentX+signX;
			int[] addendum = {currentX, currentY};
			path.add(addendum);
			if(getTileCollision(currentX, currentY)) {
				path.add(new int[] {-1, -1});
				return path;
			}
		}
		return path;
		
		}
	public int findCollisionOnPathY(int moveY, int currentX, int currentY) {
		//Move on the x axis until hit obstacle or reached path.
		int signY = (int) Math.signum(moveY);
		
		for(int i = 0; i<moveY; i++) {
			currentY=currentY+signY;
			if(getTileCollision(currentX, currentY)) {
				return currentY;
			}
		}
		return -1;
		
		}
	
		
		
	}


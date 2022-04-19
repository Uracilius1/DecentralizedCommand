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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.Point;

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

					if (worldX + gp.tileSize > gp.pl.worldX - gp.pl.screenX
							&& worldX - gp.tileSize < gp.pl.worldX + gp.pl.screenX
							&& worldY + gp.tileSize > gp.pl.worldY - gp.pl.screenY
							&& worldY - gp.tileSize < gp.pl.worldY + gp.pl.screenY) {
						g2.drawImage(tile[currentMap[j][i]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
					}
				}
			}

		} catch (Exception exc) {
			System.out.println(exc);
		}

	}

	public boolean getTileCollision(int row, int column) {
		return tile[currentMap[column + 1][row]].collision;
	}

//	public List<int[]> fixPathing(int currentX, int currentY, int finalX, int finalY) {
//		
//		
//		int[] finalCoords = new int[]{finalX, finalY,0};
//		int[] startingCoords = new int[] {currentX, currentY, 0};
//		List <int[]> path = new ArrayList<int[]>();
//		
//		path.add(startingCoords);
//		int cost = 0;
//		int iterations=0;
//		while(!path.contains(finalCoords)&&iterations<100) {
//			int i=0;
//			
//	        while (i<10) {
//	        	
//	        	path = getSurroundingTiles(path, path.get(i), cost);
//	        	
//	        	
//	        	i++;
//	        }
//
//        	path = new ArrayList<>(new HashSet<>(path));
//	        iterations++;
//	        cost++;
//
//	        
//
//		}
//
//		System.out.println(Arrays.deepToString(path.toArray()));
//		return path;
//			
//			
//		}
	

	 public List<Point> FindNeighbors(Point point) {
	        List<Point> neighbors = new ArrayList<>();
	        Point up = point.offset(0,  1);
	        Point down = point.offset(0,  -1);
	        Point left = point.offset(-1, 0);
	        Point right = point.offset(1, 0);
	        if (!getTileCollision(up.x,up.y)) neighbors.add(up);
	        if (!getTileCollision(down.x,down.y)) neighbors.add(down);
	        if (!getTileCollision(left.x,left.y)) neighbors.add(left);
	        if (!getTileCollision(right.x,right.y)) neighbors.add(right);
	        return neighbors;
	    }

	 public List<Point> FindPath(Point start, Point end) {
	        boolean finished = false;
	        List<Point> used = new ArrayList<>();
	        used.add(start);
	        while (!finished) {
	            List<Point> newOpen = new ArrayList<>();
	            for(int i = 0; i < used.size(); ++i){
	                Point point = used.get(i);
	                for (Point neighbor : FindNeighbors(point)) {
	                    if (!used.contains(neighbor) && !newOpen.contains(neighbor)) {
	                        newOpen.add(neighbor);
	                    }
	                }
	            }

	            for(Point point : newOpen) {
	                used.add(point);
	                if (end.equals(point)) {
	                    finished = true;
	                    break;
	                }
	            }

	            if (!finished && newOpen.isEmpty())
	                return null;
	        }

	        List<Point> path = new ArrayList<>();
	        Point point = used.get(used.size() - 1);
	        while(point.getPrevious()!= null) {
	            path.add(0, point);
	            point = point.previous;
	        }
	        return path;
	    }
	 
//	public List<int[]> getSurroundingTiles(int[] coords) {
//		// Move on thes x axis until hit obstacle or reached path.
//		int x = coords[0];
//		int y = coords[1];
//		List <int[]> path = new ArrayList<int[]>();
//		int[] addendum = new int[] {x, y};
//		if(!getTileCollision(x-1, y)) {
//			addendum[0]=x-1;
//			addendum[1]=y;
//			path.add(addendum);
//			}
//		if(!getTileCollision(x+1, y)) {
//			addendum[0]=x+1;
//			addendum[1]=y;
//			path.add(addendum);
//			}
//		if(!getTileCollision(x, y-1)) {
//			addendum[0]=x;
//			addendum[1]=y-1;
//			path.add(addendum);
//			}
//		if(!getTileCollision(x, y+1)) {
//			addendum[0]=x;
//			addendum[1]=y+1;
//			path.add(addendum);
//			}
//		System.out.println(x);
//		System.out.println(y);
//		System.out.println(!getTileCollision(y, x-1));
//		return path;
//		}
//		
		
//
//	public List<int[]> findPathBeforeCollisionY(List<int[]> path, int moveY) {
//		// Move on the x axis until hit obstacle or reached path.
//		int currentX = path.get(path.size()-1)[0];
//		int currentY = path.get(path.size()-1)[1];
//		int signY = (int) Math.signum(moveY);
//		for (int i = 0; i < Math.abs(moveY); i++) {
//			currentY = currentY + signY;
//			int[] addendum = { currentX, currentY };
//			path.add(addendum);
//			if (getTileCollision(currentX, currentY)) {
//				path.add(new int[] { -1, -1 });
//				return path;
//			}
//		}
//		return path;
//
//	}

}

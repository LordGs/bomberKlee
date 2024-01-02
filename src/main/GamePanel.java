package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;

import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//Screen size
	final int originalTileSize = 32;
	final int scale = 2;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 14; //default 14
	public final int maxScreenRow = 10; // default 10
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//world settings
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
//	public final int worldWidth = tileSize * maxWorldCol;
//	public final int worldHeight = tileSize * maxWorldRow;
	
	
	
	//fps
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	
	Sound music = new Sound();
	Sound se = new Sound();
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	
	//entity and player
	public Player player = new Player(this,keyH);
	public Entity obj[] = new Entity[20];
	public Entity npc[] = new Entity[20];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	//Game state
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int gameOverState = 3;
	public final int victoryState = 4;
	
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		playMusic(8);
		gameState = titleState;
	}
	
	public void retry() {
		player.setDefaultPosition();
		player.restoreLife();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		player.numOfKeys = 0;
		playSE(2);
		playMusic(0);
	}
public void restart() {
		player.setDefaultPosition();
		player.restoreLife();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		player.numOfKeys = 0;
		playMusic(8);
		playSE(2);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		double drawInterval = 1000000000 / FPS; // 0.01666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		//long timer = 0;
		//int drawCount = 0;
				
				
		while(gameThread != null) {
			
			
			//long currentTime = System.nanoTime();
			//long currentTime2 = System.currentTimeMillis();	
			//System.out.println("current Time: " + currentTime);
			
			//update
			update();
			
			//Draw or Paint
			repaint();
			//drawCount++;
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			}
		}
		
	}
	
	public void update() {
		
		
		if(gameState == playState) {
			player.update();
			
			//NPC update
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			
			
			//Monster Update
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					if(monster[i].alive == true && monster[i].dying == false) {
						monster[i].update();
					}
					if(monster[i].alive == false) {
						monster[i] = null;
						
					}
					
				}
			}
			
			
		}if(gameState == pauseState) {
			//game is paused
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		if(gameState == titleState) {
			
			ui.draw(g2);
			
			
			
		} 
		//if not on the titleState or after the titleState
		else {
			//tile drawing
			tileM.draw(g2);
			
			
			//add entity to arraylist
			entityList.add(player);
			//npc painting
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			//monster painting
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			//object painting
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			
			//sort
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					// TODO Auto-generated method stub
					
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}

			});
			
			//Draw Entities
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
				
			}
			//empty entities
//			for(int i = 0; i < entityList.size(); i++) {
//				entityList.remove(i);
//				
//			}
			
			entityList.clear();

		
			
			//ui drawing
			ui.draw(g2);
		}
		g2.dispose();

		
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}







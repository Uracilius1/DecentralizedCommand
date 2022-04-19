package entity;

import main.GamePanel;

public class NPC_Enemy extends Entity {
	public NPC_Enemy(GamePanel gp) {
		super(gp);
		direction="down";
		speed=1;
	}
	public void getImage() {
		up1=setup("/DecentralizedCommand/src/tiles/commander.png");
	}
}

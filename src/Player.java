import java.util.HashMap;
import java.util.Map;

public class Player extends GameObject {
	
	String name;
	int MP;
	Map<String, String[]> skillSet = new HashMap<String, String[]>();
	Map<String, Integer> dmgList = new HashMap<String, Integer>();
	int EXP = 0;
	int lvl = 0;
	String Class;
	int baseDMG;
	
	public Player(MonsterGame game, String name, String Class) {
		this.name = name;
		this.HP = RandIntGen.randInt(1000, 1500);
		this.MP = RandIntGen.randInt(150, 200);
		this.type = "Player";
		generateSkillSet(Class);
		int x = RandIntGen.randInt(0, 9);
		int y = RandIntGen.randInt(0, 9);
		this.pos[0] = x;
		this.pos[1] = y;
		this.type = "P";
	}
	
	public void Move(MonsterGame game, String direction) {
		int oldX = game.player.pos[0];
		int oldY = game.player.pos[1];
		int newX = game.player.pos[0];
		int newY = game.player.pos[1];
		if (direction.equalsIgnoreCase("Up")) {
			newY += 1;
		} else if (direction.equalsIgnoreCase("Down")) {
			newY += -1;	
		} else if (direction.equalsIgnoreCase("Left")) {
			newX += -1;			
		} else if (direction.equalsIgnoreCase("Right")) {
			newX += 1;			
		} else {
			System.out.println("Invalid Move");
		}
		game.gameState[oldX][oldY] = null;
		game.gameState[newX][newY] = this;
	}
	
	public void Attack(MonsterGame game, String direction, String Attack) {
		int i = 0;
		int j = 0;
		if (direction == "up") {
			i = game.player.pos[0] + 1;
		} else if (direction == "down") {
			i = game.player.pos[0] - 1;
		} else if (direction == "left") {
			j = game.player.pos[0] - 1;			
		} else if (direction == "right") {
			j = game.player.pos[0] + 1;			
		} else {
			System.out.println("Invalid Move");
		}
		if (Attack.equalsIgnoreCase("attack")) {
			if (game.gameState[i][j].getClass() != null) {
				// find the monster
				for (GameObject m : game.gameObjects) {
					if (m.pos[0] == i && m.pos[1] == j) {
						// attack it
						if (m.HP - dmgList.get(Attack) < 0) {
							int oldX = game.player.pos[0];
							int oldY = game.player.pos[1];
							int newX = i;
							int newY = j;
							game.gameState[oldX][oldY] = null;
							game.gameState[newX][newY] = this;
						} else {
							m.HP -= baseDMG;
						}
					}
			}
		}
		} else if (skillSet.containsKey(Attack)) {
			if (game.gameState[i][j].getClass() != null)  {
				// find the monster
				for (GameObject m : game.gameObjects) {
					if (m.pos[0] == i && m.pos[1] == j) {
						// attack it
						if (m.HP - dmgList.get(Attack) < 0) {
							int oldX = game.player.pos[0];
							int oldY = game.player.pos[1];
							int newX = i;
							int newY = j;
							game.gameState[oldX][oldY] = null;
							game.gameState[newX][newY] = this;
						} else {
							m.HP -= dmgList.get(Attack);
						}
					}
				}
			} else {
			System.out.println("Invalid Attack");
			}
		}
	}

	public void generateSkillSet(String Class) {
		if (Class.equalsIgnoreCase("Warrior")) {
			String[] warriorSet = new String[3];
			warriorSet[0] = "ShieldBang";
			warriorSet[1] = "Slam";
			warriorSet[2] = "Rage";
			skillSet.put("Warrior", warriorSet);
			this.baseDMG = 35;
		} else if (Class.equalsIgnoreCase("Mage")) {
			String[] mageSet = new String[3];
			mageSet[0] = "StaffBang";
			mageSet[1] = "Fireball";
			mageSet[2] = "Earthquake";
			skillSet.put("Warrior", mageSet);
			this.baseDMG = 10;
		} else if (Class.equalsIgnoreCase("Thief")) {
			String[] thiefSet = new String[3];
			thiefSet[0] = "Rob";
			thiefSet[1] = "BackStab";
			thiefSet[2] = "Assassinate";
			skillSet.put("Warrior", thiefSet);
			this.baseDMG = 28;
		} else {
			System.out.println("Invalid Class");
		}
		dmgList.put("ShieldBang", 20);
		dmgList.put("Slam", 60);
		dmgList.put("Rage", 150);
		dmgList.put("StaffBang", 20);
		dmgList.put("Fireball", 60);
		dmgList.put("Earthquake", 150);
		dmgList.put("Rob", 20);
		dmgList.put("BackStab", 60);
		dmgList.put("Assassinate", 150);
	}
	
	public void LevelUp() {
		this.lvl += 1;
		EXP = -30 * lvl;
	}
	
	public void showHP() {
		System.out.println(this.HP);
	}
	
	public void showSkills() {
		System.out.println(skillSet.keySet());
	}

}

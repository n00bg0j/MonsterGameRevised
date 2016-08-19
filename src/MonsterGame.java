import java.util.ArrayList;

public class MonsterGame {
	// Player
	Player player;
	// Game State
	GameObject[][] gameState = new GameObject[10][10];
	// list of living monsters
	ArrayList<GameObject> monstersLeft;
	// list of living allies
	ArrayList<GameObject> alliesLeft;
	
	// let's use the default constructor
	
	// generate the map
	public GameObject[][] generateMap() {
		for (int i = 0; i < gameState.length; i++) {
			for (int j = 0; j < gameState[i].length; j++) {
				// randomly generate walls
				int numWalls = RandIntGen.randInt(20, 30);
				GenerateObject(numWalls, "Wall");
				// randomly generate monsters
				int numMonsters = RandIntGen.randInt(10, 20);
				GenerateObject(numMonsters, "Monster");
				// randomly generate allies
				int numAllies = RandIntGen.randInt(5, 10);
				GenerateObject(numAllies, "Ally");
				// add monsters to the game state
				// add walls to the game state
				// add allies to the game state
			}
		}
		return gameState;
	}

	private GameObject GenerateObject(int numObjects, String Type) {
		for (int i = 0; i < numObjects; i++) {
			int x = RandIntGen.randInt(0, 9);
			int y = RandIntGen.randInt(0, 9);
			// find a non empty spot
			while (gameState[x][y] != null) {
				x = RandIntGen.randInt(0, 9);
				y = RandIntGen.randInt(0, 9);
			}
			if (Type.equalsIgnoreCase("Wall")) {
				GameObject newWall = new Wall(x, y);
				monstersLeft.add(newWall);
				gameState[x][y] = newWall;
			} else if (Type.equalsIgnoreCase("Monster")) {
				GameObject newMonster = new Monster(x, y);
				monstersLeft.add(newMonster);
				gameState[x][y] = newMonster;
			} else if (Type.equalsIgnoreCase("Ally")) {
				GameObject newAlly = new Ally(x, y);
				monstersLeft.add(newAlly);
				gameState[x][y] = newAlly;				
			} else {
				System.out.println("You fucked up");
			}

		}
	}
}

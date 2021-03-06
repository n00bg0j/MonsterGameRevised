import java.util.ArrayList;

public class MonsterGame {
	// Player
	Player player;
	// Game State
	GameObject[][] gameState = new GameObject[10][10];
	// list of living monsters
	ArrayList<GameObject> monstersLeft = new ArrayList<GameObject>();
	// list of allies
	ArrayList<GameObject> alliesLeft = new ArrayList<GameObject>();
	// list of gameObjects
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	// let's use the default constructor
	
	// generate the map
	public void generateMap() {
		for (int i = 0; i < gameState.length; i++) {
			for (int j = 0; j < gameState[i].length; j++) {
				// randomly generate walls
				int numWalls = RandIntGen.randInt(10, 15);
				GenerateObject(numWalls, "Wall");
				// randomly generate monsters
				int numMonsters = RandIntGen.randInt(5, 10);
				GenerateObject(numMonsters, "Monster");
				// randomly generate allies
				int numAllies = RandIntGen.randInt(3, 5);
				GenerateObject(numAllies, "Ally");
				// randomly generate paths
				int numPaths = RandIntGen.randInt(30, 50);
				GenerateObject(numPaths, "Path");
			}
		}
	}
	
	public void createPlayer(String name, String job) {
		this.player = new Player(this, name, job);
		this.gameState[player.pos[0]][player.pos[1]] = player;
	}

	public void GenerateObject(int numObjects, String Type) {
		for (int i = 0; i < numObjects; i++) {
			int x = RandIntGen.randInt(0, 9);
			int y = RandIntGen.randInt(0, 9);
			// find a non empty spot
			if (gameState[x][y] == null) {
				x = RandIntGen.randInt(0, 9);
				y = RandIntGen.randInt(0, 9);
			}
			if (Type.equalsIgnoreCase("Wall")) {
				GameObject newWall = new Wall(x, y);
				gameObjects.add(newWall);
				gameState[x][y] = newWall;
			} else if (Type.equalsIgnoreCase("Monster")) {
				GameObject newMonster = new Monster(x, y);
				monstersLeft.add(newMonster);
				gameObjects.add(newMonster);
				gameState[x][y] = newMonster;
			} else if (Type.equalsIgnoreCase("Ally")) {
				GameObject newAlly = new Ally(x, y);
				alliesLeft.add(newAlly);
				gameObjects.add(newAlly);
				gameState[x][y] = newAlly;
			}  else if (Type.equalsIgnoreCase("Path")) {
				GameObject newPath = new Path(x, y);
				alliesLeft.add(newPath);
				gameObjects.add(newPath);
				gameState[x][y] = newPath;
			} else {
				System.out.println("You fucked up");
			}
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < 30; i++) {
			result += "-";
		}
		result += "\n";
		for (int i = 0; i < gameState.length; i++) {
			for (int j = 0; j < gameState.length; j++)
				if (gameState[i][j] == null)
					result += "| |";
				else
					result += "|" + gameState[i][j].type + "|";
			result += "\n";
		}
		for (int i = 0; i < 30; i++) {
			result += "-";
		}
		return result;
	}
}

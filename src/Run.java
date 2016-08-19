import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Run {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Create an instance of the game!
		MonsterGame newGame = new MonsterGame();
		// generate the map for this game
		newGame.generateMap();
		// create player
		System.out.println("Enter player name");
		String playerName = br.readLine();
		System.out.println("Choose a job");
		String playerJob = br.readLine();
		newGame.createPlayer(playerName, playerJob);
		// display the game
		System.out.println(newGame);
		
		while (true) {
			System.out.println("Next Move?");
			String move = br.readLine();
			String[] input = move.split(" ");
			if (move.equalsIgnoreCase("q")) {
				System.out.println("Game Over");
				break;
			} else if (move.equalsIgnoreCase("hp")) {
				System.out.println(newGame.player.HP);
			} else if (move.equalsIgnoreCase("Skills")) {
				newGame.player.showSkills();
			} else if (move.equalsIgnoreCase("Move")) {
				newGame.player.Move(newGame, "Down");
			} else if (input[0].equalsIgnoreCase("Attack")) {
				newGame.player.Attack(newGame, input[1], "Attack");
			} else if (newGame.player.skillSet.containsKey(input[0])) {
				newGame.player.Attack(newGame, input[1], input[0]);
			} else {
				System.out.println("Invalid move!");
			}
			// remember to get rid of dead monsters
			for (GameObject monster : newGame.monstersLeft) {
				if (monster.alive == false) {
					int x = monster.pos[0];
					int y = monster.pos[0];
					newGame.gameState[x][y] = new Path(x, y);
					newGame.monstersLeft.remove(monster);
				}
				//monster.Move(newGame);
				//monster.Attack(newGame);
			}
			System.out.println(newGame);
		}
	}
}

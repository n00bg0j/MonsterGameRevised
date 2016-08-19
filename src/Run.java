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
		// display the game
		System.out.println(newGame);
		
	}
}


public class Ally extends GameObject{
	public Ally(int x, int y) {
		// effects
		this.HP = RandIntGen.randInt(500, 750);
		this.pos[0] = x;
		this.pos[1] = y;
		this.type = "A";
	}
}

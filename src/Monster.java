
public class Monster extends GameObject {
	public Monster(int x, int y) {
		this.HP = RandIntGen.randInt(100, 1000);
		this.type = "M";
		this.pos[0] = x;
		this.pos[1] = y;
	}
}

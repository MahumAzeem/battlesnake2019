package io.battlesnake.starter;
import com.fasterxml.jackson.databind.JsonNode;

public class XY{
	protected int x;
	protected int y;

	public XY() {
		Position(0,0);
	}
	public XY(int x) {
		Position(x, 0);
	}
	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}

	public int[] geta() {
		int[] a = new int[2];
		a[0] = x;
		a[1] = y;
		return a;
	}

	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}

}
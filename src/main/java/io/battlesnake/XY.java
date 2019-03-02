package io.battlesnake.starter;
import com.fasterxml.jackson.databind.JsonNode;

public class XY{
	protected int x;
	protected int y;

	public XY() {
		XY(0,0);
	}
	public XY(int x) {
		XY(x, 0);
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

	public void seta(int[] a) {
		x = a[0];
		y = a[1];
	}

	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}

	public int getDistance(XY other) {
		int dx = other.getx() - this.x;
		int dy = other.gety() - this.y;
		if (dx < 0) dx *= -1;
		if (dy < 0) dy *= -1;
		return dx+dy;
	}

}
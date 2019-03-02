package io.battlesnake.starter;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;

public class Snek {
	public String id;
	public String name;
	public int health;
	public ArrayList<XY> body;

	public Snek(String id, String name, int health) {
		this.id = id;
		this.name = name;
		this.health = health;
		body = new ArrayList<XY>();
	}

	public void grow() {
		XY t = new XY();
		t.seta(body.get(getTailInd()).geta());
		body.add(t);
	}

	public int getTailInd() {
		return body.size()-1;
	}

	public XY getHead() {
		return body.get(0);
	}

	public void move(XY newpos) {
		body.remove(getTailInd());
		body.add(newpos, 0);
			
	}

	public void addBod(XY bod) {
		body.add(XY);
	}

	public int size() {
		return body.size();
	}

	public void sethp(int hp) {
		this.health = hp;
	}
	public int gethp() {
		return health;
	}
	public void dechp() {
		this.health--;
	}


}
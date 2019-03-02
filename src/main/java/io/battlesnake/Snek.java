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


	/*-------------------------------------
				Body Methods
	---------------------------------------*/
	//adds a body part, intended only for initializing
	public void addBod(XY bod) {
		body.add(bod);
	}

	//adds an extra part at the tail
	public void grow() {
		XY t = new XY();
		t.seta(body.get(getTailInd()).geta());
		body.add(t);
	}

	//gets the index of the tail
	public int getTailInd() {
		return body.size()-1;
	}

	//gets the XY of the head
	public XY getHead() {
		return body.get(0);
	}

	//removes the tail, adds a new XY for the head
	public void move(XY newpos) {
		body.remove(getTailInd());
		body.add(0, newpos);
			
	}

	//returns the size of the snake
	public int size() {
		return body.size();
	}

	/*-------------------------------------
				Health Methods
	---------------------------------------*/
	//sets hp
	public void sethp(int hp) {
		this.health = hp;
	}
	//returns hp
	public int gethp() {
		return health;
	}

	/*-------------------------------------
				ID/Name Methods
	---------------------------------------*/
	//sets the id
	public void setID(String id) {
		this.id = id;
	}
	//returns the id
	public String getID() {
		return id;
	}
	//sets the name
	public void setName(String name) {
		this.name = name;
	}
	//returns the name
	public String getName() {
		return name;
	}

}
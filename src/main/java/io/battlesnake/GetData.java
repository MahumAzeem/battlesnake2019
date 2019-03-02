package io.battlesnake.starter;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;



public class GetData {
	protected String id;
	protected int turn;
	protected int[][] board;
	protected int height;
	protected int width;
	protected XY[] food;
	protected Snek[] snakes;
	protected Snek you;

	//initializes the data
	public GetData(JsonNode data) {
		id = data.get("game").get("id").asText();
		turn = data.get("turn").asInt();
		height = data.get("board").get("height").asInt();
		width = data.get("board").get("width").asInt();
		board = new int[width][height];
		refood(data);
		initsnakes(data);
		initself(data);
	}

	public void refood(JsonNode data) {
			JsonNode foods = data.get("board").get("food");
			food = new XY[foods.size()];
			for (int i = 0; i < foods.size(); i++) {
				int x = foods.get(i).get("x").asInt();
				int y = foods.get(i).get("y").asInt();
				food[i] = new XY(x,y);
			}
	}

	public boolean hasfood(XY pos) {
		for (int i = 0; i < food.length; i++) {
			if (food[i].getDistance(pos) == 0) {
				return true;
			}
		}
	}

	private void initsnakes(JsonNode data) {
		JsonNode enemies = data.get("board").get("snakes");
		snakes = new Snek[enemies.size()];
		for(int i = 0; i < enemies.size(); i++) {
			snakes[i] = initsnake(enemies.get(i));
		}
	}

	private void initself(JsonNode data) {
		you = initsnake(data.get("you"));
	}

	private Snek initsnake(JsonNode snake) {
		Snek temp = new Snek(snake.get("id").asText(), snake.get("name").asText(), snake.get("health").asInt());
		int size = snake.get("body").size();
		for (int j = 0; j < size; j++) {
			temp.addBod(new XY(snake.get("body").get(j).get("x").asInt(), snake.get("body").get(j).get("y").asInt()));
		}
		return temp;
	}

	public Snek findID(String id) {
		for (int i = 0; i < snakes.length; i++) {
			if (snakes[i].getID().equals(id)) {
				return snakes[i];
			}
		}
		return null;
	}

	public Snek getYou() {
		return you;
	}

	public boolean occupied(XY pos) {
		if (occupied(you, pos)) return true;
		for (int i = 0; i < snakes.length; i++) {
			if (occupied(snakes[i], pos)) return true;
		}
		return false;
	}

	private boolean occupied(Snek snake, XY pos) {
		return snake.occupies(pos);
	}

}
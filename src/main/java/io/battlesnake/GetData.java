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

	//updates the data
	public void update(JsonNode data) {
		turn = data.get("turn").asInt();
		JsonNode enemies = data.get("snakes");
		for (int i = 0; i < enemies.size(); i++) {
			JsonNode enemy = enemies.get(i);
			Snek t = findID(enemy.get("id"));
			updateSnake(enemy, t);
		}
		updateSnake(data.get("you"),you);

		refood(data);
	}

	public void refood(JsonNode data) {
			JsonNode foods = data.get("board").get("food");
			food = new XY[food.size()];
			for (int i = 0; i < food.size(); i++) {
				int x = foods.get(i).get("x").asInt();
				int y = foods.get(i).get("y").asInt();
				food[i] = new XY(x,y);
			}
	}

	private void initsnakes(JsonNode data) {
		JsonNode enemies = data.get("snakes");
		snakes = new Snek[enemies.size()];
		for(int i = 0; i < enemies.size(); i++) {
			snakes[i] = initsnake(enemies.get(i));
		}
	}

	private void initself(JsonNode data) {
		you = initsnake(data.get("you"));
	}

	private Snek initsnake(JsonNode snake) {
		Snek temp = new Snek(snake.get("id"), snake.get("name"), snake.get("health"));
		int size = snake.get("body").size();
		if (size == 0) continue; //it's dead
		for (int j = 0; j < size; i++) {
			temp.addBod(new XY(snake.get("body").get("x").asInt(),
						snake.get("body").get("y").asInt()));
		}
		return temp;
	}

	public Snek findID(String id) {
		for (int i = 0; i < snakes.length; i++) {
			if (snakes[i].getID().equals(id)) {
				return snakes[i];
			}
		}
	}

	private void updateSnake(JsonNode snake, Snek stored) {
		JsonNode head = snake.get("body").get(0);
		XY h = new XY(head.get("x"), head.get("y"));
		stored.move(h);
		if (snake.get("body").size() > stored.size()) {
			stored.grow();
		}
		stored.sethp(snake.get("health"));
	}

	public Snek getYou() {
		return you;
	}

	public occupied(XY pos) {
		if (occupied(you, pos)) return true;
		for (int i = 0; i < snakes.length; i++) {
			if (occupied(snakes[i], pos)) return true;
		}
		return false;
	}

	private occupied(Snek snake, XY pos) {
		for (int i = 0; i < snake.body.size(); i++) {
			if (snake.body.get(i).getDistance(pos) == 0) {
				return true;
			}
		}
		return false;
	}

}
package io.battlesnake.starter;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;

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
		FileHandler fh;
		try {
			fh = new FileHandler("./logs/log", true);
			Logger logger = Logger.getLogger("logtest");
			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			logger.log(Level.INFO, "Init");

			id = data.get("game").get("id").asText();
			logger.log(Level.INFO, "id");
			turn = data.get("turn").asInt();
			logger.log(Level.INFO, "turn");
			height = data.get("board").get("height").asInt();
			logger.log(Level.INFO, "height");
			width = data.get("board").get("width").asInt();
			logger.log(Level.INFO, "width");
			board = new int[width][height];
			refood(data);
			logger.log(Level.INFO, "food");
			initsnakes(data);
			logger.log(Level.INFO, "snakes");
			initself(data);
			logger.log(Level.INFO, "you");
		}
		catch(Exception e) {
		}
	}

	/*
	//updates the data
	public void update(JsonNode data) {
		turn = data.get("turn").asInt();
		JsonNode enemies = data.get("snakes");
		for (int i = 0; i < enemies.size(); i++) {
			JsonNode enemy = enemies.get(i);
			Snek t = findID(enemy.get("id").asText());
			if (t==null) continue;
			updateSnake(enemy, t);
		}
		updateSnake(data.get("you"),you);

		refood(data);
	}
	*/

	public void refood(JsonNode data) {
			JsonNode foods = data.get("board").get("food");
			food = new XY[foods.size()];
			for (int i = 0; i < foods.size(); i++) {
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
		for (int i = 0; i < snake.body.size(); i++) {
			if (snake.body.get(i).getDistance(pos) == 0) {
				return true;
			}
		}
		return false;
	}

}
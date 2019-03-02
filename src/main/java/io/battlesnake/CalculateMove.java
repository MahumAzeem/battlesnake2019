package io.battlesnake.starter;
import java.util.ArrayList;
import java.util.Random;

public class CalculateMove{
  public String nextMove;

  public CalculateMove(GetData data){
  	//nextMove = "down";
  	foodChaser(data);
  }

  public String getMove(){
    return this.nextMove;
  }

  public void foodChaser(GetData data) {
  	if (data.food.length > 0) {
  		foodChaser(data.food[0], data.you);
  		return;
  	}
  	Random rand = new Random();
  	int n = rand.nextInt(4);
  	switch(n) {
  		case 0:
  			nextMove = "up";
  			break;
  		case 1:
  			nextMove = "right";
  			break;
  		case 2:
  			nextMove = "left";
  			break;
  		default:
  			nextMove = "down";
  	}
  }

  public void foodChaser(XY food, Snek snake) {
  	XY pos = snake.getHead();
  	int x = food.getx() - pos.getx();
  	if (x > 0) {
  		XY check = new XY(pos.getx()+1, pos.gety());
  		if (!snake.occupies(check)) {
  			nextMove = "right";
  			return;
  		}
  	}
  	else if (x < 0) {
  		XY check = new XY(pos.getx()-1, pos.gety());
  		if (!snake.occupies(check)) {
  			nextMove = "left";
  			return;
  		}
  	}
  	x = food.gety() - pos.gety();
  	if (x > 0) {
  		XY check = new XY(pos.getx(), pos.gety()+1);
  		if (!snake.occupies(check)) {
  			nextMove = "down";
  			return;
  		}
  	}
  	nextMove = "up";
  }

}

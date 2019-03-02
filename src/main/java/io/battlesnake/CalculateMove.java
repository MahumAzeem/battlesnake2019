package io.battlesnake.starter;
import java.util.ArrayList;

public class CalculateMove{
  public String nextMove;

  public CalculateMove(GetData data){
  	//nextMove = "down";
  	foodChaser(data.food[0], data.you);
  }

  public String getMove(){
    return this.nextMove;
  }

  public foodChaser(XY food, Snek snake) {
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

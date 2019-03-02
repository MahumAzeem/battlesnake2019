package io.battlesnake.starter;
import java.util.ArrayList;

public class CalculateMove{
  public String nextMove;

  public CalculateMove(GetData data){
  	if (data.food.length > 0 && data.food[0].getx() < data.width/2) {
  		nextMove = "right";
  	}
  	else {
    	nextMove = "down";
    }
  }

  public String getMove(){
    return this.nextMove;
  }

}

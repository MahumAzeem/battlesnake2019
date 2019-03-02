package io.battlesnake.starter;
import java.util.ArrayList;

public class CalculateMove{
  public String nextMove;

  public CalculateMove(GetData dataparser){
    nextMove = "down";
  }

  public String getMove(){
    return this.nextMove;
  }

}

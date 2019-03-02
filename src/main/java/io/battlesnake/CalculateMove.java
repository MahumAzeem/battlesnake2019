package io.battlesnake.starter;
import java.util.ArrayList;

public class CalculateMove(GetData dataparser){
  public String nextMove;

  public CalculateMove(GetData dataparser){
    nextMove = "down";
  }

  public String getNextMove(){
    return nextMove;
  }
}

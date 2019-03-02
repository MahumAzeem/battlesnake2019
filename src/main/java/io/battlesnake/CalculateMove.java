package io.battlesnake.starter;
import java.util.ArrayList;

public class CalculateMove{
  public String nextMove;

  public CalculateMove(GetData dataparser){
    nextMove = "down";
  }

  public static String getMove(){
    return this.nextMove;
  }

}

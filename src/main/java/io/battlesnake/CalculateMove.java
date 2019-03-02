package io.battlesnake.starter;
import java.util.ArrayList;

public class CalculateMove{
  public String nextMove;
  public GetData data;

  public CalculateMove(GetData data){
  	//nextMove = "down";
  	this.data = data;
  }

  public String foodChaser() {
    Snek you = data.you;
    int foodind = 0;
    int mindis = 9999;
    XY[] food = data.food;
    for (int i = 0; i < food.length; i++) {
      if (you.getHead().getDistance(food[i]) < mindis) {
        mindis = you.getHead().getDistance(food[i]);
        foodind = i;
      }
    }
    if (food.length > 0) {
      String fc = foodChaser(food[foodind]);
      if (!fc.equals("escape")) {
        return fc;
      }
    }

    XY headpos = you.getHead();
    int ww = wallWarning(headpos, data.width, data.height);
    if (ww == 1 || ww == 2) {
      if (data.occupied(new XY(headpos.getx(), headpos.gety() -1 )) || data.danger(new XY(headpos.getx(), headpos.gety() -1 ))) {
        return "down";
      }
      else {
        return "up";
      }
    }
    if (ww == 10 || ww == 20) {
      if (data.occupied(new XY(headpos.getx()+1, headpos.gety())) || data.danger(new XY(headpos.getx()+1, headpos.gety())) ) {
        return "left";
      }
      else {
        return "right";
      }
    }
    if (ww == 11) {
      if (data.occupied(new XY(headpos.getx()+1, headpos.gety()))) {
        return "down";
      }
      else {
        return "right";
      }
    }
    if (ww == 12) {
      if (data.occupied(new XY(headpos.getx()-1, headpos.gety()))) {
        return "down";
      }
      else {
        return "left";
      }
    }
    if (ww == 21) {
      if (data.occupied(new XY(headpos.getx()+1, headpos.gety()))) {
        return "up";
      }
      else {
        return "right";
      }
    }
    if (ww == 22) {
      if (data.occupied(new XY(headpos.getx()-1, headpos.gety()))) {
        return "up";
      }
      else {
        return "left";
      }
    }

    
    if (!(data.occupied(new XY(headpos.getx(), headpos.gety() -1 )) || data.danger(new XY(headpos.getx(), headpos.gety() -1 )))) {
      return "up";
    }
    if (!(data.occupied(new XY(headpos.getx(), headpos.gety() + 1 )) || data.danger(new XY(headpos.getx(), headpos.gety() +1 )))) {
      return "down";
    }
    if (!(data.occupied(new XY(headpos.getx()+1, headpos.gety())) || data.danger(new XY(headpos.getx()+1, headpos.gety())))) {
      return "right";
    }

    if (!(data.occupied(new XY(headpos.getx()-1, headpos.gety())) || data.danger(new XY(headpos.getx()-1, headpos.gety())))) {
      return "left";
    }    
    
    return null; //go forward?
  }

  private String foodChaser(XY food) {
    XY pos = data.you.getHead();
    int x = food.getx() - pos.getx();
    if (x > 0) {
      XY check = new XY(pos.getx()+1, pos.gety());
      if (!data.occupied(check) && !data.danger(check)) {
        return "right";
      }
    }
    else if (x < 0) {
      XY check = new XY(pos.getx()-1, pos.gety());
      if (!data.occupied(check) && !data.danger(check)) {
        return "left";
      }
    }
    x = food.gety() - pos.gety();
    if (x > 0) {
      XY check = new XY(pos.getx(), pos.gety()+1);
      if (!data.occupied(check) && !data.danger(check)) {
        return "down";
      }
    }
    else if (x < 0) {
      XY check = new XY(pos.getx(), pos.gety()-1);
      if (!data.occupied(check) && !data.danger(check)) {
        return "up";
      }
    }
    return "escape";
  }

  public int wallWarning(XY head, int width, int height) {
  	int x = 1; //1-left wall, 2-right wall
  	int y = 10;//1-up wall, 2-down wall
  	if (head.getx() == 0) {
  	}
  	else if (head.getx() == width-1) {
  		x*=2;
  	}
  	else {
  		x = 0;
  	}
  	if (head.gety() == 0) {
  	}
  	else if (head.gety() == height-1) {
  		y *= 2;
  	}
  	else {
  		y = 0;
  	}

  	return x+y;
  }

  public String getMove(){
    return getMove(data.you.getHead(),data.snakes);
  }

  private String getMove(XY headpos, Snek[] snakes){
    ArrayList<XY> allpos = new ArrayList<XY>();
    for (int i = 0; i < snakes.length; i++) {
      allpos.addAll(snakes[i].body);
    }
    XY[] enemies = new XY[allpos.size()];
    enemies = allpos.toArray(enemies);


    XY newPos = new XY(headpos.getx(),headpos.gety());
    int ww = wallWarning(headpos, data.width, data.height);

    //////////////checks y//////////////////
    if (data.occupied(new XY(headpos.getx()-1, headpos.gety()))   &&  data.occupied(new XY(headpos.getx()+1, headpos.gety()))){
      /*    O
          X S X
            O
      */
    }

    else if (data.occupied(new XY(headpos.getx()-1, headpos.gety())) || ww==1 || ww==11 || ww==21 ) {
        /*    O
            X S O
              O
        */
        newPos.setx(headpos.getx() + 1);
    }
    else if (data.occupied(new XY(headpos.getx()+1, headpos.gety()))|| ww==2 || ww==12 || ww==22){
      /*    O
          O S X
            O
      */
      newPos.setx(headpos.getx() - 1);
    }

    /////////////checks y///////////////////
    if (     (data.occupied(new XY(headpos.getx(), headpos.gety()-1)) && data.occupied(new XY(headpos.getx(), headpos.gety()+1))   )
          || (data.occupied(new XY(headpos.getx(), headpos.gety()-1)) && (ww==11 ||ww==12||ww==10) )
          || (data.occupied(new XY(headpos.getx(), headpos.gety()+1)) && (ww==21 ||ww==22||ww==20) )      ){
      /*    X
          O S O
            X
      */
    }

    else if (data.occupied(new XY(headpos.getx(), headpos.gety()-1))|| ww==20 || ww==21 || ww==22){
      /*    O
          O S O
            X
      */
      newPos.sety(headpos.gety() + 1);
    }
    else if (data.occupied(new XY(headpos.getx(), headpos.gety()+1)) || ww==10 || ww==11 || ww==12 ){
      /*    X
          O S O
            O
      */
      newPos.sety(headpos.gety() - 1);
    }

    int currx=headpos.getx();
    int curry=headpos.gety();
    int newx=newPos.getx();
    int newy=newPos.gety();
    ///////////////TURN FROM XY TO DIRECTIONS///////////
    if((currx-newx) <= -1){
      return "right";
    }
    else if((currx-newx) >= 1){
      return "left";
    }
    else if((curry-newy) >= 1){
      return "up";
    }
    else if((curry-newy) <= -1){
      return "down";
    }
    return null;
  }


}

package io.battlesnake.starter;
import java.util.ArrayList;

public class CalculateMove{
  public String nextMove;
  public GetData data;

  public CalculateMove(GetData data){
  	//nextMove = "down";
  	this.data = data;
  }



  /*public void foodChaser(GetData data) {
  	if (data.food.length > 0) {
  		foodChaser(data.food[0], data.you);
  		return;
  	}
  	XY headpos = data.you.getHead();
  	int ww = wallWarning(headpos, data.width, data.height);
  	if (ww == 1 || ww == 2) {
  		if (data.occupied(new XY(headpos.getx(), headpos.gety() -1 ))) {
  			nextMove = "down";
  		}
  		else {
  			nextMove = "up";
  		}
  	}
  	if (ww == 10 || ww == 20) {
  		if (data.occupied(new XY(headpos.getx()-1, headpos.gety()))) {
  			nextMove = "left";
  		}
  		else {
  			nextMove = "right";
  		}
  	}
  	if (ww == 11) {
  		if (data.occupied(new XY(headpos.getx()+1, headpos.gety()))) {
  			nextMove = "down";
  		}
  		else {
  			nextMove = "right";
  		}
  	}
  	if (ww == 12) {
  		if (data.occupied(new XY(headpos.getx()-1, headpos.gety()))) {
  			nextMove = "down";
  		}
  		else {
  			nextMove = "left";
  		}
  	}
  	if (ww == 21) {
  		if (data.occupied(new XY(headpos.getx()+1, headpos.gety()))) {
  			nextMove = "up";
  		}
  		else {
  			nextMove = "right";
  		}
  	}
  	if (ww == 22) {
  		if (data.occupied(new XY(headpos.getx()+1, headpos.gety()))) {
  			nextMove = "up";
  		}
  		else {
  			nextMove = "left";
  		}
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
*/
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

  public String getMove(XY headpos, XY[] enemies){
    XY newPos = new XY(headpos.getx(),headpos.gety())
    int ww = wallWarning(headpos, data.width, data.height);

    //////////////checks y//////////////////
    if (data.occupied(new XY(headpos.getx()-1, headpos.gety()))   &&  data.occupied(new XY(headpos.getx()+1, headpos.gety()))){
      /*    O
          X S X
            O
      */
    }

    else if (data.occupied(new XY(headpos.getx()-1, headpos.gety()))  || ww == 1) {
        /*    O
            X S O
              O
        */
        newPos.setx(headpos.getx() + 1);
    }
    else if (data.occupied(new XY(headpos.getx()+1, headpos.gety()))  ||  ww == 2){
      /*    O
          O S X
            O
      */
      newPos.setx(headpos.getx() - 1);
    }

    /////////////checks y///////////////////
    if (data.occupied(new XY(headpos.getx(), headpos.gety()-1)) && data.occupied(new XY(headpos.getx(), headpos.gety()+1))){
      /*    X
          O S O
            X
      */
    }

    else if (data.occupied(new XY(headpos.getx(), headpos.gety()-1)) || ww == 20){
      /*    O
          O S O
            X
      */
      newPos.sety(headpos.gety() + 1);
    }
    else if (data.occupied(new XY(headpos.getx(), headpos.gety()+1)) || ww == 10){
      /*    X
          O S O
            O
      */
      newPos.sety(headpos.gety() - 1);
    }

    int currx=headpos.getx();
    int curry=headpos.gety()
    int newx=newPos.getx();
    int newy=newPos.gety();
    ///////////////TURN FROM XY TO DIRECTIONS///////////
    if(currx-newx) == 1){
      return "right";
    }
    else if((currx-newx) == -1){
      return "left";
    }
    else if((curry-newy) == 1){
      return "up";
    }
    else if((curry-newy) == -1){
      return "down";
    }
    return null;
  }


}

package bomberMan;

import java.util.Scanner;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class rgMenu extends BasicGameState{

	/*Sample encapsulation.*/
	private int ID;
	private StateBasedGame game;
	private Game gameScreen;
	private String player1=null;
	private String player2=null;
	static Scanner scan=new Scanner(System.in);
	int count=0;
	/*Required constructor. When you create a new screen/state
	 * you will need to fill an ID with the passed ID. This
	 * will be then used by the getID function you have to 
	 * override. This function is used to get the ID of your screen/state
	 * by the game engine.*/
	public rgMenu(int ID) {
		// TODO Auto-generated constructor stub
		this.ID=ID;
		this.player1=player1;
		this.player2=player2;
	}
	
	
	/*This inherited abstract function needs to be overriden.*/
	public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // TODO Auto-generated method stub
		
		if(container.getInput().isKeyDown(Input.KEY_1)){
			   game.enterState(theGame.PLAYSTATEID);//, new FadeOutTransition(org.newdawn.slick.Color.black), new FadeInTransition(org.newdawn.slick.Color.black));
		}
		if(count==0)
		{
			System.out.println("--------------------------------------------------------");
			System.out.println("Please Enter player 1's name");
			player1=scan.next();
			System.out.println("Welcome "+ player1);
			System.out.println("--------------------------------------------------------");
			System.out.println("Please Enter player 2's name");
			player2=scan.next();
			System.out.println("Welcome "+ player2);
			System.out.println("--------------------------------------------------------");
			System.out.println("Thank you for entering you're names");
			System.out.println("Please return to the game screen ");
			count+=1;
		}
		
		
		
    }
	/*This inherited abstract function needs to be overriden.*/
	 public void render(GameContainer container, StateBasedGame game, Graphics g)
	            throws SlickException {
		 /*Use the passed parameter g containing a Graphics
		  * object we can use to draw to our screen.
		  */
		g.setColor((org.newdawn.slick.Color.green));
 	    g.drawString("BomberMan", 150, 10);
 	    g.drawString("1. Play the Game", 150, 100);
 	    g.drawString("2. High Scores", 150, 120);
 	    g.drawString("3. Quit", 150, 140);
 	    g.drawString("*****************Please enter your names in the console*****************", 150, 160);
 	    
 	    
	 }
	 /*This inherited abstract function needs to be overriden.*/
	 public void init(GameContainer container, StateBasedGame game)
	            throws SlickException {
	        // TODO Auto-generated method stub
	    	//this.game=game;
	 }
	 /*This inherited abstract function needs to be overriden.*/
	 public int getID() {
	        // TODO Auto-generated method stub
	        return ID;
	 }
	
	 
	
}

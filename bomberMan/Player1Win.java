package bomberMan;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Player1Win extends BasicGameState{

	/*Sample encapsulation.*/
	private int ID;
	private StateBasedGame game;
	private Game gameScreen;
	
	/*Required constructor. When you create a new screen/state
	 * you will need to fill an ID with the passed ID. This
	 * will be then used by the getID function you have to 
	 * override. This function is used to get the ID of your screen/state
	 * by the game engine.*/
	public Player1Win(int ID) {
		// TODO Auto-generated constructor stub
		this.ID=ID;
	}
	
	/*This inherited abstract function needs to be overriden.*/
	public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // TODO Auto-generated method stub
		
		if(container.getInput().isKeyDown(Input.KEY_0)){
			   game.enterState(theGame.MENUSTATEID);//, new FadeOutTransition(org.newdawn.slick.Color.black), new FadeInTransition(org.newdawn.slick.Color.black));
		}
			
		game.getState(theGame.PLAYSTATEID).init(container, game);
    }
	/*This inherited abstract function needs to be overriden.*/
	 public void render(GameContainer container, StateBasedGame game, Graphics g)
	            throws SlickException {
		 /*Use the passed parameter g containing a Graphics
		  * object we can use to draw to our screen.
		  */
		 g.setColor((org.newdawn.slick.Color.green));
		 g.drawString("Player 1 Wins!", 500, 500);
	 	 g.drawString("Return to menu.....press 0", 500, 550);
 	    
 	    
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
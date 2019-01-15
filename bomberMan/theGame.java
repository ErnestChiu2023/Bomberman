package bomberMan;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class theGame extends StateBasedGame{
/*The main game state is created by inheriting from
 * StateBasedGame. This class manages all the game screens.
 * This class is the starting point for your game.
 */
	/*Give each screen a static final id value to help identify it.
	 * These are static and final because we only need one
	 * copy of them and their value will not change. They
	 * are public so that we can access them outside the class.
	 * */
	public static final int MENUSTATEID=10;
	public static final int PLAYSTATEID=11;
	public static final int Player1Win=12;
	public static final int Player2Win=13;
	
	public static final int GAMEWIDTH=1100;
	public static final int GAMEHEIGHT=1100;
	
	
	/*The class constructor. We provide a string parameter
	 * so that the creator of the object can initialize 
	 * the game screen title.*/
	 
	public theGame(String name)
	{
		/*Call parent's constructor. Initialize the game with name.*/
		super(name);
		// TODO Auto-generated constructor stub
		/*Add each state to this class as it keeps track
		 *  of all your screens/states.
		 */
		this.addState(new rgMenu(theGame.MENUSTATEID));
		this.addState(new playGame(theGame.PLAYSTATEID));
		this.addState(new Player1Win(theGame.Player1Win));
		this.addState(new Player2Win(theGame.Player2Win));
		

	}
	
	/*We must override this inherited function.*/
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		/*Initialize each of your screens.*/
		this.getState(theGame.MENUSTATEID).init(arg0, this);
		this.getState(theGame.PLAYSTATEID).init(arg0, this);
		//this.getState(GameStateManager.HELPSTATEID).init(arg0, this);
		//this.getState(GameStateManager.GAMERESULTSSTATEID).init(arg0, this);
		/*Set the start state i.e. the screen to show up when 
		 * the game begins.*/
		this.enterState(theGame.MENUSTATEID);
	}
	
	/*Our required main method to start the program.*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Create an AppGameContainer object. This will
		 * represent the basic startup game object required
		 * by the game engine. Notice that this object requires
		 * a new copy of this class where we pass it the name of
		 * the game (this shows up in the window's title bar).
		 * The object is then used to set the dimensions of the
		 * game screen/window and to set its frame rate.
		 */
		AppGameContainer appgc;
		try{
			/*Create the AppGameContainer object.
			 * Provide it a GameStateManager object.
			 * The GameStateManager object requires 
			 * a string as part of inits constructor.
			 */
			/*The following demo's polymorphism as an AppGameConatiner
			 * object's constructor requires a game object which is a 
			 * child of StateBasedGame on which our class, theGame, inherits
			 * from.
			 */
			appgc=new AppGameContainer(new theGame("BomberMan!"));//create your window and set its title bar text
			/*Set the game window size.*/
			appgc.setDisplayMode(theGame.GAMEWIDTH,theGame.GAMEHEIGHT,false);//set window size
			/*Set the game framerate.*/
			appgc.setTargetFrameRate(60);
			/*Start the game.*/
			appgc.start();
			
		}catch(SlickException e){e.printStackTrace();}
	}
}

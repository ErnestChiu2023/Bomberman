package bomberMan;

import java.util.ArrayList;

import org.lwjgl.util.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.*;
import java.util.concurrent.TimeUnit;
public class playGame extends BasicGameState {

	/*Sample encapsulation.*/
	//Initializes the variables for the game
	private int ID;
	private StateBasedGame game;
	Background background;
	Player bomber1,bomber2;
	Bomb bomb1,bomb2;
	Explosion boom1,boom2;
	Block[] blocks = new Block[25] ; 
	power power;
	fakeBlock[] fakeBlocks=new fakeBlock[75]; 

	private int gameTime;
	boolean bombCounter1=false;
	boolean bombCounter2=false;
	boolean placeBomb1=false;
	boolean placeBomb2=false;
	long buffer1,buffer2=0;

	boolean bombUpBlock1 = false;
	boolean bombRightBlock1=false;
	boolean bombUpBlock2 = false;
	boolean bombRightBlock2=false;
	Random rand = new Random();
	
	//the following method checks if the bomb explosion intersects with the player, which will kill the player
	void dead(StateBasedGame game)
	{
		if(boom1.getRectangle().intersects(bomber1.getRectangle()))
		{
			game.enterState(theGame.Player2Win);
		}
		if(boom2.getRectangle().intersects(bomber1.getRectangle()))
		{
			game.enterState(theGame.Player2Win);
		}
		if(boom1.getRectangle().intersects(bomber2.getRectangle()))
		{
			game.enterState(theGame.Player1Win);
		}
		if(boom2.getRectangle().intersects(bomber2.getRectangle()))
		{
			game.enterState(theGame.Player1Win);
		}
	}
	//the following method checks if the explosion intersects with a fake block, the fake block is destroyed
	void bomb() throws SlickException
	{
		for(int x=0;x<75;x++)
		{
			if(boom1.getRectangle().intersects(fakeBlocks[x].getRectangle()))
			{	
				if(rand.nextInt(3)==1 && power.X<0 && power.Y<0)
				{
					power.X=fakeBlocks[x].X;
					power.Y=fakeBlocks[x].Y;	
				}
				fakeBlocks[x].X=-100;
				fakeBlocks[x].Y=-100;	
			}
			if(boom2.getRectangle().intersects(fakeBlocks[x].getRectangle()))
			{	
				if(rand.nextInt(4)==1 && power.X<0 && power.Y<0)
				{
					power.X=fakeBlocks[x].X;
					power.Y=fakeBlocks[x].Y;	
				}
				fakeBlocks[x].X=-100;
				fakeBlocks[x].Y=-100;	
			}
		}
		
	}

	//the following method checks if there is a explosion to wall or explosion to player interaction
	void collisions(StateBasedGame game) throws SlickException
	{
		dead(game);
		bomb();
	}
	
	public playGame(int ID) {
		// TODO Auto-generated constructor stub
		this.ID=ID;
	}

	//the following method places the permanent walls of our game
	public void placeBlocks() throws SlickException{
		int x=1;
		int y=1;
		for (int i = 0; i < 25; i++) {
			blocks[i] = new Block(x*100,y*100);
			x+=2;
			if(x>10)
			{
				x=1;
				y+=2;
			}
		}
	}
	//the following method places the temporary walls of our game
	public void placeFakeBlocks() throws SlickException
	{
		
		int a=0;
		int b=0;
		for(int x=0;x<75;x++)
		{
			a=rand.nextInt(12);
	 		b=rand.nextInt(12);
	 		if(a%2>0)
	 		{
	 			if(b%2>0)
		 		{
		 			b+=1;
		 			a+=1;
		 		}
	 		}
	 		a*=100;
	 		b*=100;
	 		if(a<200 && b<200 || a>800 && b>800)
	 		{
	 			fakeBlocks[x]=new fakeBlock(400,500);
	 		}
	 		else
	 		{
	 			fakeBlocks[x]=new fakeBlock(a,b);
	 		}
		}
		
 		
	}

	//The following method places the permanent walls of our game
	public void placeWalls() throws SlickException{
		int x=1;
		int y=1;
		for (int i = 0; i < 25; i++) {
			blocks[i] = new Block(x*100,y*100);
			x+=2;
			if(x>10)
			{
				x=1;
				y+=2;
			}
		}
	}
	public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // TODO Auto-generated method stub

		boolean upBlocked1=false;
		boolean downBlocked1=false;
		boolean rightBlocked1=false;
		boolean leftBlocked1=false;
		boolean upBlocked2=false;
		boolean downBlocked2=false;
		boolean rightBlocked2=false;
		boolean leftBlocked2=false;
		//The following checks if the player intersects with our power sprite, if it does, their power goes up
		if(bomber1.getRectangle().intersects(power.getRectangle()))
		{
			bomber1.powerUp();
			power.X=-100;
			power.Y=-100;
		}
		//The following checks if the player intersects with our power sprite, if it does, their power goes up
		if(bomber2.getRectangle().intersects(power.getRectangle()))
		{
			bomber2.powerUp();
			power.X=-100;
			power.Y=-100;
		}
		//The following checks if the player has a block around him. 
		for(int x=0;x<25;x++)
		{
			if(bomber1.up().intersects(blocks[x].getRectangle())||bomber1.up().intersects(bomb1.getRectangle()))
			{
				upBlocked1=true;
			}
			if(bomber1.down().intersects(blocks[x].getRectangle())||bomber1.down().intersects(bomb1.getRectangle()))
			{
				downBlocked1=true;
			}
			if(bomber1.right().intersects(blocks[x].getRectangle())||bomber1.right().intersects(bomb1.getRectangle()))
			{
				rightBlocked1=true;
			}
			if(bomber1.left().intersects(blocks[x].getRectangle())||bomber1.left().intersects(bomb1.getRectangle()))
			{
				leftBlocked1=true;
			}
			//PLAYER TWO
			if(bomber2.up().intersects(blocks[x].getRectangle())||bomber2.up().intersects(bomb1.getRectangle()))
			{
				upBlocked2=true;
			}
			if(bomber2.down().intersects(blocks[x].getRectangle())||bomber2.down().intersects(bomb1.getRectangle()))
			{
				downBlocked2=true;
			}
			if(bomber2.right().intersects(blocks[x].getRectangle())||bomber2.right().intersects(bomb1.getRectangle()))
			{
				rightBlocked2=true;
			}
			if(bomber2.left().intersects(blocks[x].getRectangle())||bomber2.left().intersects(bomb1.getRectangle()))
			{
				leftBlocked2=true;
			}

			//The following checks if the player has a temporary block around him. 
			for(int x1=0;x1<75;x1++)
			{
				if(bomber1.up().intersects(fakeBlocks[x1].getRectangle()))
				{
					upBlocked1=true;
				}
				if(bomber1.down().intersects(fakeBlocks[x1].getRectangle()))
				{
					downBlocked1=true;
				}
				if(bomber1.right().intersects(fakeBlocks[x1].getRectangle()))
				{
					rightBlocked1=true;
				}
				if(bomber1.left().intersects(fakeBlocks[x1].getRectangle()))
				{
					leftBlocked1=true;
				}
				if(bomber2.up().intersects(fakeBlocks[x1].getRectangle()))
				{
					upBlocked2=true;
				}
				if(bomber2.down().intersects(fakeBlocks[x1].getRectangle()))
				{
					downBlocked2=true;
				}
				if(bomber2.right().intersects(fakeBlocks[x1].getRectangle()))
				{
					rightBlocked2=true;
				}
				if(bomber2.left().intersects(fakeBlocks[x1].getRectangle()))
				{
					leftBlocked2=true;
				}
			}

		}
		//********************************
		//PLAYER ONE MOVEMENTS
		//********************************
		if(container.getInput().isKeyDown(Input.KEY_RIGHT)&& rightBlocked1==false && bomber1.getX()<1000)
		{
			bomber1.setX(bomber1.getX()+100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(container.getInput().isKeyDown(Input.KEY_DOWN)&& downBlocked1==false && bomber1.getY()<1000)
		{
			bomber1.setY(bomber1.getY()+100);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(container.getInput().isKeyDown(Input.KEY_UP) && upBlocked1==false && bomber1.getY()>0)
		{
			bomber1.setY(bomber1.getY()-100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(container.getInput().isKeyDown(Input.KEY_LEFT)&& leftBlocked1==false && bomber1.getX()>0)
		{
			bomber1.setX(bomber1.getX()-100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(container.getInput().isKeyDown(Input.KEY_P))
		{
			if(placeBomb1==false)
			{
			placeBomb1=true;
			bomb1.setX(bomber1.getX()+5);
			bomb1.setY(bomber1.getY()+5);
			buffer1=java.time.Instant.now().getEpochSecond();
			}
		}
		

		//********************************
		//PLAYER TWO MOVEMENTS
		//********************************
		
		if(container.getInput().isKeyDown(Input.KEY_W)&& upBlocked2==false && bomber2.getY()>0)
		{
			bomber2.setY(bomber2.getY()-100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(container.getInput().isKeyDown(Input.KEY_D)&& rightBlocked2==false && bomber2.getX()<1000)
		{
			bomber2.setX(bomber2.getX()+100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(container.getInput().isKeyDown(Input.KEY_S)&& downBlocked2==false && bomber2.getY()<1000)
		{
			bomber2.setY(bomber2.getY()+100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(container.getInput().isKeyDown(Input.KEY_A)&& leftBlocked2==false && bomber2.getX()>0)
		{
			bomber2.setX(bomber2.getX()-100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(container.getInput().isKeyDown(Input.KEY_SPACE))
		{
			if(placeBomb2==false)
			{
			placeBomb2=true;
			bomb2.setX(bomber2.getX()+5);
			bomb2.setY(bomber2.getY()+5);
			buffer2=java.time.Instant.now().getEpochSecond();
			}
		}
		//********************************
		//Collisions between objects
		//********************************
    }
	/*This inherited abstract function needs to be overriden.*/
	 public void render(GameContainer container, StateBasedGame game, Graphics g)
	            throws SlickException {
		 /*Use the passed parameter g containing a Graphics
		  * object we can use to draw to our screen.
		  */
		float tempY1=bomb1.getY();
		float tempX1=bomb1.getX();
		float tempY2=bomb2.getY();
		float tempX2=bomb2.getX();
		background.draw();
		bomber2.draw();
		bomber1.draw();
		
		if(placeBomb1==true)
		{
			if(placeBomb1==true && buffer1+2>java.time.Instant.now().getEpochSecond())
			{	
				bomb1.draw();
				for(int x=0;x<25;x++)
				{
					if(bomb1.up().intersects(blocks[x].getRectangle()))
					{
						bombUpBlock1=true;
					}
					if(bomb1.right().intersects(blocks[x].getRectangle()))
					{
						bombRightBlock1=true;
					}
				}
			}
			else
			{
				placeBomb1=false;
				bombCounter1=true;
				buffer1=java.time.Instant.now().getEpochSecond();
				
			}
		}
		
		if(bombCounter1==true)
		{
			if(bombCounter1==true&&buffer1+2>java.time.Instant.now().getEpochSecond()&&placeBomb1==false)
			 {
				boom1.setX(bomb1.getX());
				boom1.setY(bomb1.getY());
				bomb1.setX(-100);
				bomb1.setY(-100);
				if(bombUpBlock1==true)
				{
					for(int x=0;x<bomber1.showPower();x++)
					{
						boom1.draw();
						collisions(game);
						boom1.setX(tempX1+(100*x));
						boom1.setY(tempY1);
						boom1.draw();
						collisions(game);
						boom1.setX(tempX1-(100*x));
						boom1.setY(tempY1);
						boom1.draw();
						collisions(game);
					}
				}
				else if(bombRightBlock1==true)
				{
					for(int x=0;x<bomber1.showPower();x++)
					{
						boom1.draw();
						collisions(game);
						boom1.setX(tempX1);
						boom1.setY(tempY1+(100*x));
						boom1.draw();
						collisions(game);
						boom1.setX(tempX1);
						boom1.setY(tempY1-(100*x));
						boom1.draw();
						collisions(game);
					}
				}
				else{
						for(int x=0;x<bomber1.showPower();x++)
						{
							boom1.draw();
							collisions(game);
							boom1.setX(tempX1+(100*x));
							boom1.setY(tempY1);
							boom1.draw();
							collisions(game);
							boom1.setX(tempX1-(100*x));
							boom1.setY(tempY1);
							boom1.draw();
							collisions(game);
							boom1.setX(tempX1);
							boom1.setY(tempY1+(100*x));
							boom1.draw();
							collisions(game);
							boom1.setX(tempX1);
							boom1.setY(tempY1-(100*x));
							boom1.draw();
							collisions(game);
						}
					}
			}
			else
			{
				bombRightBlock1=false;
				bombUpBlock1=false;
				bombCounter1=false;
			}
		}
		//************************Player 2's Bomb***********************************
		if(placeBomb2==true)
		{
			if(placeBomb2==true && buffer2+2>java.time.Instant.now().getEpochSecond())
			{	
				bomb2.draw();
				for(int x=0;x<25;x++)
				{
					if(bomb2.up().intersects(blocks[x].getRectangle()))
					{
						bombUpBlock2=true;
					}
					if(bomb2.right().intersects(blocks[x].getRectangle()))
					{
						bombRightBlock2=true;
					}
				}
			}
			else
			{
				placeBomb2=false;
				bombCounter2=true;
				buffer2=java.time.Instant.now().getEpochSecond();
				
			}
		}
		
		if(bombCounter2==true)
		{
			if(bombCounter2==true&&buffer2+2>java.time.Instant.now().getEpochSecond()&&placeBomb2==false)
			 {
				boom2.setX(bomb2.getX());
				boom2.setY(bomb2.getY());
				bomb2.setX(-100);
				bomb2.setY(-100);
				if(bombUpBlock2==true)
				{
					for(int x=0;x<bomber2.showPower();x++)
					{
						boom2.draw();
						collisions(game);
						boom2.setX(tempX2+(100*x));
						boom2.setY(tempY2);
						boom2.draw();
						collisions(game);
						boom2.setX(tempX2-(100*x));
						boom2.setY(tempY2);
						boom2.draw();
						collisions(game);
					}
				}
				else if(bombRightBlock2==true)
				{
					for(int x=0;x<bomber2.showPower();x++)
					{
						boom2.draw();
						collisions(game);
						boom2.setX(tempX2);
						boom2.setY(tempY2+(100*x));
						boom2.draw();
						collisions(game);
						boom2.setX(tempX2);
						boom2.setY(tempY2-(100*x));
						boom2.draw();
						collisions(game);
					}
				}
				else{
						for(int x=0;x<bomber2.showPower();x++)
						{
							boom2.draw();
							collisions(game);
							boom2.setX(tempX2+(100*x));
							boom2.setY(tempY2);
							boom2.draw();
							collisions(game);
							boom2.setX(tempX2-(100*x));
							boom2.setY(tempY2);
							boom2.draw();
							collisions(game);
							boom2.setX(tempX2);
							boom2.setY(tempY2+(100*x));
							boom2.draw();
							collisions(game);
							boom2.setX(tempX2);
							boom2.setY(tempY2-(100*x));
							boom2.draw();
							collisions(game);
						}
					}
			}
			else
			{
				bombRightBlock2=false;
				bombUpBlock2=false;
				bombCounter2=false;
			}
		}
		
		
		for(int x=0; x<25;x++)
		{
		
			blocks[x].draw();
				
		}
		
		for(int y=0;y<75;y++)
		{
			fakeBlocks[y].draw();
		}
		power.draw();
		
	 }
	 
	 public void init(GameContainer container, StateBasedGame game)
	            throws SlickException {
	        // TODO Auto-generated method stub
		 	
		 	bomber1 =new Player(1000,1000, "assets/Bomberman1.png");
		 	background=new Background(0,0);
		 	placeFakeBlocks();
		 	placeWalls();
		 	boom1 = new Explosion(0,0);
		 	boom2 = new Explosion(0,0);
		 	bomb1=new Bomb(0,0);
		 	bomb2=new Bomb(0,0);
		 	bomber2 = new Player(0,0, "assets/Bomberman2.png");
		 	power=new power(-100,-100);
	 }
	 /*This inherited abstract function needs to be overriden.*/
	 public int getID() {
	        // TODO Auto-generated method stub;
	        return this.ID;
	 }
	
	 
}

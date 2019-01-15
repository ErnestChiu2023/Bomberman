package bomberMan;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public abstract class gameSprites extends Image{
	protected float X;
	protected float Y;
	
	public abstract void setX(float x);
	public void setY(float y){this.Y=y;}
	public float getX(){return this.X;}
	public float getY(){return this.Y;}
	
	public gameSprites(String file)throws SlickException{
		super(file);
	}

	public Rectangle getRectangle(){
		Rectangle temp=new Rectangle(this.X-5, this.Y-5, this.width-10, this.height-10);
		return temp;
	}
	public Rectangle up()
	{
		Rectangle up=new Rectangle(this.X-5, this.Y-100, this.width-10, this.height-10);
		return up;
	}
	public Rectangle down()
	{
		Rectangle down=new Rectangle(this.X-5, this.Y+100, this.width-10, this.height-10);
		return down;
	}
	public Rectangle right()
	{
		Rectangle right=new Rectangle(this.X+100, this.Y-5, this.width-10, this.height-10);
		return right;
	}
	public Rectangle left()
	{
		Rectangle left=new Rectangle(this.X-100, this.Y-5, this.width-10, this.height-10);
		return left;
	}
	public String toString()
	{
		String temp=(this.X-5)+","+ (this.Y-100)+","+(this.width-10)+","+ (this.height-10);
		return temp;
	}
	public void draw()
	{
		super.draw(this.X,this.Y);
	}

}

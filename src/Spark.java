import java.awt.Color;
import java.awt.Graphics;

//Sparks fly in all directions around cursor position
public class Spark {
	private double angle, velocity; 
	private int x, y;
	private int[] rgba = new int[4];
	private float[] hsb = new float[3];
	
	public Spark(int posX, int posY, double a, double v, Color c){
		angle = a;
		velocity = v;
		x = posX;
		y = posY;
		rgba[0] = c.getRed();
		rgba[1] = c.getGreen();
		rgba[2] = c.getBlue();
		rgba[3] = 255;
		hsb = Color.RGBtoHSB(rgba[0], rgba[1], rgba[2], hsb);
	}
	
	//called at each iteration
	public void incrementSpark(){
		x = (int) (Math.cos(angle) * velocity + x);
		y = (int) (Math.sin(angle) * velocity + y);
		velocity = velocity * 0.995;
		changeColor();
	}
	
	//colors decay to black
	public void changeColor(){
		double changeSpeed = 0.0025;
		hsb[0]+= changeSpeed;
		int temp = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
		rgba[0] = (temp >> 16) & 0xFF;
		rgba[1] = (temp >> 8) & 0xFF;
		rgba[2] = temp & 0xFF;
		rgba[3] *= 0.95;
	}
	
	//graphics rendering of spark
	public void drawSpark(Graphics g){
		for(int radius = 5 ; radius > 1; radius--){
			g.setColor(new Color(rgba[0]/(radius/2), rgba[1]/(radius/2), rgba[2]/(radius/2), rgba[3]));
			g.fillOval(x - radius, y - radius, 2*radius, 2*radius);
		}
	}
	
}

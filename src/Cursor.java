import java.awt.Color;
import java.awt.Graphics;


public class Cursor {
	private int radius = 20;
	private int x, y, centerX, centerY;
	private int[] rgb = new int[3];
	private float[] hsb = new float[3];
	private int counter = 0;
	private int pathX;
	private int pathY;
	
	public Cursor(int width, int height, Color initial){
		pathX = (int)(width/9) + (int) (Math.random()*height/9);
		pathY = (int)(height/9) + (int) (Math.random()*width/9);
		System.out.println(pathX + " " + pathY);
		centerX = width/2;
		centerY = height/2;
		x = centerX;
		y = centerY;
		rgb[0] = initial.getRed();
		rgb[1] = initial.getGreen();
		rgb[2] = initial.getBlue();
		hsb = Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], hsb);
	}
	
	public void changeColor(){
		double changeSpeed = 0.007;
		hsb[0]+= changeSpeed;
		int temp = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
		rgb[0] = (temp >> 16) & 0xFF;
		rgb[1] = (temp >> 8) & 0xFF;
		rgb[2] = temp & 0xFF;
	} 
	
	public void incrementCursor(){
		counter++;
		changeColor();
		x = (int) (centerX + (centerX-100)*Math.cos(Math.PI * counter/pathX));
		y = (int) (centerY + (centerY-100)*Math.cos(Math.PI * counter/pathY));
	}
	
	public void drawCursor(Graphics g){
		for(int r = radius; r > 0; r--){
			g.setColor(new Color((int)(rgb[0]*Math.cos(Math.PI*r/(radius*2.2))), (int)(rgb[1]*Math.cos(Math.PI*r/(radius*2.2))), (int)(rgb[2]*Math.cos(Math.PI*r/(radius*2.2)))));
			g.fillOval(x - r, y - r, 2*r, 2*r);
		}
	}
	
	public Spark poopSpark(){
		//poopSpark(posX, posY, angle, velocity, color)
		return new Spark(x, y, 2*Math.PI*Math.random(), 1+5*Math.random(), new Color(rgb[0], rgb[1], rgb[2]));
	}
	
	public Pulse poopPulse(int level){
		//poopPulse(x, y , velocity, color);
		return new Pulse(x, y, level/5, new Color(rgb[0], rgb[1], rgb[2]));	
		}
}

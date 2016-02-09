import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

//pulses are circles that grow out of original position
public class Pulse {
	private double speed;
	private int x, y, radius;
	private int[] rgba = new int[4];
	private float[] hsb = new float[3];
	private int maxRadius = 120;

	public Pulse(int posX, int posY, int v, Color c){
		radius = 0;
		speed = v;
		x = posX;
		y = posY;
		rgba[0] = c.getRed();
		rgba[1] = c.getGreen();
		rgba[2] = c.getBlue();
		rgba[3] = 255;
		hsb = Color.RGBtoHSB(rgba[0], rgba[1], rgba[2], hsb);
	}

	//incremented at each iteration
	public void incrementPulse(){
		if (radius < maxRadius)
			radius += speed;
		rgba[3] = (int) (rgba[3]*0.92);
	}

	//graphics drawing of pulse
	public void drawPulse(Graphics g, Graphics2D g2){
		BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, null, 0);
		g2.setStroke(bs);
		g.setColor(new Color(rgba[0], rgba[1], rgba[2], rgba[3]));
		g.drawOval(x-radius, y-radius, radius*2, radius*2);
	}
}

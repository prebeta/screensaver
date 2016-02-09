import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaverPanel extends JPanel implements Runnable {
	private ArrayList<Spark> myS = new ArrayList<Spark>();
	private ArrayList<Pulse> myP = new ArrayList<Pulse>();
	private Cursor myC;
	private Thread runner;
	private int maxSparks = 50;
	private int sparkIndex = 0;
	private int maxPulses = 30;
	private int pulseIndex = 0;
	private AudioControl myA = new AudioControl();

	public SaverPanel(Cursor curse){
		super();
		this.setBackground(Color.black);
		runner = new Thread(this);
		runner.start();
		myC = curse;
		
	}

	public void paint(Graphics g){

		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRect(0,0,getWidth(),getHeight());
		for(int p = 0; p < myP.size(); p++)
			myP.get(p).drawPulse(g,g2);
		
		for(int q = 0; q < myS.size(); q++)
			myS.get(q).drawSpark(g);
		
		myC.drawCursor(g);
	}

	
	public void increment(){
		myC.incrementCursor();
		int level = myA.getAudio();
		if(myS.size()<maxSparks)
			myS.add(myC.poopSpark());
		else {
			myS.set(sparkIndex, myC.poopSpark());
			sparkIndex++;
			if(sparkIndex == maxSparks-1){
				sparkIndex = 0;
			}
		}
		if(level > 5){
			if(myP.size() < maxPulses)
				myP.add(myC.poopPulse(level));
			else{
				myP.set(pulseIndex, myC.poopPulse(level));
				pulseIndex++;
				if(pulseIndex == maxPulses-1){
					pulseIndex = 0;
				}
			}
		}
		
		for(int q = 0; q < myS.size(); q++){
			myS.get(q).incrementSpark();
		}
		for(int p = 0; p < myP.size(); p++){
			myP.get(p).incrementPulse();
		}

	}

	public void run() {
		while (runner != null){
			repaint();
			increment();
			try {Thread.sleep(22);} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

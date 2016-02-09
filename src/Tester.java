import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Tester extends JFrame{
	public Tester(){
		super("Fullscreen");
		getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		pack();
		this.setTitle("Screensaver");
		this.setBackground(Color.black);
		Cursor myC = new Cursor(this.getWidth(), this.getHeight(), Color.red);
		this.add(new SaverPanel(myC));
		this.setVisible(true);
		this.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						System.exit(0);
					}
				});
	}

	public static void main(String[] args) throws InterruptedException{
		new Tester();
	}
}

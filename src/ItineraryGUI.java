import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ItineraryGUI extends JFrame {
	public ItineraryGUI() {
		
		JTextArea textArea = new JTextArea();
		getContentPane().add(textArea, BorderLayout.NORTH);
	}
}

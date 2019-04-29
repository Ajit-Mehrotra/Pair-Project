import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {

	private JFrame frame;
	private JPanel contentPane;
	private JPanel Menu;
	private JLabel backgroundImg;
	private BoardPanel board;
	private GameState thisState;

	public Window() {
		thisState = new GameState();
		board = new BoardPanel();
		
		frame = new JFrame("Othello");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		frame.setContentPane(contentPane);

		frame.setVisible(true);
		contentPane.setPreferredSize(new Dimension(1600,900));
		
		backgroundImg = new JLabel(new ImageIcon("img/intended GUI.png"));
		contentPane.add(board);
		frame.pack();
		frame.setResizable(true);

		frame.setLocationRelativeTo(null);
		
		board.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				//detect Cell clicked
					//place piece
				/*frame.repaint();
				board.repaint(thisState.getCellArray());*/
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}

			public void mousePressed(MouseEvent e){		
			}
			public void mouseReleased(MouseEvent e) {
				
			}
		});
		
	}

}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {

	private JFrame frame;
	private JPanel contentPane;
	
	private JButton reset;
	private JButton forfeit;
	private JButton versusHumanOrComputer;
	
	private JLabel player1;
	private JLabel player2;
	private JLabel score;
	
	
	private BoardPanel board;
	private GameState state;


	public Window() {
		state = new GameState();
		board = new BoardPanel();
		frame = new JFrame("Othello");
		contentPane = new JPanel();

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setPreferredSize(new Dimension(1600,900));
		
		contentPane.setOpaque(true);
		contentPane.setBackground(Color.lightGray);

	
		reset = new JButton("Reset");
		forfeit= new JButton("forfeit");
		versusHumanOrComputer = new JButton("Versus");	
		
		


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);
		
		score = new JLabel("Score: " ); 
		score.setForeground(Color.RED);
		score.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		score.setBorder(BorderFactory.createEmptyBorder (0, 100, 0, 0));


						
		player1 = new JLabel("White: "); 
		player1.setForeground(Color.BLACK);
		player1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		player1.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		
		player2 = new JLabel("Black: ");
		player2.setForeground(Color.BLACK);
		player2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		player2.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		
		


		contentPane.add(reset);
		contentPane.add(forfeit);
		contentPane.add(versusHumanOrComputer);
		
		contentPane.add(score);
		contentPane.add(player1);
		contentPane.add(player2);
		
		contentPane.add(board);
		frame.setLocationRelativeTo(null);

		state.setTurn(true);
		board.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {	
			}
			
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}

			public void mousePressed(MouseEvent e){		
			}
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				Cell z = board.calcCellClicked(x,y);
				int cellCol = z.getCol();
				int cellRow = z.getRow();
				System.out.println(cellRow + " " + cellCol);
				Cell[][] copyCellArray = GameState.getCellArray();
				if(state.isPlaceable(copyCellArray[cellRow][cellCol])) {
					if(state.getTurn()) {
						GameState.getCellArray()[cellRow][cellCol].setWhite();
					}else {
						GameState.getCellArray()[cellRow][cellCol].setBlack();
					}
					state.setTurn(!state.getTurn());
					board.repaint();
				}
			}
		});
		
	}

}

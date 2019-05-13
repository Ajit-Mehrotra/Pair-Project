import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
	private JPanel scoreContentPane;
	
	private JButton reset;
	private JButton forfeit;
	private JButton versusHumanOrComputer;
	
	private JLabel player1;
	private JLabel player2;
	private JLabel score;
	
	
	private BoardPanel board;
	private GameState state;
	private int playerOnePoints;
	private int playerTwoPoints;

	public Window() {
		state = new GameState();
		board = new BoardPanel();
		frame = new JFrame("Othello");
		contentPane = new JPanel();
		scoreContentPane = new JPanel();

		playerOnePoints = state.PlayerOnePoints();
		playerTwoPoints = state.PlayerTwoPoints();
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.setPreferredSize(new Dimension(1600,900));		
		contentPane.setOpaque(true);
		contentPane.setBackground(Color.lightGray);
		
		scoreContentPane.setLayout(new BoxLayout(scoreContentPane, BoxLayout.Y_AXIS));
		scoreContentPane.setPreferredSize(new Dimension(400,150));
		scoreContentPane.setOpaque(true);
		scoreContentPane.setBackground(Color.lightGray);

		reset = new JButton("Reset");
		forfeit= new JButton("forfeit");
		versusHumanOrComputer = new JButton("Versus");	
		
		score = new JLabel("Score: " ); 
		score.setFont(new Font("Serif", Font.PLAIN, 40));
		score.setForeground(Color.RED);
		score.setAlignmentX(JLabel.TOP_ALIGNMENT);
		score.setBorder(BorderFactory.createEmptyBorder (0, 100, 200, 0));

		player1 = new JLabel("White: ___" ); 
		player1.setFont(new Font("Serif", Font.PLAIN, 30));
		player1.setForeground(Color.BLACK);
		player1.setAlignmentX(JLabel.TOP_ALIGNMENT);
		player1.setBorder(BorderFactory.createEmptyBorder(0, 100, 200, 0));
		
		player2 = new JLabel("Black: __ " );
		player2.setFont(new Font("Serif", Font.PLAIN, 30));
		player2.setForeground(Color.BLACK);
		player2.setAlignmentX(JLabel.TOP_ALIGNMENT);
		player2.setBorder(BorderFactory.createEmptyBorder(0, 100, 200, 0));
		
		contentPane.add(reset);
		contentPane.add(forfeit);
		contentPane.add(versusHumanOrComputer);
		
		scoreContentPane.add(score);
		scoreContentPane.add(player1);
		scoreContentPane.add(player2);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);
		
		contentPane.add(board);
		contentPane.add(scoreContentPane);
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
				
				//System.out.println("playerOnePoints: " + playerOnePoints);
				//System.out.println("playerTwoPoints: " + playerTwoPoints);
				Cell z = board.calcCellClicked(x,y);
				int cellCol = z.getCol();
				int cellRow = z.getRow();
				//System.out.println(cellRow + " " + cellCol);
				Cell[][] copyCellArray = GameState.getCellArray();
				if(state.isPlaceable(copyCellArray[cellRow][cellCol])) {
					if(state.getTurn()) {
						GameState.getCellArray()[cellRow][cellCol].setWhite();
						//System.out.println( "State: " + GameState.getCellArray()[cellRow][cellCol].getState());
						//System.out.println("Sets white" );
					}else {
						GameState.getCellArray()[cellRow][cellCol].setBlack();
						//System.out.println( "State: " + GameState.getCellArray()[cellRow][cellCol].getState());
						state.updateCellArray(cellRow, cellCol, 2);
						//System.out.println("Sets Black" );
					}
				}
				state.updateCellArray(cellRow, cellCol, state.playerTurn(state.getTurn()) );
				state.setTurn(!state.getTurn());
				board.repaint();
				playerOnePoints = state.PlayerOnePoints();
				playerTwoPoints = state.PlayerTwoPoints();
				
				//System.out.println("After playerOnePoints: " + playerOnePoints);
				//System.out.println("After playerTwoPoints: " + playerTwoPoints);
				player1.setText("White: " + playerOnePoints );
				player2.setText("Black: " + playerTwoPoints );
			}
		});
	}
}

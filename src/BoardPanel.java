import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    	int w = this.getWidth();
        int h = this.getHeight();
        Graphics2D G = (Graphics2D) g;
        G.setStroke(new BasicStroke());
        boolean[][] placeable = GameState.getPlaceableArray();
        
        //draw lines
        for (int i = 0; i < 9; i++) {
            // draw horz lines
            if (i < 8 && i > 0) {
                G.drawLine(0, h - ((h / 8) * i), w, h - ((h / 8) * i));
            } else if (i == 8) {
                G.drawLine(0, 0, w, 0);
            } else {
                G.drawLine(0, h - 1, w, h - 1);
            }

        }

        //draw lines
        for (int i = 0; i < 9; i++) {
            // draw vert lines
            if (i < 8 && i > 0) {
                G.drawLine(w - ((w / 8) * i), 0, w - ((w / 8) * i), h);
            } else if (i == 8) {
                G.drawLine(0, 0, 0, h);
            } else {
                G.drawLine(w - 1, 0, w - 1, h);
            }

        }

        //draw board pieces
        for (Cell[] currentCellArray : GameState.getCellArray()) {
            for (Cell currentCell : currentCellArray) {
            	currentCell.setPlaceable(true);
            	if(currentCell.getPlaceable()) {
                 	G.setColor(Color.BLUE);
                 	G.setStroke(new BasicStroke((float) .1));
                 	int[] coordinate = calcPixStart(currentCell);
                 	G.fillRect(coordinate[0], coordinate[1], w/8, h/8);
                 	G.setColor(Color.BLACK);
                 }
            	
            	if (currentCell.getState() == 1) {
                    int[] coordinate = calcPixStart(currentCell);
                    G.drawOval(coordinate[0], coordinate[1], w / 8, h / 8);
                } else if (currentCell.getState() == 2) {
                    int[] coordinate = calcPixStart(currentCell);
                    G.fillOval(coordinate[0], coordinate[1], w / 8, h / 8);
                }
            }
        }
    }

    private int[] calcPixStart(Cell given) {
        int[] coordinate = new int[2];
        int h = this.getHeight();
        int w = this.getWidth();
        int xCell = given.getCol();
        int yCell = given.getRow();
        coordinate[0] = (w/8) * xCell;
        coordinate[1] = (h/8) * yCell;
        return coordinate;
    }

    // Just returns the Cell. You can retrieve the Cell's location using getRow and getCol. Or you can return an int[] with 2 values.
    public Cell calcCellClicked(int xPix, int yPix) {
        int h = this.getHeight();
        int w = this.getWidth();

        final int widthDifference = (w / 8);
        final int heightDifference = (h / 8);

        boolean xFound = false;
        boolean yFound = false;

        Cell cell = new Cell();

        for (int a = 0; a < 8; a++) {


            if (!xFound) {
                xFound = (xPix >= ((widthDifference) * a) && xPix <= ((widthDifference) * a + (widthDifference)));
                cell.setCol(a);
            }

        }

        for (int b = 0; b < 8; b++) {

            if (!yFound) {
                yFound = (yPix >= ((heightDifference) * b) && yPix <= ((heightDifference) * b + (heightDifference)));

                cell.setRow(b);
            }

        }
        return cell;
    }

    public BoardPanel() {

    }

}

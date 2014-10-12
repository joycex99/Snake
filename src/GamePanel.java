import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * GUI
 * @author Joyce
 */
public class GamePanel extends JPanel{
	private Board board;
	private Snake snake;
	
	/**Constructor*/
	public GamePanel(Board board, Snake snake) {
		this.board = board;
		this.snake = snake;
	}
	
	/**Paints the board, calls drawTile for each specific cell found on board array*/
	public void paint(Graphics g) {
		super.paint(g);
		this.board.render();
		
		int rows = this.board.getNumRows();
		int cols = this.board.getNumCols();
		
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
            	Cell cell = this.board.getCell(i, j);
            	if (cell == null) {
            		drawTile(g, null, i, j);
            		continue;
            	}
                drawTile(g, cell.getType(), i, j);
            }
        }
        
        g.setColor(Color.WHITE);
        String score = Integer.toString(this.snake.getScore());
        g.drawString("Score: " + score, rows*15-70, 20);
        
	}	
	
	/**Draws a tile based on given cell's type: 
	 * e.g. black for empty, green rect for snake, red circle for apple
	 */
	boolean green = true;
    private void drawTile(Graphics g, TileType type, int x, int y) {
        int xPos = x * 15;
        int yPos = y * 15;
        if (type == null) {
            return;
        }
        
        switch (type) {
	        case APPLE:
	            g.setColor(Color.RED);
	            g.drawOval(xPos, yPos, 15, 15);
	            break;
            case SNAKE:
            	if (green) {
            		g.setColor(Color.GREEN);
            		g.drawRoundRect(xPos, yPos, 15, 15, 5, 5);
            		green = false;
            	}
            	else {
            		g.setColor(Color.YELLOW);
            		g.drawRoundRect(xPos, yPos, 15, 15, 5, 5);
            		green = true;
            	}
                break;
            default:
        }
    }
}

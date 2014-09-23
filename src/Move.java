import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public class Move extends JPanel {
	public static final int STATIC = 0; 
	public static final int RIGHT = 1;
	public static final int LEFT = -1;
	public static final int UP = 2;
	public static final int DOWN = -2;
	
	public Snake snake;
	public Board board;
	public int direction = UP;
	public static boolean gameOver;
	
	public Move(Snake snake, Board board) {
		this.snake = snake;
		this.board = board;
		
		KeyListener l = new KeyAdapter() {
			@Override 
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_UP:
						setDirection(UP);
						break;
					case KeyEvent.VK_DOWN:
						setDirection(DOWN);
						break;
					case KeyEvent.VK_LEFT:
						setDirection(LEFT);
						break;
					case KeyEvent.VK_RIGHT:
						setDirection(RIGHT);
						break;
				}
			}
		};
		addKeyListener(l);
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void update() {
		if (!gameOver) {
			if (direction != STATIC) {
				Cell next = getNext(snake.head);
				
				if (snake.crashed(next)) {
					setDirection(STATIC);
					gameOver = true;
				}
				else {
					snake.move(next);
					if (next.type == Cell.APPLE) {
						snake.grow();
						board.generateApple();
					}
				}
			}
			
			
		}
	}
	
	public Cell getNext(Cell current) {
		int r = current.row;
		int c = current.col;
		
		switch(direction) {
		case(RIGHT):
			c++;
			break;
		case(LEFT):
			c--;
			break;
		case(UP):
			r--;
			break;
		case(DOWN):
			r++;
			break;
		}	
		
		Cell nextCell = board.cells[r][c];
		return nextCell;
	}
}

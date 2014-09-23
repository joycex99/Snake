import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.Random;

public class Board extends JPanel implements ActionListener {
	final int rows;
	final int cols;
	Cell[][] cells;

	//determines direction
	public static final int STATIC = 0; 
	public static final int RIGHT = 1;
	public static final int LEFT = -1;
	public static final int UP = 2;
	public static final int DOWN = -2;
	
	public Snake snake;
	public int direction = UP;
	public static boolean gameOver;
	
	//constructor: builds array of Cells with the specified rows, columns; adds specified snake
	public Board(int r, int c, Snake snake) {
		rows = r;
		cols = c;
		this.snake = snake;
		
		cells = new Cell[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				cells[i][j] = new Cell(i, j);
				
			}
		}
		for (Cell cell: snake.snake) {
			int i = cell.row;
			int j = cell.col;
			cells[i][j].type = Cell.SNAKE;
		}
		
		//keylistener: should change the snake's direction (NOT WORKING)
		KeyListener l = new KeyAdapter() {
			@Override 
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				
				if (k == KeyEvent.VK_UP && direction != DOWN) {
					setDirection(UP);
				}
				else if (k == KeyEvent.VK_DOWN && direction != UP) {
					setDirection(DOWN);
				}
				else if (k == KeyEvent.VK_RIGHT && direction != LEFT) {
					setDirection(RIGHT);
				}
				else if (k == KeyEvent.VK_LEFT && direction != RIGHT) {
					setDirection(LEFT);
				}
				repaint();
			}
		};
		this.addKeyListener(l);
		setFocusable(true);
		//init();
	}
	
	//initializes game: generates apple, updates/repaints every two seconds
	public void init() {
		this.generateApple();

		while (!gameOver) {
			try {
				Thread.sleep(2000);
			} catch (Exception ex) {}
			
			this.update();
			this.repaint();
		}
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	//moves the snake
	public void update() {
		if (!gameOver) {
			if (direction != STATIC) {
				Cell next = getNext(snake.getHead(), Cell.SNAKE);
				
				if (snake.crashed(next)) {
					setDirection(STATIC);
					gameOver = true;
				}
				else {
					snake.move(next);
					if (next.type == Cell.APPLE) {
						snake.grow();
						this.generateApple();
					}
				}
			}
		}
	}
	
	//used to animate (move/repaint) snake (NOT WORKING)
	@Override
	public void actionPerformed(ActionEvent e) {
		//if (direction == UP) {
			//snake.move(getNext(snake.head));
		//}
		update();
		repaint();
	}
	
	//returns the next cell position of snake given the direction
	public Cell getNext(Cell current, int type) {
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
		
		Cell nextCell = new Cell(r, c, type);
		return nextCell;
	}
	
	//generates the apple
	public void generateApple() {
		Random ran = new Random();
		int r = ran.nextInt(rows-2); 
		int c = ran.nextInt(cols-2);
	
		cells[r+1][c+1].type = Cell.APPLE;
	}	
	
	//GUI
	public void paint(Graphics g) {
		super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {	
                drawTile(g, cells[i][j].type, j , i);
            }
        }
    }
	
	public void drawTile(Graphics g, int type, int x, int y) {
		int xPos = x*10;
		int yPos = y*10;
		
		if (type == Cell.SNAKE) {
			System.out.println("drawing at " + xPos + ", " + yPos);
			g.setColor(Color.GREEN);
			g.fillRect(xPos, yPos, 10, 10);
		}
		else if (type == Cell.APPLE) {
			g.setColor(Color.RED);
			g.fillOval(xPos, yPos, 10, 10);
		}
	}
}

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * Main class:
 * Adds board, adds snake, sets correspondence to one another
 * Adds keylistener to control direction
 * Animates snake with thread
 * 
 * @author Joyce
 */
public class Main extends JPanel {
	public static void main(String[] args) {
		int x = 20;
		int y = 20;
		
		Board board = new Board(x, y);
		final Snake snake = new Snake(15, 15);
		board.setSnake(snake);
		snake.setBoard(board);
		
		GamePanel p = new GamePanel(board, snake);
		p.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				Direction dir = snake.getDirection();
				
				switch(k) {
					case KeyEvent.VK_UP:
						if (dir == Direction.DOWN) 
							break;
						snake.setDirection(Direction.UP);
						break;
					case KeyEvent.VK_DOWN:
						if (dir == Direction.UP) 
							break;
						snake.setDirection(Direction.DOWN);
						break;
					case KeyEvent.VK_LEFT:
						if (dir == Direction.RIGHT) 
							break;
						snake.setDirection(Direction.LEFT);
						break;
					case KeyEvent.VK_RIGHT:
						if (dir == Direction.LEFT) 
							break;
						snake.setDirection(Direction.RIGHT);
						break;
				}
			}
		});
		
		p.setFocusable(true);
		p.setSize(x*15, y*16);
		
		JFrame f = new JFrame();
		f.setTitle("Snake");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setSize(p.getSize().width, p.getSize().height);
        f.add(p);
        f.setVisible(true);
        
        board.generateApple();
        
        while (!snake.hasCrashed()) {
	        try {
                Thread.sleep(120);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        
	        snake.move();
	        p.repaint();
        }
	}	
}

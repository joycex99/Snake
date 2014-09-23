import javax.swing.*;

public class Main extends JPanel {
	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.setTitle("Snake");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300, 300);
		
		Snake snake = new Snake(new Cell(15, 15, Cell.SNAKE));
		Board board = new Board(30, 30, snake);
		f.add(board);
		f.setVisible(true);
		board.init();
	}
}

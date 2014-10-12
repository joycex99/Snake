import java.util.Random;

/**
 * Board class:
 * Constructs the board as an array of object Cell
 * Can check if a Cell exists, set it to the board, etc.
 * Generates the apple
 * Renders the board for GUI configuration
 * 
 * @author Joyce
 */
public class Board {
	private final int rows;
	private final int cols;
	private Cell[][] cells;
	private Snake snake;
	//private int score;
	
	/** constructor: builds array of Cells 
	 * @param r is the number of rows
	 * @param c is the number of columns
	 */
	public Board(int r, int c) {
		this.rows = r;
		this.cols = c;
		this.cells = new Cell[rows][cols];
	}
	
	public int getNumRows() {
		return this.rows;
	}
	
	public int getNumCols() {
		return this.cols;
	}
	
	public void setSnake(Snake snake) {
		this.snake = snake;
	}
	
	/*public int getScore() {
		return this.score;
	}*/
	
	/**checks to see if the specified cell is on the board*/
	public boolean hasCell(int r, int c) {
		if (r >= this.rows || c >= this.cols)
			return false;
		return true;
	}
	
	/**returns the cell if it exists, null if it's off the board*/
	public Cell getCell(int r, int c) {
		if (!this.hasCell(r, c)) {
			return null;
		}
		return this.cells[r][c];
	}
	
	/**adds a cell to the board based on the cell's x and y*/
	public boolean setCell(Cell cell) {
		int col = cell.getX();
		int row = cell.getY();
		
		if (!this.hasCell(row, col)) {
			return false;
		}
		this.cells[col][row] = cell;
		return true;
	}
	
	/**adds a cell with a specified row and column to the board*/
	public boolean setCell(int r, int c, Cell cell) {
		if (r >= this.rows || c > this.cols) {
			return false;
		}
		this.cells[r][c] = cell;
		return true;
	}
	
	/**generates the apple, sets the apple to the board*/
	public void generateApple() {
		boolean available = false;
		while (!available) {
			Random ran = new Random();
			int r = ran.nextInt(this.rows-2)+1; 
			int c = ran.nextInt(this.cols-2)+1;
			
			try {
				if (cells[r][c].getType() == TileType.SNAKE) {
					System.out.println("SNAKE!");;
				}
			} catch (NullPointerException ex) {
				available = true;
				this.setCell(new Cell(r, c, TileType.APPLE));
			}
		}
	}	
	
	
	/** Renders the board (for GUI):
	 *  sets cells to null
	 *  draws the snake onto the board
	 */
	public void render() {
		for (int i = 0; i < this.rows; i++) { 
			for (int j = 0; j < this.cols; j++) {
				Cell cell = this.cells[i][j];
				if (cell == null || cell.getType() != TileType.SNAKE) continue;
				this.cells[i][j] = null;
			}
		}
		
		if (this.snake != null) {
			this.snake.draw(this);
		}
	}
}


public class Cell {
	final static int EMPTY = 0;
	final static int SNAKE = 1;
	final static int APPLE = 2;
	
	final int row, col;
	int type;
	
	//constructor: defaults to empty square
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		this.type = Cell.EMPTY;
	}
	
	//constructor: type specified
	public Cell(int row, int col, int type) {
		this.row = row;
		this.col = col;
		this.type = type;
	}
}

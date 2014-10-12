import java.util.*;

/**
 * Snake class:
 * Snake is constructed out of LinkedList
 * Includes methods of moving the snake, checking for collision, and adding the 
 * snake to the board
 * 
 * @author Joyce
 */

public class Snake {
	LinkedList<Cell> cells = new LinkedList<>();
	private Direction direction;
	private boolean grow = false;
	private Board board;
	private int score;
	
	/**constructor: creates snake with one link*/
	public Snake(int x, int y) { 
		Cell head = new Cell(x, y, TileType.SNAKE);
		this.cells.addFirst(head);
		this.direction = Direction.UP;
		this.score = 0;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Cell getHead() {
		return this.cells.getFirst();
	}
	
	public Cell getTail() {
		return this.cells.getLast();
	}
	
	public void grow() {
		this.grow = true;
	}
	
	public int getScore() {
		return this.score;
	}
	
	/** obtains the x, y position of the head and shifts based on direction
	 *  if the current board piece (x, y) is the apple, grow snake & generate new apple
	 *  adds the cell (x, y) to the snake, removes last piece (moves!)
	 */
	public void move() {
	    Cell head = this.getHead();
	    int x = head.getX();
	    int y = head.getY();
	    
	    switch (this.direction) {
    	    case UP:
    	        y--;
    	        break;
    	    case DOWN:
    	        y++;
    	        break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
	        default:
	    }
	    
        Cell c = this.board.getCell(x, y);
        if (c != null && c.getType() == TileType.APPLE) {
        	this.score += 1;
            this.grow();
            this.board.generateApple();
        }
	    
	    Cell prepend = new Cell(x, y, TileType.SNAKE);
	    cells.addFirst(prepend);
	    
	    if (this.grow) {
	        this.grow = false;
	        return;
	    }
	    
	    cells.removeLast();
	}
 	
	
	/** checks for collisions
	 *  if target destination is off board or the snake itself, crashes
	 */ 
	public boolean hasCrashed() {
        Cell head = this.getHead();
        int x = head.getX();
        int y = head.getY();
        
        switch (this.direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
            default:
        }
        
        if (board.getCell(x,y) != null) {
        	 if (board.getCell(x,y).getType() == TileType.SNAKE) {
        		 return true;
        	 }
        }
        if (!board.hasCell(x, y)) {
        	return true;
        }
        return false;
	}
	
	public Direction getDirection() {
	    return this.direction;
	}
	
	public void setDirection(Direction direction) {
	    this.direction = direction;
	}
	
	/** sets each cell of the snake to specified board */
	public void draw(Board board) {
	    for (Cell cell : this.cells) {
	        board.setCell(cell);
	    }
	}
}
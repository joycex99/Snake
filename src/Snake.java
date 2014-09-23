import java.util.*;

public class Snake {
	LinkedList<Cell> snake = new LinkedList<>();
	Cell head;
	
	//constructor: creates snake with one link
	public Snake(Cell first) { 
		head = first;
		snake.add(head);
	}
	
	//returns first cell (head) of snake
	public Cell getHead() {
		return this.snake.getFirst();
	}
	
	//adds a link
	public void grow() {
		snake.add(head);
	}
	
	//moves snake to specified next cell
	public void move(Cell next) {
		System.out.println("next called");
		snake.removeLast();
		snake.addFirst(next);
		
		System.out.println("snake: " + snake.get(0).row + " " + snake.get(0).col);
	}
	
	//checks collision with self
	public boolean crashed(Cell next) {
		for (Cell cell: snake) {
			if (cell == next) {
				return true;
			}
		}
		return false;
	}
}
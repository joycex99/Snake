/**
 * Object Cell: consists of row, column, and type
 * 
 * @author Joyce
 */

public class Cell {
	private TileType type;
	private int row;
	private int col;
	
	/**constructor: defaults to empty square*/
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		this.type = TileType.EMPTY;
	}
	
	/**constructor: type specified*/
	public Cell(int row, int col, TileType type) {
		this.row = row;
		this.col = col;
		this.type = type;
	}
	
	public TileType getType() {
		return this.type;
	}
	
	public void setType(TileType type) {
		this.type = type;
	}
	
	public int getX() {
		return this.row;
	}
	
	public int getY() {
		return this.col;
	}
}

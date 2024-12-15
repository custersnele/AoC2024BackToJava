package ccfun.day15part2;


public enum Move {
	UP(-1, 0),
	RIGHT(0, 1),
	DOWN(1, 0),
	LEFT(0, -1);

	private int deltaRow;
	private int deltaCol;

	Move(int deltaRow, int deltaCol) {
		this.deltaRow = deltaRow;
		this.deltaCol = deltaCol;
	}

	public static Location step(Location original, Move direction) {
		return new Location(original.row() + direction.deltaRow, original.col() + direction.deltaCol);
	}
}

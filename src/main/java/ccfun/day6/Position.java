package ccfun.day6;

public record Position(int row, int col) {

	public boolean inGrid(int rows, int cols) {
		return row >= 0 && row < rows && col >= 0 && col < cols;
	}
}

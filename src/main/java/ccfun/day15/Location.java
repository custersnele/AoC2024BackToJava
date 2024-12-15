package ccfun.day15;

public record Location(int row, int col) {

	public int getGPSCoordinates() {
		return row * 100 + col;
	}
}

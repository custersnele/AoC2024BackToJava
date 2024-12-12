package ccfun.day6;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	private int rows;
	private int cols;
	private List<Position> obstructions = new ArrayList<>();

	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}

	public void addObstruction(Position position) {
		obstructions.add(position);
	}

	public boolean isObstacle(Position position) {
		return obstructions.contains(position);
	}

	public boolean isIn(Position position) {
		return position.row() >= 0 && position.row() < rows &&
				position.col() >= 0 && position.col() < cols;
	}

	public void removeObstruction(Position position) {
		obstructions.remove(position);
	}
}

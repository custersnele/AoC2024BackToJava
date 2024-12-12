package ccfun.day12;

public class Plot {
	private int row;
	private int col;
	private String vegetable;

	public Plot(int row, int col, String vegetable) {
		this.row = row;
		this.col = col;
		this.vegetable = vegetable;
	}

	public int getNonTouchingSides(Region region) {
		int sides = 0;
		if (region.findPlot(row, col - 1).isEmpty()) { // left
			sides++;
		}
		if (region.findPlot(row, col + 1).isEmpty()) { // right
			sides++;
		}
		if (region.findPlot(row - 1, col).isEmpty()) { // up
			sides++;
		}
		if (region.findPlot(row + 1, col).isEmpty()) { // down
			sides++;
		}
		return sides;
	}



	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public String getVegetable() {
		return vegetable;
	}
}

package ccfun.day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Region {
	private List<Plot> plots = new ArrayList<>();

	public Optional<Plot> findPlot(int row, int col) {
		return plots.stream().filter(p -> p.getRow() == row && p.getCol() == col).findAny();
	}

	public String getVegetable() {
		return plots.getFirst().getVegetable();
	}

	public int getArea() {
		return plots.size();
	}

	public int getPerimeter() {
		return plots.stream().mapToInt(p -> p.getNonTouchingSides(this)).sum();
	}

	public int getPrice() {
		return getArea() * getPerimeter();
	}


	public int getPrice2() {
		return getArea() * countSides();
	}


	public void add(Plot plot) {
		this.plots.add(plot);
	}

	public int countSides() {
		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
		Set<Side> uniqueSides = new HashSet<>();

		int minRow = plots.stream().mapToInt(p -> p.getRow()).min().getAsInt();
		int maxRow = plots.stream().mapToInt(p -> p.getRow()).max().getAsInt();
		int minCol = plots.stream().mapToInt(p -> p.getCol()).min().getAsInt();
		int maxCol = plots.stream().mapToInt(p -> p.getCol()).max().getAsInt();

		// Iterate through each cell
		for (int row = minRow; row <= maxRow; row++) {
			for (int col = minCol; col <= maxCol; col++) {
				if (findPlot(row, col).isPresent()) { // If the cell is filled
					for (int[] direction : directions) {
						int nRow = row + direction[0];
						int nCol = col + direction[1];
						if (findPlot(nRow, nCol).isEmpty()) { // If neighbor is outside the region, it's a potential boundary
							int startRow = row;
							int startCol = col;
							while (findPlot(startRow + direction[1], startCol + direction[0]).isPresent() &&
									findPlot(startRow + direction[0], startCol + direction[1]).isEmpty()) {
								startRow += direction[1];
								startCol += direction[0];
							}
							uniqueSides.add(new Side(startRow, startCol, direction[0], direction[1]));
						}

					}
				}
			}
		}
		return uniqueSides.size();
	}

	public boolean isEmpty() {
		return plots.isEmpty();
	}
}

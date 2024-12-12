package ccfun.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day12 {

	public static void main(String[] args) throws IOException {
		List<String> map = Files.readAllLines(Path.of("src/main/resources/day12test.txt"));
		List<Plot> plots = new ArrayList<>();
		for (int row = 0; row < map.size(); row++) {
			for (int col = 0; col < map.getFirst().length(); col++) {
				plots.add(new Plot(row, col, map.get(row).substring(col, col+1)));
			}
		}
		List<Region> regions = createRegions(plots);
		long totalPrice = 0;
		for (Region region : regions) {
			System.out.println(region.getVegetable() + " " + region.getArea() + " " + region.countSides());
			totalPrice += region.getPrice2();
		}
		System.out.println(totalPrice);
	}

	private static List<Region> createRegions(List<Plot> plots) {
		List<Region> regions = new ArrayList<>();
		while (!plots.isEmpty()) {
			Region region = new Region();
			addPlots(region, plots, plots.getFirst().getRow(), plots.getFirst().getCol());
			regions.add(region);
		}
		return regions;
	}

	public static void addPlots(Region region, List<Plot> plots, int currentRow, int currentCol) {
		Optional<Plot> nextPlot = plots.stream().filter(p -> p.getCol() == currentCol && p.getRow() == currentRow).findAny();
		if (nextPlot.isEmpty()) {
			return;
		}
		if (!region.isEmpty() && !region.getVegetable().equals(nextPlot.get().getVegetable())) {
			return;
		}
		plots.remove(nextPlot.get());
		region.add(nextPlot.get());

		// Moving up, right, down, and left one by one
		addPlots(region, plots, currentRow, currentCol - 1);
		addPlots(region, plots, currentRow, currentCol + 1);
		addPlots(region, plots, currentRow - 1, currentCol);
		addPlots(region, plots, currentRow + 1, currentCol);
	}

}

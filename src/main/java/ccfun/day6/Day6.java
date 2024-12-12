package ccfun.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class Day6 {

		public static void main(String[] args) throws IOException {
			List<String> lines = Files.readAllLines(Path.of("src/main/resources/day6.txt"));
			Grid grid = new Grid(lines.size(), lines.getFirst().length());
			Guard guard = new Guard();
			int row = 0;
			RunningPosition start = null;
			for (String line : lines) {
				for (int col = 0; col < line.length(); col++) {
					if (line.charAt(col) == '#') {
						grid.addObstruction(new Position(row, col));
					}
					if (line.charAt(col) == '^') {
						start = new RunningPosition(new Position(row, col), Direction.N);
						guard.setPostion(start);
					}
				}
				row++;
			}
			guard.setGrid(grid);
			guard.runRound(start);
			System.out.println(guard.getPlaces());
			Set<RunningPosition> visited = guard.getVisited();
			visited.remove(start);
			int loops = 0;
			for (Position plp : visited.stream().map(RunningPosition::position).distinct().toList()) {
				grid.addObstruction(plp);
				guard.setGrid(grid);
				guard.runRound(start);
				if (guard.isLoop()) {
					loops++;
				}
				grid.removeObstruction(plp);
			}
			System.out.println(loops);
		}
}

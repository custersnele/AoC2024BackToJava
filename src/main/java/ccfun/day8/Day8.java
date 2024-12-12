package ccfun.day8;

import ccfun.day6.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day8 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day8.txt"));
		Map<String, List<Position>> antennas = new HashMap<>();
		Set<Position> allAntennas = new HashSet<>();
		int row = 0;
		int gridRows = lines.size();
		int gridCols = lines.getFirst().length();
		for (String line : lines) {
			for (int col = 0; col < line.length(); col++) {
				String letter = line.substring(col, col + 1);
				if (!letter.equals(".")) {
					System.out.println(letter);
					if (!antennas.containsKey(letter)) {
						antennas.put(letter, new ArrayList<>());
					}
					Position e = new Position(row, col);
					antennas.get(letter).add(e);
					allAntennas.add(e);
				}
			}
			row++;
		}
		Set<Position> locations = new HashSet<>();
		Set<Position> locations2 = new HashSet<>();
		for (String key : antennas.keySet()) {
			System.out.println("freq " + key);
			for (int p1 = 0; p1 < antennas.get(key).size() - 1; p1++) {
				for (int p2 = p1 + 1; p2 < antennas.get(key).size(); p2++) {
					Position pos1 = antennas.get(key).get(p1);
					Position pos2 = antennas.get(key).get(p2);
					Equation equation = new Equation(pos1, pos2);
					Set<Position> antinodes = equation.findAntinodes(gridRows, gridCols);
					locations.addAll(antinodes);
					Set<Position> allAntinodes = equation.findAllAntinodes(gridRows, gridCols);
					locations2.addAll(allAntinodes);
				}
			}
		}
		locations2.addAll(allAntennas);
		System.out.println(locations.size());
		System.out.println(locations2.size());
	}
}

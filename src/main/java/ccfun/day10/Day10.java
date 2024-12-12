package ccfun.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day10 {

	public static void main(String[] args) throws IOException {
		List<String> map = Files.readAllLines(Path.of("src/main/resources/day10test1.txt"));
		List<Node> nodes = new ArrayList<>();
		for (int row = 0; row < map.size(); row++) {
			for (int col = 0; col < map.getFirst().length(); col++) {
				String substring = map.get(row).substring(col, col + 1);
				if (!substring.equals(".")) {
					nodes.add(new Node(row, col, Integer.parseInt(substring)));
				}
			}
		}
		int count = 0;
		List<Node> trailHeads = nodes.stream().filter(Node::isTrailhead).toList();
		int countTrails = 0;
		for (Node trailHead : trailHeads) {
			int destinations = trailHead.findDestinations(nodes);
			System.out.println(trailHead.getRow() + " " + trailHead.getCol() + " " + destinations);
			count += destinations;
			int trails = trailHead.findPaths(nodes);
			countTrails += trails;
		}
		System.out.println(count);
		System.out.println(countTrails);
	}

}

package ccfun.day15;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Day15 {
	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day15.xmas"));
		Warehouse warehouse = new Warehouse();
		boolean readingWarehouse = true;
		StringBuilder instructions = new StringBuilder();
		for (int row = 0; row < lines.size(); row++) {
			if (readingWarehouse) {
				if (lines.get(row).length() == 0) {
					readingWarehouse = false;
				} else {
					for (int col = 0; col < lines.get(row).length(); col++) {
						if (lines.get(row).charAt(col) == '#') {
							warehouse.add(new Wall(row, col, warehouse));
						} else if (lines.get(row).charAt(col) == 'O') {
							warehouse.add(new Box(row, col, warehouse));
						} else if (lines.get(row).charAt(col) == '@') {
							warehouse.setRobot(new Robot(row, col, warehouse));
						}
					}
				}
			} else {
				instructions.append(lines.get(row));
			}
		}
		warehouse.execute(instructions.toString());
	}

}

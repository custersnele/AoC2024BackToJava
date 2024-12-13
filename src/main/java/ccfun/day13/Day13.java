package ccfun.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day13 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day13.txt"));
		ClawMachine clawMachine = null;
		long tokens = 0;
		for (String line : lines) {
			if (line.startsWith("Button A")) {
				clawMachine = new ClawMachine();
				Position position = getXAndY(line, "A");
				clawMachine.setButtonA(position);
			} else if (line.startsWith("Button B")) {
				Position position = getXAndY(line, "B");
				clawMachine.setButtonB(position);
			 }else if (line.startsWith("Prize")) {
				Position position = getXAndY(line);
				clawMachine.setPrize(position);
				try {
					long[] solve = clawMachine.solve();
					System.out.println(solve[0] + " " + solve[1]);
					//if (solve[0] <= 10 && solve[1] <= 10) {
						tokens += (solve[0] * 3 + solve[1]);
					//}
				} catch (RuntimeException e) {
					System.out.println("Unsolvable");
				}
			}
		}
		System.out.println(tokens);
	}

	public static Position getXAndY(String line, String button) {
		line = line.replace("Button " + button + ": ", "");
		line = line.replace("X+", "");
		line = line.replace("Y+", "");
		String[] split = line.split(", ");
		int x = Integer.parseInt(split[0]);
		int y = Integer.parseInt(split[1]);
		return new Position(x, y);
	}

	public static Position getXAndY(String line) {
		line = line.replace("Prize: ", "");
		line = line.replace("X=", "");
		line = line.replace("Y=", "");
		String[] split = line.split(", ");
		int x = Integer.parseInt(split[0]);
		int y = Integer.parseInt(split[1]);
		return new Position(x, y);
	}

}

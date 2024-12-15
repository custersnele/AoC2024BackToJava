package ccfun.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day14 {

	private static final int MAX_X = 101;
	private static final int MAX_Y = 103;
	private static long prevCount = 0;

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day14.xmas"));
		List<Robot> robots = new ArrayList<>();

		for (String line: lines) {
			Robot robot = new Robot(line);
			//robot.setTimes(100);
			robot.setMaxX(MAX_X);
			robot.setMaxY(MAX_Y);
			robots.add(robot);
		}
		for (int i = 0; i < 100000; i++) {
			robots.forEach(Robot::step);

			if (isChristmasTree(robots)) {
				printRobots(robots);
				System.out.println("SECONDS:" + i);
				scanner.nextLine();
			}

		}
		printRobots(robots);
	}


	private static void printRobots(List<Robot> robots) {
		int[] count = new int[4];
		for (int y = 0; y < MAX_Y; y++) {
			for (int x = 0; x < MAX_X; x++) {
				int finalX = x;
				int finalY = y;
				long numberOfRobots = robots.stream().filter(r -> r.isAt(finalX, finalY)).count();
				System.out.print(numberOfRobots == 0? "." : "*");
				int quadrant = getQuadrant(x, y);
				if (quadrant >= 0) {
					count[quadrant] += numberOfRobots;
				}
			}
			System.out.println();
		}
		int result = 1;
		for (int number : count) {
			System.out.println(number);
			result *= number;
		}
		System.out.println("RESULT:" + result);
	}

	private static int getQuadrant(int x, int y) {
		if (x < Day14.MAX_X / 2 && y < Day14.MAX_Y / 2) {
			return 0;
		} else if (x < Day14.MAX_X / 2 && y > Day14.MAX_Y / 2) {
			return 2;
		} else if (x > Day14.MAX_X / 2 && y < Day14.MAX_Y / 2) {
			return 1;
		} else if (x > Day14.MAX_X / 2 && y > Day14.MAX_Y / 2){
			return 3;
		}
		return -1;
	}

	public static boolean isChristmasTree(List<Robot> robots) {
		long count = robots.stream().mapToInt(r -> r.countNeighbours(robots)).filter(x -> x > 6).count();
		boolean value = count > prevCount;
		if (value) {
			prevCount = count;
		}
		return count >= 100 && value;
	}

}

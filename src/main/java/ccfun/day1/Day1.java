package ccfun.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

	public static void main(String[] args) throws IOException {
		List<String> list = Files.readAllLines(Path.of("src/main/resources/day1.txt"));
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		list.forEach(s -> {
			String[] split = s.split("\\s+");
			left.add(Integer.parseInt(split[0]));
			right.add(Integer.parseInt(split[1]));
		});
		List<Integer> leftSorted = left.stream().sorted().toList();
		List<Integer> rightSorted = right.stream().sorted().toList();
		long totalDistance = 0;
		for (int i = 0; i < leftSorted.size(); i++) {
			int distance = Math.abs(leftSorted.get(i) - rightSorted.get(i));
			totalDistance += distance;
		}
		System.out.println(totalDistance);
		long scoreLeft = calculateScore(leftSorted, rightSorted);
		System.out.println(scoreLeft);
		long scoreRight = calculateScore(rightSorted, leftSorted);
		System.out.println(scoreRight);
		long sim = Math.abs(scoreLeft - scoreRight);
		System.out.println(sim);

	}

	public static long calculateScore(List<Integer> unique, List<Integer> all) {
		long score = 0;
		for (int value : unique) {
			score += value * all.stream().filter(i -> i == value).count();
		}
		return score;
	}
}

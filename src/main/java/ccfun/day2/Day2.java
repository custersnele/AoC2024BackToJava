package ccfun.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 {

	public static void main(String[] args) throws IOException {
		List<String> list = Files.readAllLines(Path.of("src/main/resources/day2.txt"));
		List<List<Integer>> reports = list.stream().map(s -> Arrays.stream(s.split("\\s+")).map(Integer::parseInt).toList()).toList();
		int safeCount = (int) reports.stream().filter(Day2::isSafe).count();
		System.out.println(safeCount);
		int safeWithRemoveCount = (int) reports.stream().filter(Day2::isSafeWithRemove).count();
		System.out.println(safeWithRemoveCount);
	}

	public static boolean isSafeWithRemove(List<Integer> report) {
		if (isSafe(report)) {
			return true;
		}
		for (int i = 0; i < report.size(); i++) {
			List<Integer> copy = new ArrayList<>(report);
			copy.remove(i);
			if (isSafe(copy)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isSafe(List<Integer> report) {
		List<Integer> difference = new ArrayList<>();
		for (int i = 0; i < report.size() - 1; i++) {
			difference.add(report.get(i + 1) - report.get(i));
		}
		long countNegative = difference.stream().filter(i -> i > 0).count();
		long countPositive = difference.stream().filter(i -> i < 0).count();
		if (countNegative > 0 && countPositive > 0) {
			return false;
		}
		long countBig = difference.stream().map(Math::abs).filter(i -> i == 0 || i > 3).count();
		return countBig == 0;
	}
}


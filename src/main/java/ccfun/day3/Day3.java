package ccfun.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

	public static void main(String[] args) throws IOException {
		List<String> list = Files.readAllLines(Path.of("src/main/resources/day3.txt"));
		Pattern pattern = Pattern.compile("do\\(\\)|mul\\((\\d+),(\\d+)\\)|don't\\(\\)");
		long sum = 0;
		boolean active = true;
		StringBuffer buffer = new StringBuffer();
		for (String line : list) {
			buffer.append(line);
		}
		int countMul = 0;
		int countDo = 0;
		int countDont = 0;
		Matcher matcher = pattern.matcher(buffer.toString());
		while (matcher.find()) {
			String group = matcher.group();
			System.out.println(group);
			if (group.startsWith("mul(")) {
				countMul++;
			}
			if (group.startsWith("mul") && active) {
				int x = Integer.parseInt(matcher.group(1));
				int y = Integer.parseInt(matcher.group(2));
				System.out.println(x + " x " + y);
				sum += ((long) x * y);
			} else if (group.startsWith("do()")) {
				active = true;
				countDo++;
			} else if (group.startsWith("don't()")) {
				countDont++;
				active = false;
			}
		}
		System.out.println(sum);
		System.out.println("do:" + countDo);
		System.out.println("don't:" + countDont);
		System.out.println("mul:" + countMul);
	}
}

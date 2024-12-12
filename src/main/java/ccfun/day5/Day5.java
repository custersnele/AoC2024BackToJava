package ccfun.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {

		public static void main(String[] args) throws IOException {
			List<String> lines = Files.readAllLines(Path.of("src/main/resources/day5.txt"));
			boolean readingRules = true;
			List<PrintRule> rules = new ArrayList<>();
			int count = 0;
			long middlesum = 0;
			long middlesum2 = 0;
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				if (line.isBlank()) {
					readingRules = false;
					continue;
				}
				if (readingRules) {
					rules.add(new PrintRule(line));
				} else {
					System.out.println(line);
					List<Integer> script = new ArrayList<>(Arrays.stream(line.split(",")).map(x -> Integer.parseInt(x)).toList());
					if (isValid(script, rules)) {
						count++;
						middlesum += getMiddle(script);
					} else {
						System.out.println(script);
						script.sort(new PrintRuleComparator(rules));
						System.out.println(script);
						middlesum2 += getMiddle(script);
					}
				}
			}
			System.out.println(count);
			System.out.println(middlesum);
			System.out.println(middlesum2);
		}

		public static boolean isValid(List<Integer> script, List<PrintRule> rules) {
			for (PrintRule rule : rules) {
				if (!rule.isOk(script)) {
					return false;
				}
			}
			return true;
		}

		public static long getMiddle(List<Integer> script) {
			int middle = (script.size() / 2);
			return script.get(middle);
		}

}

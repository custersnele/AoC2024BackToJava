package ccfun.day11;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Day11Part {

	public static void main(String[] args) throws IOException {

		Function<Long, List<Long>> rule = new Function<Long, List<Long>>() {
			@Override
			public List<Long> apply(Long aLong) {
				if (aLong == 0) {
					return Collections.singletonList(1L);
				} else {
					String s = String.valueOf(aLong);
					if (s.length() % 2 == 0) {
						//return String.valueOf(aLong).chars().mapToObj(i -> (long)(i - '0')).toList();
						return Arrays.asList(Long.parseLong(s.substring(0, s.length() / 2)), Long.parseLong(s.substring(s.length() / 2)));
					} else {
						return Collections.singletonList(aLong * 2024);
					}
				}
			}
		};
		//List<String> map = Files.readAllLines(Path.of("src/main/resources/day11test.txt"));
		String input = "2 54 992917 5270417 2514 28561 0 990";
		//String input = "125 17";
		Map<Long, Long> numberTimes = new HashMap<>();
		List<Long> list = Arrays.stream(input.split("\\s+")).map(s -> Long.parseLong(s)).toList();
		for (Long key : list) {
			numberTimes.compute(key, (k, v) -> (v == null) ? 1 : v + 1);
		}
		for (int blink = 0; blink < 75; blink++) {
			Map<Long, Long> afterBlink = new HashMap<>();
			for (Long key : numberTimes.keySet()) {
				List<Long> apply = rule.apply(key);
				long times = numberTimes.get(key);
				for (Long result : apply) {
					afterBlink.compute(result, (k, v) -> (v == null) ? times : v + times);
				}
			}
			System.out.println(afterBlink.values().stream().mapToLong(s -> s).sum());
			numberTimes = afterBlink;
		}

	}





}

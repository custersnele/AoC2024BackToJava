package ccfun.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day11 {

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
		List<Long> list = Arrays.stream(input.split("\\s+")).map(Long::parseLong).toList();
		for (int i = 0; i < 75; i++) {
			System.out.println(i);
			list = list.parallelStream().map(rule).flatMap(List::stream).toList();
			//System.out.println(list);
		}
		System.out.println(list.size());
	}





}

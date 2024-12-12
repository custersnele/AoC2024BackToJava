package ccfun.day5;

import java.util.List;

public class PrintRule {

	private int first;
	private int second;

	public PrintRule(String line) {
		String[] split = line.split("\\|");
		first = Integer.parseInt(split[0]);
		second = Integer.parseInt(split[1]);
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	public boolean isOk(List<Integer> script) {
		if (script.contains(first) && script.contains(second)) {
			return script.indexOf(first) < script.indexOf(second);
		} else {
			return true;
		}
	}

	public boolean isBefore(int number1, int number2) {
		if (number1 == first && number2 == second) {
			return true;
		}
		return false;
	}


	public boolean contains(int o1, int o2) {
		return (getFirst() == o1 && getSecond() == o2 )|| (getFirst() == o2 && getSecond() == o1);
	}
}

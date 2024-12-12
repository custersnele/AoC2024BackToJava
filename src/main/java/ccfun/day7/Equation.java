package ccfun.day7;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Equation {
	private static final List<Operator> operators = Arrays.asList(Operator.ADD, Operator.MULTIPLY);
	private long result;
	private List<Long> operands;


	public Equation(String line) {
		String[] split = line.split(":");
		result = Long.parseLong(split[0].trim());
		operands = Arrays.stream(split[1].trim().split(("\\s+"))).map(Long::parseLong).toList();
	}

	public Equation(long result, List<Long> values) {
		this.result = result;
		this.operands = values;
	}

	public long numberOfOperands() {
		return operands.size();
	}

	public long getResult() {
		return result;
	}

	public long getOperand(int i) {
		return operands.get(i);
	}

	public Equation addNext() {
		List<Long> all = new ArrayList<>(operands);
		all.set(0, all.get(0) + all.get(1));
		all.remove(1);
		return new Equation(result, all);
	}

	public Equation multiplyNext() {
		List<Long> all = new ArrayList<>(operands);
		all.set(0, all.get(0) * all.get(1));
		all.remove(1);
		return new Equation(result, all);
	}

	public Equation concatNext() {
		List<Long> all = new ArrayList<>(operands);
		all.set(0, Long.parseLong(String.valueOf(all.get(0)) + String.valueOf(all.get(1))));
		all.remove(1);
		return new Equation(result, all);
	}
}

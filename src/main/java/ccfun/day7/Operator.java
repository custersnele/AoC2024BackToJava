package ccfun.day7;

public enum Operator {
	ADD,
	MULTIPLY,
	CONCAT;

	public long execute(long operand1, long operand2) {
		switch (this) {
			case ADD -> {
				return operand1 + operand2;
			}
			case MULTIPLY -> {
				return operand1 * operand2;
			}
			case CONCAT -> {
				return Long.parseLong(String.valueOf(operand1) + String.valueOf(operand2));
			}
		}
		return -1000;
	}
}

package ccfun.day7;

public class EquationSolver {

	public static boolean isSolvable(Equation equation) {
		if (equation.numberOfOperands() == 2) {
			if (Operator.MULTIPLY.execute(equation.getOperand(0), equation.getOperand(1)) == equation.getResult()) {
				return true;
			} else if (Operator.ADD.execute(equation.getOperand(0), equation.getOperand(1)) == equation.getResult()) {
				return true;
			} else if (Operator.CONCAT.execute(equation.getOperand(0), equation.getOperand(1)) == equation.getResult()) {
				return true;
			}
			return false;
		}
		return isSolvable(equation.addNext()) || isSolvable(equation.multiplyNext()) || isSolvable(equation.concatNext());
	}

}

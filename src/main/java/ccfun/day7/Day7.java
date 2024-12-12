package ccfun.day7;

import ccfun.day6.Direction;
import ccfun.day6.Grid;
import ccfun.day6.Guard;
import ccfun.day6.Position;
import ccfun.day6.RunningPosition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class Day7 {

		public static void main(String[] args) throws IOException {
			List<String> lines = Files.readAllLines(Path.of("src/main/resources/day7.txt"));
			long sum = 0;
			for (String line : lines) {
				Equation equation = new Equation(line);
				System.out.println(line);
				boolean solvable = EquationSolver.isSolvable(equation);
				System.out.println(solvable);
				if (solvable) {
					sum += equation.getResult();
				}
			}
			System.out.println(sum);
		}
}

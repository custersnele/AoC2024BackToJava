package ccfun.day8;

import ccfun.day6.Position;

import java.util.HashSet;
import java.util.Set;

public class Equation {
	// ax + by = c
	private int a;
	private int b;
	private int c;
	private Position p1;
	private Position p2;

	public Equation(Position p1, Position p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Set<Position> findAntinodes(int rows, int cols) {
		int distRow = p2.row() - p1.row();
		int distCol = p2.col() - p1.col();
		Position anode1 = new Position(2 * p1.row() - p2.row(), 2 * p1.col() - p2.col());
		Position anode2 = new Position(2 * p2.row() - p1.row(), 2 * p2.col() - p1.col());
		Set<Position> solutions = new HashSet<>();
	//	System.out.println("Can" + anode1);
		if (anode1.inGrid(rows, cols)) {
			solutions.add(anode1);
		}
	//	System.out.println("Can " + anode2);
		if (anode2.inGrid(rows, cols)) {
			solutions.add(anode2);
		}
		return solutions;

	}

	public Set<Position> findAllAntinodes(int rows, int cols) {
		Set<Position> solutions = new HashSet<>();
		int distRow = p2.row() - p1.row();
		int distCol = p2.col() - p1.col();
		boolean inGrid = true;
		int times = 1;
		while (inGrid) {
			Position anode1 = new Position(p1.row() - times * distRow, p1.col() - times * distCol);
			if (anode1.inGrid(rows, cols)) {
				solutions.add(anode1);
				times++;
			} else {
				inGrid = false;
			}
		}
		inGrid = true;
		times = 1;
		while (inGrid) {
			Position anode2 = new Position(p2.row() + times * distRow, p2.col() + times * distCol);
			if (anode2.inGrid(rows, cols)) {
				solutions.add(anode2);
				times++;
			} else {
				inGrid = false;
			}
		}
		return solutions;
	}


}

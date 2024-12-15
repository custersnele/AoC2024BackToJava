package ccfun.day15;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Warehouse {
	private List<Item> items = new ArrayList<>();
	private Robot robot;

	public Optional<Item> find(int row, int col) {
		return items.stream().filter(i -> i.getLocation().row() == row && i.getLocation().col() == col).findAny();
	}

	public void add(Item item) {
		items.add(item);
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public long getBoxesGPSCoordinates() {
		long sum = items.stream().filter(i -> i instanceof Box).mapToLong(b -> b.getLocation().getGPSCoordinates()).sum();
		return sum;
	}

	public void execute(String instructions) {
		for (int i = 0; i < instructions.length(); i++) {
			char c = instructions.charAt(i);
			//System.out.println(c);
			switch (c) {
				case '^': robot.execute(Move.UP); break;
				case 'v': robot.execute(Move.DOWN); break;
				case '<': robot.execute(Move.LEFT); break;
				case '>': robot.execute(Move.RIGHT); break;
			}
			//print();

		}
		System.out.println("GPC coordinates sum" + getBoxesGPSCoordinates());
	}



	public int getMaxRow() {
		return items.stream().mapToInt(i -> i.getLocation().row()).max().getAsInt();
	}

	public int getMaxCol() {
		return items.stream().mapToInt(i -> i.getLocation().col()).max().getAsInt();
	}

	public void print() {
		for (int i = 0; i <= getMaxRow(); i++) {
			for (int j = 0; j <= getMaxCol(); j++) {
				Optional<Item> item = find(i, j);
				if (item.isEmpty()) {
					if (robot.getLocation().row() == i & robot.getLocation().col() == j) {
						System.out.print("@");
					} else {
						System.out.print(".");
					}
				} else if (item.get() instanceof Box) {
					System.out.print("O");
				} else {
					System.out.print("#");
				}
			}
			System.out.println();
		}
	}
}

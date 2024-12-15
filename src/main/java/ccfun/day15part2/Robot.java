package ccfun.day15part2;

import java.util.ArrayList;
import java.util.List;

public class Robot extends Item {

	public Robot(int row, int col, Warehouse warehouse) {
		super(row, col, warehouse);
	}

	@Override
	public List<Item> findNext(Move move) {
		List<Item> items = new ArrayList<>();
		switch (move) {
			case UP ->
			{
				Location l1 = Move.step(location, Move.UP);
				warehouse.find(l1).ifPresent(items::add);
			}
			case DOWN -> {
				Location l1 = Move.step(location, Move.DOWN);
				warehouse.find(l1).ifPresent(items::add);
			}
			case LEFT -> {
				Location l1 = Move.step(location, Move.LEFT);
				warehouse.find(l1).ifPresent(items::add);
			}
			case RIGHT -> {
				Location l1 = Move.step(location, Move.RIGHT);
				warehouse.find(l1).ifPresent(items::add);
			}
		}
		return items;
	}
}

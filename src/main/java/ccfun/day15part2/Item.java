package ccfun.day15part2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Item {

	protected Location location;
	protected Warehouse warehouse;

	public Item(int row, int col, Warehouse warehouse) {
		this.location = new Location(row, col);
		this.warehouse = warehouse;
	}

	public Location getLocation() {
		return location;
	}

	public List<Item> findNext(Move move) {
		List<Item> items = new ArrayList<>();
		switch (move) {
			case UP ->
			{
				Location l1 = Move.step(location, Move.UP);
				Location l2 = Move.step(Move.step(location, Move.RIGHT), Move.UP);
				warehouse.find(l1).ifPresent(items::add);
				warehouse.find(l2).ifPresent(items::add);
			}
			case DOWN -> {
				Location l1 = Move.step(location, Move.DOWN);
				Location l2 = Move.step(Move.step(location, Move.RIGHT), Move.DOWN);
				warehouse.find(l1).ifPresent(items::add);
				warehouse.find(l2).ifPresent(items::add);
			}
			case LEFT -> {
				Location l1 = Move.step(location, Move.LEFT);
				warehouse.find(l1).ifPresent(items::add);
			}
			case RIGHT -> {
				Location l1 = Move.step(Move.step(location, Move.RIGHT), Move.RIGHT);
				warehouse.find(l1).ifPresent(items::add);
			}
		}
		return items.stream().distinct().toList();
	}

	public boolean execute(Move move) {
		List<Item> next = findNext(move);
		if (next.isEmpty()) {
			this.location = Move.step(location, move);
			return true;
		} else if (next.stream().filter(i -> i instanceof Wall).count() > 0) {
			return false;
		} else if (next.size() == 1 && next.getFirst() instanceof Box){ // it's a box -> try to move it
			if (next.getFirst().canMove(move)) {
				next.getFirst().execute(move);
				this.location = Move.step(location, move);
				return true;
			} else {
				return false;
			}
		} else if (next.size() == 2) {
			if (next.getFirst().canMove(move) && next.getLast().canMove(move)) {
				next.getFirst().execute(move);
				next.getLast().execute(move);
				this.location = Move.step(location, move);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}


	public boolean canMove(Move move) {
		List<Item> next = findNext(move);
		if (next.isEmpty()) {
			return true;
		} else if (next.stream().filter(i -> i instanceof Wall).count() > 0) {
			return false;
		} else {
			return next.getFirst().canMove(move) && next.getLast().canMove(move);
		}
	}
}

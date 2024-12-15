package ccfun.day15;

import java.util.Optional;

public abstract class Item {

	private Location location;
	private Warehouse warehouse;

	public Item(int row, int col, Warehouse warehouse) {
		this.location = new Location(row, col);
		this.warehouse = warehouse;
	}

	public Location getLocation() {
		return location;
	}

	public Optional<Item> findNext(Move move) {
		Location step = Move.step(location, move);
		return warehouse.find(step.row(), step.col());
	}

	public boolean execute(Move move) {
		Optional<Item> next = findNext(move);
		if (next.isEmpty()) {
			this.location = Move.step(location, move);
			return true;
		} else if (next.get() instanceof Wall) {
			return false;
		} else { // it's a box -> try to move it
			if (next.get().execute(move)) {
				this.location = Move.step(location, move);
				return true;
			} else {
				return false;
			}
		}
	}
}

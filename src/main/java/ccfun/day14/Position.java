package ccfun.day14;

public record Position(int x, int y) {

	public Position add(Position other, int maxX, int maxY) {
		int newX = x + other.x;
		newX = getNext(newX, maxX);
		int newY = y + other.y;
		newY = getNext(newY, maxY);
		return new Position(newX, newY);
	}

	private static int getNext(int next, int max) {
		if (next < 0) {
			next = max + next;
		} else if (next >= max) {
			next = next - max;
		}
		return next;
	}

	public int distance(Position other) {
		return Math.abs(x - other.x) + Math.abs(y - other.y);
	}
}

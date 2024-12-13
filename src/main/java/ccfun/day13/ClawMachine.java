package ccfun.day13;

public class ClawMachine {
	private Position prize;
	private Position buttonA;
	private Position buttonB;

	public long[] solve() {
		long b = (prize.y() * buttonA.x() - prize.x() * buttonA.y()) /
				(-buttonB.x() * buttonA.y() + buttonB.y() * buttonA.x());
		long a = (prize.x() - b * buttonB.x()) / buttonA.x();
		boolean check = a * buttonA.x() + b * buttonB.x() == prize.x();
		check = check && (a * buttonA.y() + b * buttonB.y() == prize.y());
		if (!check) {
			throw new RuntimeException("Unsolvable");
		}
		return new long[]{a,b};
	}

	public void setPrize(Position prize) {
		this.prize = new Position(10000000000000L + prize.x(), 10000000000000L + prize.y());
	}

	public void setButtonA(Position buttonA) {
		this.buttonA = buttonA;
	}

	public void setButtonB(Position buttonB) {
		this.buttonB = buttonB;
	}

	// B=
	//−22⋅34+67⋅94
	//5400⋅94−8400⋅34
	//​
}

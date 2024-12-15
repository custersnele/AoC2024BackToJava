package ccfun.day14;

import java.util.Arrays;
import java.util.List;

public class Robot {
	private Position currentPosition;
	private Position velocity;
	private int times;
	private int maxX;
	private int maxY;

	public Robot(String t) { //p=9,5 v=-3,-3
		t = t.replace("p=", "").replace("v=", "");
		String[] split = t.split(" ");
		String[] p = split[0].split(",");
		String[] v = split[1].split(",");
		currentPosition = new Position(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
		velocity = new Position(Integer.parseInt(v[0]), Integer.parseInt(v[1]));
	}

	public boolean isAt(int x, int y) {
		return currentPosition.x() == x && currentPosition.y() == y;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public void step() {
		//System.out.println(currentPosition);

		currentPosition = currentPosition.add(velocity, maxX, maxY);
		//	System.out.println(currentPosition);

	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public int countNeighbours(List<Robot> robots) {
		return (int) robots.stream().filter(r -> r.getCurrentPosition().distance(this.getCurrentPosition()) < 5).count();
	}
}

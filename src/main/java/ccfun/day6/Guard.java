package ccfun.day6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Guard {
	private Direction direction;
	private Position position;
	private Grid grid;
	private Set<RunningPosition> visited  = new HashSet<>();
	private boolean out = false;
	private boolean loop = false;

	public Guard(Direction currentDirection, Position currentPosition) {
		this.direction = currentDirection;
		this.position = currentPosition;
	}

	public Guard() {
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void step() {
		Position position = Direction.next(this.position, direction);
		if (grid.isObstacle(position)) {
			direction = Direction.turn90(direction);
		} else if (grid.isIn(position)){
			RunningPosition next = new RunningPosition(position, direction);
			if (visited.contains(next)) {
				loop = true;
			} else {
				visited.add(next);
				this.position = position;
			}
			//System.out.println(visited + " " + position);
		} else {
			out = true;
		}
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public boolean isOfGrid() {
		return out;
	}

	public Set<RunningPosition> getVisited() {
		return visited;
	}

	public void runRound(RunningPosition start) {
		visited = new HashSet<>();
		out = false;
		loop = false;
		visited.add(new RunningPosition(start.position(), start.direction()));
		setPostion(start);
		while (!isOfGrid() && !loop) {
			step();
		}
	}

	public void setPostion(RunningPosition start) {
		this.direction = start.direction();
		this.position = start.position();
	}

	public long getPlaces() {
		return visited.stream().map(RunningPosition::position).distinct().count();
	}

	public boolean isLoop() {
		return loop;
	}
}

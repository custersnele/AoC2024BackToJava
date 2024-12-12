package ccfun.day10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Node {
	private int height;
	private int row;
	private int col;

	public Node( int row, int col, int height) {
		this.height = height;
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getHeight() {
		return height;
	}

	private List<Node> getNeighbours(List<Node> allNodes) {
		return allNodes.stream().filter(this::isNeighbour).toList();
	}

	public boolean isNeighbour(Node other) {
		return Math.abs(this.row - other.row) + Math.abs(this.col - other.col) == 1 &&
				other.height == this.height + 1;

	}

	public boolean isTrailhead() {
		return height == 0;
	}

	public int findPaths(List<Node> allNodes) {
		int pathCount = 0;
		Queue<List<Node>> queue = new LinkedList<>();
		queue.add(List.of(this));

		while (!queue.isEmpty()) {
			List<Node> currentPath = queue.poll();
			Node currentNode = currentPath.get(currentPath.size() - 1);

			if (currentNode.getHeight() == 9) {
				printPath(currentPath);
				pathCount++;
				continue; // Do not expand paths further if we reached height 9
			}

			for (Node neighbor : currentNode.getNeighbours(allNodes)) {
				List<Node> newPath = new ArrayList<>(currentPath);
				newPath.add(neighbor);
				queue.add(newPath);
			}
		}
		return pathCount;
	}

	public int findDestinations(List<Node> allNodes) {
		Set<Node> destinations = new HashSet<>();
		Queue<List<Node>> queue = new LinkedList<>();
		queue.add(List.of(this));

		while (!queue.isEmpty()) {
			List<Node> currentPath = queue.poll();
			Node currentNode = currentPath.get(currentPath.size() - 1);

			if (currentNode.getHeight() == 9) {
				destinations.add(currentNode);
				continue; // Do not expand paths further if we reached height 9
			}

			for (Node neighbor : currentNode.getNeighbours(allNodes)) {
				List<Node> newPath = new ArrayList<>(currentPath);
				newPath.add(neighbor);
				queue.add(newPath);
			}
		}
		return destinations.size();
	}

	public void printPath(List<Node> path) {
		for (Node node: path) {
			System.out.print(node);
		}
		System.out.println();
	}

	@Override
	public String toString() {
		return "Node{" +
				"height=" + height +
				", row=" + row +
				", col=" + col +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Node node = (Node) o;
		return height == node.height && row == node.row && col == node.col;
	}

	@Override
	public int hashCode() {
		int result = height;
		result = 31 * result + row;
		result = 31 * result + col;
		return result;
	}
}

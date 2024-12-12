package ccfun.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordGrid {

	private List<String> lines;

	public WordGrid(List<String> lines) {
		this.lines = lines;
	}

	public long searchHorizontalLeftToRight(Pattern pattern) {
		long count = 0;
		for (String line : lines) {
			Matcher matcher = pattern.matcher(line);
			count += matcher.results().count();
		}
		return count;
	}

	public long searchHorizontalRightToLeft(Pattern pattern) {
		long count = 0;
		for (String line : lines) {
			Matcher matcher = pattern.matcher(new StringBuffer(line).reverse());
			count += matcher.results().count();
		}
		return count;
	}

	public long searchVerticalUpToDown(Pattern pattern) {
		long count = 0;
		for (String line : getLinesVerical()) {
			Matcher matcher = pattern.matcher(line);
			count += matcher.results().count();
		}
		return count;
	}

	public long searchVerticalDownToUp(Pattern pattern) {
		long count = 0;
		for (String line : getLinesVerical()) {
			Matcher matcher = pattern.matcher(new StringBuffer(line).reverse());
			count += matcher.results().count();
		}
		return count;
	}

	public long searchDiagonalLeftToRight(Pattern pattern) {
		long count = 0;
		for (String line : getLinesDiagonal()) {
			Matcher matcher = pattern.matcher(line);
			count += matcher.results().count();
		}
		return count;
	}

	public long searchDiagonalRightToLeft(Pattern pattern) {
		long count = 0;
		for (String line : getLinesDiagonal()) {
			Matcher matcher = pattern.matcher(new StringBuffer(line).reverse());
			count += matcher.results().count();
		}
		return count;
	}

	public long searchOtherDiagonalLeftToRight(Pattern pattern) {
		long count = 0;
		for (String line : getLinesDiagonalLeft()) {
			Matcher matcher = pattern.matcher(line);
			count += matcher.results().count();
		}
		return count;
	}

	public long searchOtherDiagonalRightToLeft(Pattern pattern) {
		long count = 0;
		for (String line : getLinesDiagonalLeft()) {
			Matcher matcher = pattern.matcher(new StringBuffer(line).reverse());
			count += matcher.results().count();
		}
		return count;
	}

	private List<String> getLinesVerical() {
		List<String> newLines = new ArrayList<>();
		for (int col = 0; col < lines.getFirst().length(); col++) {
			StringBuffer next = new StringBuffer();
			for (int row = 0; row < lines.size(); row++) {
				String str = lines.get(row);
				next.append(str.charAt(col));
			}
			newLines.add(next.toString());
		}
		return newLines;
	}

	private List<String> getLinesDiagonal() {
		List<String> newLines = new ArrayList<>();
		for (int col = lines.get(0).length() - 1; col >= 0; col--) {
			StringBuffer next = new StringBuffer();
			int curCol = col;
			int row = 0;
			while (curCol < lines.get(0).length()) {
				next.append(lines.get(row++).charAt(curCol++));
			}
			newLines.add(next.toString());
		}
		for (int row = 1; row < lines.size(); row++) {
			StringBuffer next = new StringBuffer();
			int curRow = row;
			int col = 0;
			while (curRow < lines.size()) {
				next.append(lines.get(curRow++).charAt(col++));
			}
			newLines.add(next.toString());
		}
		return newLines;
	}


	private List<String> getLinesDiagonalLeft() {
		List<String> newLines = new ArrayList<>();
		for (int row = 0; row < lines.size(); row++) {
			StringBuffer next = new StringBuffer();
			int curRow = row;
			int col = 0;
			while (curRow >= 0) {
				next.append(lines.get(curRow--).charAt(col++));
			}
			newLines.add(next.toString());
		}
		for (int col = 1; col < lines.getFirst().length(); col++) {
			StringBuffer next = new StringBuffer();
			int curCol = col;
			int row = lines.size() - 1;
			while (curCol < lines.get(0).length()) {
				next.append(lines.get(row--).charAt(curCol++));
			}
			newLines.add(next.toString());
		}

		return newLines;
	}


	public long countXMas() {
		int count = 0;
		for (int row = 0; row < lines.size(); row++) {
			for (int col = 0; col < lines.getFirst().length(); col++) {
				if (isXmas(row, col)) {
					count++;
				}
			}
		}
		return count;
	}


	private boolean isXmas(int row, int col) {
		if (getElement(row, col) != 'A') {
			return false;
		}
		char leftup = getElement(row - 1, col - 1);
		char rightup = getElement(row - 1, col + 1);
		char leftdown = getElement(row + 1, col - 1);
		char rightdown = getElement(row + 1, col + 1);
		return (leftup == 'M' && rightup == 'S' && leftdown == 'M' && rightdown == 'S') ||
		 (leftup == 'S' && rightup == 'S' && leftdown == 'M' && rightdown == 'M') ||
		 (leftup == 'S' && rightup == 'M' && leftdown == 'S' && rightdown == 'M') ||
		 (leftup == 'M' && rightup == 'M' && leftdown == 'S' && rightdown == 'S');
	}

	public char getElement(int row, int col) {
		try {
			return lines.get(row).charAt(col);
		} catch (IndexOutOfBoundsException e) {
			return ' ';
		}
	}


}

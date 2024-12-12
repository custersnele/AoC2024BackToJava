package ccfun.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class Day4 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/day4.txt"));
		Pattern pattern = Pattern.compile("XMAS");
		WordGrid wordGrid = new WordGrid(lines);
		long count = wordGrid.searchHorizontalLeftToRight(pattern);
		count += wordGrid.searchHorizontalRightToLeft(pattern);
		count += wordGrid.searchVerticalUpToDown(pattern);
		count += wordGrid.searchVerticalDownToUp(pattern);
		count += wordGrid.searchDiagonalLeftToRight(pattern);
		count += wordGrid.searchDiagonalRightToLeft(pattern);
		count += wordGrid.searchOtherDiagonalLeftToRight(pattern);
		count += wordGrid.searchOtherDiagonalRightToLeft(pattern);
		System.out.println(count);

		System.out.println(wordGrid.countXMas());

	}
}

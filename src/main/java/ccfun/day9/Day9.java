package ccfun.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day9 {

	public static void main(String[] args) throws IOException {
		String layout =  Files.readAllLines(Path.of("src/main/resources/day9.txt")).getFirst();
		//String layout = "2333133121414131402";
		long fileId = 0;
		boolean file = true;
		DiskMap diskMap = new DiskMap();
		for (int i = 0; i < layout.length(); i++) {
			int blocks = Integer.parseInt(layout.substring(i, i + 1));
			DiskBlock block = new DiskBlock(blocks);
			diskMap.addDiskBlock(block);
			if (file) {
				block.setFileId(fileId);
				fileId++;
			}
			file = !file;
		}
		//diskMap.show();
		diskMap.compact2();
		diskMap.show();
		//diskMap.print();
		System.out.println(diskMap.calculateChecksum());


	}
}

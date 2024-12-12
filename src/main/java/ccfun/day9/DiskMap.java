package ccfun.day9;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DiskMap {

	private List<DiskBlock> diskBlocks = new ArrayList<>();

	public  void addDiskBlock(DiskBlock diskBlock) {
		diskBlocks.add(diskBlock);
	}

	public BigInteger calculateChecksum() {
		BigInteger checksum = BigInteger.ZERO;
		int idx = 0;
		for (DiskBlock diskBlock : diskBlocks) {
			if (diskBlock.isFile()) {
				checksum = checksum.add(diskBlock.checksum(idx));
			}
			idx += diskBlock.getBlocks();
		}
		return checksum;
	}

	public int getFirstFreeIdx() {
		for (int i = 0; i < diskBlocks.size(); i++) {
			if (diskBlocks.get(i).isFree()) {
				return i;
			}
		}
		return -1;
	}

	public int getFirstFreeIdx(int size) {
		for (int i = 0; i < diskBlocks.size(); i++) {
			if (diskBlocks.get(i).isFree() && diskBlocks.get(i).getBlocks() >= size) {
				return i;
			}
		}
		return -1;
	}

	public long getTotalSize() {
		long totalSize = 0;
		for (DiskBlock block : diskBlocks) {
			totalSize += block.getBlocks();
		}
		return totalSize;
	}

	public boolean isCompact() {
		return getFirstFreeIdx() > getLastFileIdx();
	}

	public int getLastFileIdx() {
		for (int i = diskBlocks.size() - 1; i >= 0; i--) {
			DiskBlock block = diskBlocks.get(i);
			if (block.isFile() && block.isMove()) {
				return i;
			}
		}
		return -1;
	}

	public void compactLastFile() {
		int firstFreeIdx = getFirstFreeIdx();
		int lastFileIdx = getLastFileIdx();
		DiskBlock free = diskBlocks.get(firstFreeIdx);
		DiskBlock file = diskBlocks.get(lastFileIdx);
		if (free.getBlocks() > file.getBlocks()) {
			free.useBlocks(file.getBlocks());
			diskBlocks.remove(lastFileIdx);
			diskBlocks.add(new DiskBlock(file.getBlocks()));
			diskBlocks.add(firstFreeIdx, file);
		} else if (free.getBlocks() == file.getBlocks()) {
			diskBlocks.remove(lastFileIdx);
			diskBlocks.remove(firstFreeIdx);
			diskBlocks.add(firstFreeIdx, file);
			diskBlocks.add(new DiskBlock(file.getBlocks()));
		} else {
			free.setFileId(file.getFileId());
			int blocks = free.getBlocks();
			file.useBlocks(blocks);
			diskBlocks.add(new DiskBlock(free.getBlocks()));
		}
	}

	public void compactFullLastFile() {
		int lastFileIdx = getLastFileIdx();
		DiskBlock file = diskBlocks.get(lastFileIdx);
		int firstFreeIdx = getFirstFreeIdx(file.getBlocks());
		if (lastFileIdx < firstFreeIdx) {
			file.setMove(false);
			return;
		}
		if (firstFreeIdx == -1) {
			file.setMove(false);
			return;
		}
		DiskBlock free = diskBlocks.get(firstFreeIdx);
		file.setMove(false);
		long id = file.getFileId();
		file.setFileId(-1); // file komt vrij
		if (free.getBlocks() > file.getBlocks()) {
			free.useBlocks(file.getBlocks());
			diskBlocks.add(firstFreeIdx, new DiskBlock(file.getBlocks(), id));
		} else if (free.getBlocks() == file.getBlocks()) {
			free.setFileId(id);
			file.setFileId(-1);
			//diskBlocks.add(new DiskBlock(file.getBlocks()));
		}
		//show();
	}

	public void compact() {
		while (!isCompact()) {
			compactLastFile();
			//diskMap.show();
			//System.out.println();
		}
	}

	public void compact2() {
		while (!isCompact()) {
			compactFullLastFile();
			//diskMap.show();
			//System.out.println();
		}
	}




	public void show() {
		for (DiskBlock block : diskBlocks) {
			for (int i = 0; i < block.getBlocks(); i++) {
				if (block.isFree()) {
					System.out.print(".");
				} else {
					System.out.print("[" + block.getFileId() + "]");
				}
			}
		}
		System.out.println();
	}

	public void print() {
		int idx = 0;
		for (DiskBlock block : diskBlocks) {
			System.out.print("Segment(" + (block.isFree()? "." : block.getFileId()) + "," + idx + "," + block.getBlocks() + "), ");
			idx += block.getBlocks();
		}
	}


	public void printLastFile() {
		DiskBlock lastFile = diskBlocks.get(getLastFileIdx());
		System.out.println(lastFile.getFileId());
		System.out.println(lastFile.getBlocks());
	}
}

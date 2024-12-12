package ccfun.day9;

import java.math.BigInteger;

public class DiskBlock {
	private int blocks;
	private long fileId;
	private boolean move = true;

	public DiskBlock(int blocks) {
		this.blocks = blocks;
		this.fileId = -1;
	}

	public DiskBlock(int blocks, long fileId) {
		this.blocks = blocks;
		this.fileId = fileId;
	}

	public boolean isFree() {
		return fileId == -1;
	}

	public int getBlocks() {
		return blocks;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public void useBlocks(int blocks) {
		this.blocks -= blocks;
	}

	public boolean isFile() {
		return fileId >= 0;
	}

	public void addBlocks(int blocks) {
		this.blocks += blocks;
	}

	public BigInteger checksum(int idx) {
		BigInteger checksum = BigInteger.ZERO;
		for (int i = 0; i < blocks; i++) {
			checksum = checksum.add(BigInteger.valueOf(fileId * (idx + i)));
		}
		return checksum;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public boolean isMove() {
		return move;
	}
}

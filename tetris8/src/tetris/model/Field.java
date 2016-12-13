package tetris.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tetris.gui.Block;

public class Field {
	private int height, width;
	private List<Block> blocks;

	public Field(int fieldHeight, int fieldWidth) {
		this.setHeight(fieldHeight);
		this.setWidth(fieldWidth);
		this.blocks = new ArrayList<>();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public void addBlocks(Block[] blocks2) {
		for (Block b : blocks2) {
			blocks.add(b);
		}
	}

	public void removeBlocks() {
		blocks = new ArrayList<>();
	}

	public int removeFullRows() {
		int count = 0;
		for (int i = 0; i < height; i++) {
			if (isRowFull(i)) {
				// decrement index to check if 2 successive rows are full
				removeRow(i--);
				count++;
			}
		}
		return count;
	}

	private void removeRow(int i) {
		Iterator<Block> it = blocks.iterator();
		while (it.hasNext()) {
			Block b = it.next();
			if (b.y == i)
				it.remove();
		}

		for (Block b : blocks) {
			if (b.y > i)
				b.y = b.y - 1;
		}

	}

	private boolean isRowFull(int i) {
		for (int j = 0; j < width; j++) {
			boolean isCellFull = false;
			for (Block b : blocks) {
				if (b.x == j && b.y == i) {
					isCellFull = true;
					break;
				}
			}
			if (!isCellFull)
				return false;
		}
		return true;
	}

	public void detectCollision(Block[] figureBlocks) throws CollisionException {
		for (Block figureBlock : figureBlocks) {
			if (figureBlock.x >= width)
				throw new CollisionException("x zu gross");
			else if (figureBlock.y >= height)
				throw new CollisionException("y zu gross");
			else if (figureBlock.x < 0)
				throw new CollisionException("x zu klein");
			else if (figureBlock.y < 0)
				throw new CollisionException("y zu klein");
			else {
				for (Block b : blocks) {
					if (figureBlock.x == b.x && figureBlock.y == b.y)
						throw new CollisionException("Kollision bei " + b.x + "/" + b.y);
				}

			}
		}
	}

}

package tetris.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
		blocks = blocks.stream().filter(b -> b.y != i).collect(Collectors.toList());
		blocks.stream().filter(b -> b.y > i).forEach(b -> b.y -= 1);

	}

	private boolean isRowFull(int i) {
		return blocks.stream().filter(b -> b.y == i).count() == width;
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

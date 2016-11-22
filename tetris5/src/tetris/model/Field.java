package tetris.model;

import tetris.gui.Block;

public class Field {
	private int height, width;

	public Field(int fieldHeight, int fieldWidth) {
		this.setHeight(fieldHeight);
		this.setWidth(fieldWidth);
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

	public void detectCollision(Block[] blocks) throws CollisionException {
		for (Block b : blocks) {
			if (b.x >= width)
				throw new CollisionException("x zu gross");
			else if (b.y >= height)
				throw new CollisionException("y zu gross");
			else if (b.x < 0)
				throw new CollisionException("x zu klein");
			else if (b.y < 0)
				throw new CollisionException("y zu klein");
		}
	}

}

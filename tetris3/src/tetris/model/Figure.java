package tetris.model;

import tetris.gui.Block;

public abstract class Figure {
	protected Block[] blocks = new Block[4];

	public Block[] getBlocks() {
		return blocks;
	}

	public void move(int dx, int dy) {
		for (Block b : blocks) {
			b.x += dx;
			b.y += dy;
		}
	}

	public void rotate(int d) {
		int cx = blocks[0].x;
		int cy = blocks[0].y;
		for (Block b : blocks) {
			int x = b.x;
			int y = b.y;
			b.x = d * (y - cy) + cx;
			b.y = -1 * d * (x - cx) + cy;
		}
	}
}

class IFigure extends Figure {
	private static final int col = 1;

	public IFigure() {
		blocks[0] = new Block(2, 19, col);
		blocks[1] = new Block(1, 19, col);
		blocks[2] = new Block(3, 19, col);
		blocks[3] = new Block(4, 19, col);
	}
}

class JFigure extends Figure {
	private static final int col = 2;

	public JFigure() {
		blocks[0] = new Block(3, 17, col);
		blocks[1] = new Block(2, 17, col);
		blocks[2] = new Block(3, 18, col);
		blocks[3] = new Block(3, 19, col);
	}
}

class LFigure extends Figure {
	private static final int col = 3;

	public LFigure() {
		blocks[0] = new Block(3, 17, col);
		blocks[1] = new Block(4, 17, col);
		blocks[2] = new Block(3, 18, col);
		blocks[3] = new Block(3, 19, col);
	}
}

class OFigure extends Figure {
	private static final int col = 4;

	public OFigure() {
		blocks[0] = new Block(3, 19, col);
		blocks[1] = new Block(3, 18, col);
		blocks[2] = new Block(4, 19, col);
		blocks[3] = new Block(4, 18, col);
	}

	@Override
	public void rotate(int d) {
	}

}

class SFigure extends Figure {
	private static final int col = 5;

	public SFigure() {
		blocks[0] = new Block(3, 19, col);
		blocks[1] = new Block(4, 19, col);
		blocks[2] = new Block(3, 18, col);
		blocks[3] = new Block(2, 18, col);
	}
}

class TFigure extends Figure {
	private static final int col = 6;

	public TFigure() {
		blocks[0] = new Block(3, 19, col);
		blocks[1] = new Block(2, 19, col);
		blocks[2] = new Block(4, 19, col);
		blocks[3] = new Block(3, 18, col);
	}
}

class ZFigure extends Figure {
	private static final int col = 7;

	public ZFigure() {
		blocks[0] = new Block(3, 19, col);
		blocks[1] = new Block(4, 19, col);
		blocks[2] = new Block(4, 18, col);
		blocks[3] = new Block(5, 18, col);
	}
}

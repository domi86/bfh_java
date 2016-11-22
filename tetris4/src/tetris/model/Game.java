package tetris.model;

import tetris.gui.ActionHandler;
import tetris.gui.GUI;

public class Game {
	private GUI gui;
	private Figure figure;

	public Game(GUI gui) {
		this.gui = gui;
	}

	public void start() {
		figure = createFigure();
		gui.clear();
		gui.drawBlocks(figure.getBlocks());
		gui.setActionHandler(new FigureController());
	}

	private Figure createFigure() {
		int i = (int) (7 * Math.random());
		int px = gui.getFieldWidth() / 2 - 1;
		int py = gui.getFieldHeight() - 1;
		switch (i) {
		case 0:
			return new IFigure(px, py);
		case 1:
			return new JFigure(px, py);
		case 2:
			return new LFigure(px, py);
		case 3:
			return new OFigure(px, py);
		case 4:
			return new SFigure(px, py);
		case 5:
			return new TFigure(px, py);
		case 6:
			return new ZFigure(px, py);
		default:
			return new IFigure(px, py);
		}
	}

	private void updateGui() {
		gui.clear();
		gui.drawBlocks(figure.getBlocks());
	}

	private class FigureController implements ActionHandler {
		@Override
		public void rotateRight() throws Exception {
			figure.rotate(1);
			updateGui();
		}

		@Override
		public void rotateLeft() throws Exception {
			figure.rotate(-1);
			updateGui();
		}

		@Override
		public void moveRight() throws Exception {
			figure.move(1, 0);
			updateGui();
		}

		@Override
		public void moveLeft() throws Exception {
			figure.move(-1, 0);
			updateGui();
		}

		@Override
		public void moveDown() throws Exception {
			figure.move(0, -1);
			updateGui();
		}

		@Override
		public void drop() throws Exception {
			// TODO Auto-generated method stub
		}
	}
}

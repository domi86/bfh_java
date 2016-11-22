package tetris.model;

import tetris.gui.ActionHandler;
import tetris.gui.GUI;

public class Game {
	private GUI gui;
	private Figure figure;
	private Field field;

	public Game(GUI gui) {
		this.gui = gui;
		this.field = new Field(gui.getFieldHeight(), gui.getFieldWidth());
	}

	public void start() {
		createFigure();
		gui.setActionHandler(new FigureController());
		gui.drawBlocks(figure.getBlocks());
	}

	private void createFigure() {
		int i = (int) (7 * Math.random());
		int px = gui.getFieldWidth() / 2 - 1;
		int py = gui.getFieldHeight() - 1;
		switch (i) {
		case 0:
			this.figure = new IFigure(px, py);
		case 1:
			this.figure = new JFigure(px, py);
		case 2:
			this.figure = new LFigure(px, py);
		case 3:
			this.figure = new OFigure(px, py);
		case 4:
			this.figure = new SFigure(px, py);
		case 5:
			this.figure = new TFigure(px, py);
		case 6:
			this.figure = new ZFigure(px, py);
		default:
			this.figure = new IFigure(px, py);
		}
	}

	/**
	 * Clear gui to remove old figure-state and repaint current figure-state
	 */
	private void updateGui() {
		gui.clear();
		gui.drawBlocks(figure.getBlocks());
	}

	private class FigureController implements ActionHandler {
		@Override
		public void rotateRight() throws Exception {
			figure.rotate(1);
			try {
				field.detectCollision(figure.getBlocks());
			} catch (CollisionException e) {
				figure.rotate(-1);
			}
			updateGui();
		}

		@Override
		public void rotateLeft() throws Exception {
			figure.rotate(-1);
			try {
				field.detectCollision(figure.getBlocks());
			} catch (CollisionException e) {
				figure.rotate(1);
			}
			updateGui();
		}

		@Override
		public void moveRight() throws Exception {
			figure.move(1, 0);
			try {
				field.detectCollision(figure.getBlocks());
			} catch (CollisionException e) {
				figure.move(-1, 0);
			}
			updateGui();
		}

		@Override
		public void moveLeft() throws Exception {
			figure.move(-1, 0);
			try {
				field.detectCollision(figure.getBlocks());
			} catch (CollisionException e) {
				figure.move(1, 0);
			}
			updateGui();
		}

		@Override
		public void moveDown() throws Exception {
			figure.move(0, -1);
			try {
				field.detectCollision(figure.getBlocks());
			} catch (CollisionException e) {
				figure.move(0, 1);
			}
			updateGui();
		}

		@Override
		public void drop() throws Exception {
			try {
				while (true) {
					figure.move(0, -1);
					field.detectCollision(figure.getBlocks());
				}
			} catch (CollisionException e) {
				figure.move(0, 1);
			}
			updateGui();
		}
	}
}

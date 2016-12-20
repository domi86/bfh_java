package tetris.model;

import tetris.gui.ActionHandler;
import tetris.gui.GUI;

public class Game {
	private GUI gui;
	private Figure figure;
	private Field field;
	private Scoring scoring;
	private Thread moveDownThread;

	public Game(GUI gui) {
		this.gui = gui;
		this.field = new Field(gui.getFieldHeight(), gui.getFieldWidth());
	}

	public void start() {
		createFigure();
		scoring = new Scoring();
		gui.setActionHandler(new FigureController());
		gui.drawBlocks(figure.getBlocks());
		updateGui();
		moveDownThread = new Thread(new FigureController());
		moveDownThread.setName("moveDownThread");
		moveDownThread.start();
	}

	private void stop() {
		moveDownThread.interrupt();
		figure = null;
		gui.setActionHandler(null);
		scoring.reset();
		updateGui();
	}

	private void createFigure() {
		int i = (int) (7 * Math.random());
		int px = gui.getFieldWidth() / 2 - 1;
		int py = gui.getFieldHeight() - 1;
		switch (i) {
		case 0:
			this.figure = new IFigure(px, py);
			break;
		case 1:
			this.figure = new JFigure(px, py);
			break;
		case 2:
			this.figure = new LFigure(px, py);
			break;
		case 3:
			this.figure = new OFigure(px, py);
			break;
		case 4:
			this.figure = new SFigure(px, py);
			break;
		case 5:
			this.figure = new TFigure(px, py);
			break;
		case 6:
			this.figure = new ZFigure(px, py);
			break;
		default:
			this.figure = new IFigure(px, py);
		}
	}

	/**
	 * Clear gui to remove old figure-state and repaint current figure-state
	 */
	private void updateGui() {
		gui.clear();
		if (figure != null)
			gui.drawBlocks(figure.getBlocks());
		gui.drawBlocks(field.getBlocks());
		gui.setLevel(scoring.getLevel());
		gui.setScore(scoring.getScore());
		gui.setHighScore(scoring.getHighScore());
	}

	public void figureLanded() {
		field.addBlocks(figure.getBlocks());
		scoring.rewardFullRows(field.removeFullRows());
		createFigure();
		try {
			field.detectCollision(figure.getBlocks());
		} catch (CollisionException e) {
			stop();
			System.out.println("ende");
		}

	}

	private boolean execute(Movement m1, Movement m2) {
		m1.make(figure);
		try {
			field.detectCollision(figure.getBlocks());
			updateGui();
			return true;
		} catch (CollisionException e) {
			m2.make(figure);
			return false;
		}
	}

	private class FigureController implements ActionHandler, Runnable {
		@Override
		public void rotateRight() {
			execute(f -> f.rotate(1), f -> f.rotate(-1));
		}

		@Override
		public void rotateLeft() {
			execute(f -> f.rotate(-1), f -> f.rotate(1));
		}

		@Override
		public void moveRight() {
			execute(f -> f.move(1, 0), f -> f.move(-1, 0));
		}

		@Override
		public void moveLeft() {
			execute(f -> f.move(-1, 0), f -> f.move(1, 0));
		}

		@Override
		public void moveDown() {
			if (!execute(f -> f.move(0, -1), f -> f.move(0, 1)))
				figureLanded();
		}

		@Override
		public void drop() throws Exception {
			while(execute(f -> f.move(0,-1), f-> f.move(0,1))){
				//moo
			}
		}

		@Override
		public void run() {
			try {
				while (true) {
					int l = scoring.getLevel();
					moveDown();
					Thread.sleep(1000 - (l - 1) * 100);
				}
			} catch (InterruptedException e) {
				System.out.println(java.time.Instant.now().toString() + ": moveDownThread interrupted");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

package tetris.model;

import tetris.gui.ActionEvent;
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

		while (true) {
			ActionEvent e = gui.waitEvent();
			handleEvent(e);
			updateGui();
		}
	}

	private Figure createFigure() {
		int i = (int) (7 * Math.random());
		switch (i) {
		case 0:
			return new IFigure();
		case 1:
			return new JFigure();
		case 2:
			return new LFigure();
		case 3:
			return new OFigure();
		case 4:
			return new SFigure();
		case 5:
			return new TFigure();
		case 6:
			return new ZFigure();
		default:
			return new IFigure();
		}
	}

	public void handleEvent(ActionEvent e) {
		switch (e) {
		case DROP:
			break;
		case MOVE_DOWN:
			figure.move(0, -1);
			break;
		case MOVE_LEFT:
			figure.move(-1, 0);
			break;
		case MOVE_RIGHT:
			figure.move(1, 0);
			break;
		case ROTATE_LEFT:
			figure.rotate(-1);
			break;
		case ROTATE_RIGHT:
			figure.rotate(1);
			break;
		default:
			break;
		}

	}

	public void updateGui() {
		gui.clear();
		gui.drawBlocks(figure.getBlocks());
	}
}

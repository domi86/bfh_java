package tetris;

import tetris.gui.GUI;
import tetris.model.Game;

public class Tetris {

	private final static int width = 10;
	private final static int height = 20;
	private final static int scale_factor = 6;

	public static void main(String[] args) {
		int w = width;
		int h = height;
		if (args.length == 2) {
			w = Integer.parseInt(args[0]);
			h = Integer.parseInt(args[1]);
		}

		GUI gui = new GUI(w, h, scale_factor);
		Game game = new Game(gui);
		game.start();
	}
}

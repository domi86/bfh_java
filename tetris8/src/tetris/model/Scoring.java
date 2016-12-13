package tetris.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Scoring {
	private static final int rowsNeededForNextLevel = 10;
	private int level, score, highScore, rowCounter;
	private final String highScorePersPath = "data/highScore.txt";

	public Scoring() {
		level = 1;
		score = 0;
		rowCounter = 0;
		loadHighScore();
	}

	public void rewardFullRows(int rowCount) {
		switch (rowCount) {
		case 1:
			score += 40 * level;
			break;
		case 2:
			score += 100 * level;
			break;
		case 3:
			score += 300 * level;
			break;
		case 4:
			score += 1200 * level;
			break;
		default:
			break;
		}
		rowCounter += rowCount;
		if(rowCounter >= rowsNeededForNextLevel) {
			level++;
			rowCounter %= rowsNeededForNextLevel;
		}
		if (score > highScore)
			highScore = score;
	}

	public int getLevel() {
		return level;
	}

	public int getScore() {
		return score;
	}

	public int getHighScore() {
		return highScore;
	}

	public void reset() {
		score = highScore = 0;
		saveHighScore();

	}

	public void loadHighScore() {
		try (DataInputStream dis = new DataInputStream(new FileInputStream(highScorePersPath))) {
			highScore = dis.readInt();
		} catch (IOException e) {
			System.out.println(java.time.Instant.now().toString() + ": " + e.getMessage());
		}
	}

	public void saveHighScore() {
		try {
			File highScoreFile = new File(highScorePersPath);
			File parentDir = new File(highScoreFile.getParent());
			if(!parentDir.exists())
				parentDir.mkdir();
			if(!highScoreFile.exists())
				highScoreFile.createNewFile();
		} catch (IOException e) {
			System.out.println(java.time.Instant.now().toString() + ": " + e.getMessage());
		} finally {
		}
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(highScorePersPath));) {
			dos.writeInt(highScore);
//			dos.flush();
		} catch (IOException e) {
			System.out.println(java.time.Instant.now().toString() + ": " + e.getMessage());
		}
	}

}

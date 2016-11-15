package java1;

import java.io.FileInputStream;
import java.io.IOException;

public class WordCount {

	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			FileInputStream fis = new FileInputStream(args[0]);
			int current = 0;
			int charCount = 0, wordCount = 0, lineCount = 0;
			while ((current = fis.read()) != -1) {
				charCount++;
				char c = (char) current;
				if (c == ' ' || c == '.' || c == ',')
					wordCount++;
				if (c == '\n')
					lineCount++;
			}
			fis.close();
			System.out.println(charCount);
			System.out.println(wordCount);
			System.out.println(lineCount);
		} else {
			System.out.println("Bitte Datei angeben");
		}
	}
}

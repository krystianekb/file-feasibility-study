package org.krystianekb.feasibility.files;

import java.io.IOException;

public class MainApp {

	public static void main(String[] args) throws IOException {
		int _1kb = 1_000;
		int _1gb = 1_000_000;
		{
			RandomAlphanumericLineGenerator lineGenerator = new RandomAlphanumericLineGenerator(1 * _1kb);
			FileGenerator fileGenerator = new FileGenerator(lineGenerator);
			fileGenerator.generateFile("target/data/1g-1k-1k-1.txt", 1 * _1gb);
			fileGenerator.generateFile("target/data/1g-1k-1k-2.txt", 1 * _1gb);
			fileGenerator.generateFile("target/data/2g-1k-1k-1.txt", 2 * _1gb);
			fileGenerator.generateFile("target/data/2g-1k-1k-2.txt", 2 * _1gb);
			fileGenerator.generateFile("target/data/5g-1k-1k-1.txt", 5 * _1gb);
			fileGenerator.generateFile("target/data/5g-1k-1k-2.txt", 5 * _1gb);
			fileGenerator.generateFile("target/data/12g-1k-1k-1.txt", 12 * _1gb);
			fileGenerator.generateFile("target/data/12g-1k-1k-2.txt", 12 * _1gb);
		}
		{
			RandomAlphanumericLineGenerator lineGenerator = new RandomAlphanumericLineGenerator(768, 5 * _1kb);
			FileGenerator fileGenerator = new FileGenerator(lineGenerator);
			fileGenerator.generateFile("target/data/1g-1k-5k-1.txt", 1 * _1gb);
			fileGenerator.generateFile("target/data/1g-1k-5k-2.txt", 1 * _1gb);
			fileGenerator.generateFile("target/data/2g-1k-5k-1.txt", 2 * _1gb);
			fileGenerator.generateFile("target/data/2g-1k-5k-2.txt", 2 * _1gb);
			fileGenerator.generateFile("target/data/5g-1k-5k-1.txt", 5 * _1gb);
			fileGenerator.generateFile("target/data/5g-1k-5k-2.txt", 5 * _1gb);
			fileGenerator.generateFile("target/data/12g-1k-5k-1.txt", 12 * _1gb);
			fileGenerator.generateFile("target/data/12g-1k-5k-2.txt", 12 * _1gb);
		}
	}
}

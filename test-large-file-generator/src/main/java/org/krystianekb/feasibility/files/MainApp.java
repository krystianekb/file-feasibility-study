package org.krystianekb.feasibility.files;

import java.io.IOException;

public class MainApp {

    public static void main(String[] args) {
        RandomAlphanumericLineGenerator lineGenerator = new RandomAlphanumericLineGenerator(1024);
        FileGenerator fileGenerator = new FileGenerator(lineGenerator, 5000000);
        try {
            fileGenerator.generateFile("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

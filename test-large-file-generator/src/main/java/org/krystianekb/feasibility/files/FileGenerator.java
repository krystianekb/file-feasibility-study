package org.krystianekb.feasibility.files;

import org.krystianekb.feasibility.files.intf.LineGenerator;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileGenerator {

    private LineGenerator lineGenerator;
    private int lineCount;

    public FileGenerator(LineGenerator lineGenerator, int lineCount) {
        this.lineGenerator = lineGenerator;
        this.lineCount = lineCount;
    }

    public void generateFile(String filename) throws IOException {
        int count = lineCount;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            while (count-- != 0 ) {
                writer.write(lineGenerator.generateLine().toCharArray());
                writer.write(System.lineSeparator().toCharArray());
            }
        }
    }
}

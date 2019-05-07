package org.krystianekb.feasibility.files;

import org.krystianekb.feasibility.files.intf.LineGenerator;

public class RandomAlphanumericLineGenerator implements LineGenerator {

    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnoprqstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private int minLineSize;
    private int maxLineSize;

    public RandomAlphanumericLineGenerator(int lineSize) {
        this(lineSize, lineSize);
    }

    public RandomAlphanumericLineGenerator(int minLineSize, int maxLineSize) {
        this.minLineSize = minLineSize;
        this.maxLineSize = maxLineSize;
    }

    public String generateLine() {
        int count = minLineSize + (int) ((1 + maxLineSize - minLineSize) * Math.random());
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}

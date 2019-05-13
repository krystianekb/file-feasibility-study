package org.krystianekb.sorting.file;

import com.google.code.externalsorting.ExternalSort;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static com.google.code.externalsorting.ExternalSort.mergeSortedFiles;
import static com.google.code.externalsorting.ExternalSort.sortInBatch;

public class MainApp {

    private static final List<String> sizeList = Arrays.asList("1g", "2g", "5g", "12g");
    private static final List<String> lineSize = Arrays.asList("1k", "5k");

    private static final String OUTPUT_DIR = "target";
    private static final String SORTED_DIR = String.format("%s/sorted", OUTPUT_DIR);
    private static final String DATA_DIR = String.format("%s/data", OUTPUT_DIR);
    private static final String TMP_DIR = String.format("%s/tmp", OUTPUT_DIR);
    //public static final String TMP_DIR = "/home/krystian/tmp";

    private static long measure(String size, String lineSize, int iter) throws IOException {
        String filename = String.format("%s-1k-%s-%d.txt", size, lineSize, iter);

        StopWatch overall = new StopWatch();
        overall.start();

        List <File> tmpFiles = sortInBatch(new File(String.format("%s/%s",DATA_DIR, filename)),
                ExternalSort.defaultcomparator, 1024, Charset.defaultCharset(),
                new File(TMP_DIR), false);


        mergeSortedFiles(tmpFiles, new File(String.format("%s/%s", SORTED_DIR, filename)));

        overall.stop();
        return overall.getTime();
    }

    public static void main(String[] args) throws IOException {

        for (String s : sizeList) {
            for (String l : lineSize) {
                for (int i=1; i<=2; i++) {
                    long timeTaken = measure(s, l, i);
                    System.out.println(
                            String.format("Execution time for [%s,%s,%d] : %.2f s",
                                    s, l, i,
                                    timeTaken / 1000.0));
                }

            }
        }



    }

}

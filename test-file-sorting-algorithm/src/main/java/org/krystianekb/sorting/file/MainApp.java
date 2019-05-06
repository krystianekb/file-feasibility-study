package org.krystianekb.sorting.file;

import com.google.code.externalsorting.ExternalSort;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.IOException;

public class MainApp {

    public static void main(String[] args) throws IOException {

        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        ExternalSort.sort(new File("test.txt"),
                new File("sorted_test_java.txt"));

        stopwatch.stop();
        long timeTaken = stopwatch.getTime();
        System.out.println(
                String.format("Execution time : %.2f s", timeTaken / 1000.0));

    }

}

package org.krystianekb.sorting.file;

import com.google.code.externalsorting.ExternalSort;

import java.io.File;
import java.io.IOException;

public class MainApp {

    public static void main(String[] args) throws IOException {

        ExternalSort.sort(new File("test.txt"),
                new File("sorted_test_java.txt"));

    }

}

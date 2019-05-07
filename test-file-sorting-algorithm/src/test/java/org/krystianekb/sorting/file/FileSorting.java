package org.krystianekb.sorting.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.code.externalsorting.ExternalSort;

class FileSorting {
    static StringWriter CSV_BUFFER;
    static PrintWriter CSV_OUT;

    @BeforeAll
    static void prepareCsvBuffer() {
        CSV_BUFFER = new StringWriter();
        CSV_OUT = new PrintWriter(CSV_BUFFER);
        CSV_OUT.println("File\tStart\tMerge Start\tEnd\tSplit Time [s]\tMerge Time [s]\tChunk Size\tMax Mem"
                + "\tLine Number\tMin Length\tMax Length\tIndex");
    }

    @AfterAll
    static void printStatsAsCsv() {
        System.out.println(CSV_BUFFER);
    }

    @ParameterizedTest
    @MethodSource("getFiles")
    void sortUsingSimpleExternalSort(Path inputFile) throws IOException {
        File outputFile = inputFile.getParent().resolve("sorted/" + inputFile.getFileName().toString()).toFile();
        outputFile.getParentFile().mkdirs();

        Instant start = Instant.now();
        System.out.format("%s:\tStart Splitting\t%s%n", inputFile.getFileName(), start);
        List<File> sortedSplitFiles = ExternalSort.sortInBatch(inputFile.toFile());

        Instant startMerge = Instant.now();
        System.out.format("%s:\tStart Merging\t%s\t%s%n", inputFile.getFileName(), startMerge,
                Duration.between(start, startMerge));
        double fileSizeInMb = sortedSplitFiles.get(0).length() / 1000D / 1000D;
        ExternalSort.mergeSortedFiles(sortedSplitFiles, outputFile);

        Instant end = Instant.now();
        double maxMemoryInMb = Runtime.getRuntime().maxMemory() / 1000D / 1000D;
        System.out.format("%s:\tDone  Sorting\t%s\t%s\tTotal: %s\tFile Chunk Size: %sMB\tMax Mem: %sMB%n",
                inputFile.getFileName(), end, Duration.between(startMerge, end), Duration.between(start, end),
                fileSizeInMb, maxMemoryInMb);

        CSV_OUT.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s%n", inputFile.getFileName(), start, startMerge, end,
                Duration.between(start, end).getSeconds(), Duration.between(startMerge, end).getSeconds(), fileSizeInMb,
                maxMemoryInMb, StringUtils.join(inputFile.getFileName().getFileName().toString().split("-"), "\t"));
        outputFile.delete();
    }

    static Stream<Arguments> getFiles() throws IOException {
        return getRawFiles().map(Arguments::of);
    }

    static Stream<Path> getRawFiles() throws IOException {
        return Files.walk(Paths.get("target").resolve("data"), 1).filter(Files::isRegularFile);
    }
}

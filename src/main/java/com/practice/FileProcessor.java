package com.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    private final OutputWriter writer;
    private final StatisticsCollector stats;
    private final boolean fullStats;

    public FileProcessor(String outputDir, String prefix, boolean append, boolean fullStats) {
        this.writer = new OutputWriter(outputDir, prefix, append);
        this.stats = new StatisticsCollector();
        this.fullStats = fullStats;
    }

    public void processFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                DataType type = DataClassifier.classify(line);
                writer.write(type, line);
                stats.update(type, line);
            }
        }
    }

    public void finish() {
        writer.closeAll();
        stats.printStats(fullStats);
    }
}

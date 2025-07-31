package com.practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java Main [-s|-f] [-a] [-o outputPath] [-p prefix] file1 file2 ...");
            return;
        }

        boolean append = false;
        boolean fullStats = false;
        String outputDir = ".";
        String prefix = "";
        List<String> inputFiles = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a":
                    append = true;
                    break;
                case "-s":
                    fullStats = false;
                    break;
                case "-f":
                    fullStats = true;
                    break;
                case "-o":
                    if (++i < args.length) outputDir = args[i];
                    break;
                case "-p":
                    if (++i < args.length) prefix = args[i];
                    break;
                default:
                    inputFiles.add(args[i]);
            }
        }

        FileProcessor processor = new FileProcessor(outputDir, prefix, append, fullStats);

        for (String fileName : inputFiles) {
            try {
                processor.processFile(fileName);
            } catch (IOException e) {
                System.err.println("Ошибка при чтении файла " + fileName + ": " + e.getMessage());
            }
        }

        processor.finish();
    }
}
package com.practice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class OutputWriter {
    private final Map<DataType, BufferedWriter> writers = new HashMap<>();
    private final String outputDir;
    private final String prefix;
    private final boolean append;
    private final Map<DataType, String> fileNames = Map.of(
            DataType.INTEGER, "integers.txt",
            DataType.FLOAT, "floats.txt",
            DataType.STRING, "strings.txt"
    );

    public OutputWriter(String outputDir, String prefix, boolean append) {
        this.outputDir = outputDir;
        this.prefix = prefix;
        this.append = append;
    }

    public void write(DataType type, String line) throws IOException {
        if (!writers.containsKey(type)) {
            String path = Paths.get(outputDir, prefix + fileNames.get(type)).toString();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, append));
            writers.put(type, writer);
        }
        writers.get(type).write(line);
        writers.get(type).newLine();
    }

    public void closeAll() {
        for (BufferedWriter writer : writers.values()) {
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        }
    }
}

package com.practice;

public class DataClassifier {
    public static DataType classify(String line) {
        if (line.matches("^-?\\d+$")) return DataType.INTEGER;
        if (line.matches("^-?\\d*\\.\\d+([eE][-+]?\\d+)?$")) return DataType.FLOAT;
        return DataType.STRING;
    }
}

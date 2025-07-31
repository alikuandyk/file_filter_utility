package com.practice;

public class StatisticsCollector {
    private int intCount = 0;
    private long intSum = 0;
    private long intMin = Long.MAX_VALUE;
    private long intMax = Long.MIN_VALUE;

    private int floatCount = 0;
    private double floatSum = 0;
    private double floatMin = Double.MAX_VALUE;
    private double floatMax = -Double.MAX_VALUE;

    private int stringCount = 0;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = Integer.MIN_VALUE;

    public void update(DataType type, String value) {
        switch (type) {
            case INTEGER:
                try {
                    long val = Long.parseLong(value);
                    intCount++;
                    intSum += val;
                    intMin = Math.min(intMin, val);
                    intMax = Math.max(intMax, val);
                } catch (NumberFormatException ignored) {}
                break;
            case FLOAT:
                try {
                    double val = Double.parseDouble(value);
                    floatCount++;
                    floatSum += val;
                    floatMin = Math.min(floatMin, val);
                    floatMax = Math.max(floatMax, val);
                } catch (NumberFormatException ignored) {}
                break;
            case STRING:
                stringCount++;
                int len = value.length();
                minLength = Math.min(minLength, len);
                maxLength = Math.max(maxLength, len);
                break;
        }
    }

    public void printStats(boolean full) {
        System.out.println("--- Статистика ---");

        if (intCount > 0) {
            System.out.println("Целые числа: количество = " + intCount);
            if (full) {
                System.out.printf("  min = %d, max = %d, sum = %d, avg = %.2f%n",
                        intMin, intMax, intSum, (double) intSum / intCount);
            }
        }
        if (floatCount > 0) {
            System.out.println("Дробные числа: количество = " + floatCount);
            if (full) {
                System.out.printf("  min = %f, max = %f, sum = %f, avg = %.2f%n",
                        floatMin, floatMax, floatSum, floatSum / floatCount);
            }
        }
        if (stringCount > 0) {
            System.out.println("Строки: количество = " + stringCount);
            if (full) {
                System.out.println("  shortest length = " + minLength + ", longest length = " + maxLength);
            }
        }
    }
}

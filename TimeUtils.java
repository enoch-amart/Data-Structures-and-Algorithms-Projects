public class TimeUtils {
    public static String getReport(String algoName, long timeNano) {
        double timeMs = timeNano / 1_000_000.0;
        String complexity = switch (algoName) {
            case "BubbleSort" -> "O(n^2)";
            case "BinarySearch" -> "O(log n)";
            case "SequentialSearch" -> "O(n)";
            case "QuickSort" -> "O(n log n)";
            default -> "Unknown";
        };
        return "Time taken: " + timeMs + " ms\nTheoretical Time Complexity: " + complexity;
    }
}
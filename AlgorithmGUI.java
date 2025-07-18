import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AlgorithmGUI {
    private final InputHandler inputHandler = new InputHandler();
    private final FileInputHandler fileInputHandler = new FileInputHandler();

    public void start() {
        while (true) {
            String[] options = {"1. Searching", "2. Sorting", "0. Cancel"};
            int choice = showOptionDialog("Hello, welcome. Do you want to perform Searching or Sorting?", options);
            switch (choice) {
                case 0 -> handleSearching();
                case 1 -> handleSorting();
                case 2, -1 -> System.exit(0);
                default -> showError("Invalid input. Please select 0, 1, or 2.");
            }
        }
    }

    private void handleSearching() {
        String[] options = {"1. Sequential Search", "2. Binary Search", "0. Back"};
        int choice = showOptionDialog("Welcome to Searching. Choose an algorithm:", options);
        SearchAlgorithm algorithm = switch (choice) {
            case 0 -> new SequentialSearch();
            case 1 -> new BinarySearch();
            case 2, -1 -> null;
            default -> {
                showError("Invalid input.");
                yield null;
            }
        };
        if (algorithm != null) runSearch(algorithm);
    }

    private void handleSorting() {
        String[] options = {
            "1. Bubble Sort", "2. Heap Sort", "3. Insertion Sort",
            "4. Quick Sort", "5. Merge Sort", "6. Selection Sort", "7. Radix Sort", "0. Back"
        };
        int choice = showOptionDialog("Welcome to Sorting. Choose an algorithm:", options);
        SortAlgorithm algorithm = switch (choice) {
            case 0 -> new BubbleSort();
            case 1 -> new HeapSort();
            case 2 -> new InsertionSort();
            case 3 -> new QuickSort();
            case 4 -> new MergeSort();
            case 5 -> new SelectionSort();
            case 6 -> new RadixSort();
            case 7, -1 -> null;
            default -> {
                showError("Invalid input.");
                yield null;
            }
        };
        if (algorithm != null) runSort(algorithm);
    }

    private void runSearch(SearchAlgorithm algorithm) {
        List<Integer> list = getInputData();
        if (list == null || list.isEmpty()) return;
        int target = Integer.parseInt(JOptionPane.showInputDialog("Enter the value to search:"));

        long start = System.nanoTime();
        int result = algorithm.search(list, target);
        long end = System.nanoTime();

        StringBuilder message = new StringBuilder("Search Result: ");
        message.append(result != -1 ? "Found at index " + result : "Not found").append("
");
        message.append(TimeUtils.getReport(algorithm.getClass().getSimpleName(), end - start));
        showMessage(message.toString());
    }

    private void runSort(SortAlgorithm algorithm) {
        List<Integer> list = getInputData();
        if (list == null || list.isEmpty()) return;

        long start = System.nanoTime();
        algorithm.sort(list);
        long end = System.nanoTime();

        StringBuilder message = new StringBuilder("Sorted Output: " + list + "
");
        message.append(TimeUtils.getReport(algorithm.getClass().getSimpleName(), end - start));
        showMessage(message.toString());
    }

    private List<Integer> getInputData() {
        String[] options = {"1. Enter manually", "2. Upload from file", "0. Cancel"};
        int choice = showOptionDialog("Choose input method:", options);
        return switch (choice) {
            case 0 -> inputHandler.getManualInput();
            case 1 -> fileInputHandler.readFromFile();
            default -> null;
        };
    }

    private int showOptionDialog(String message, String[] options) {
        Object selection = JOptionPane.showInputDialog(null, message, "Menu",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selection == null) return -1;
        return Integer.parseInt(selection.toString().substring(0, 1));
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
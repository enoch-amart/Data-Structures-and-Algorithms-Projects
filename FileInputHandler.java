// Java implementation continues from previous classes...

// Import statements required for file handling
import java.io.*;
import java.util.*;
import javax.swing.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

class FileInputHandler {
    public static List<Integer> readFromFile(File file) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        String fileName = file.getName().toLowerCase();

        if (fileName.endsWith(".txt")) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    extractIntegersFromLine(line, numbers);
                }
            }
        } else if (fileName.endsWith(".doc")) {
            try (FileInputStream fis = new FileInputStream(file)) {
                HWPFDocument doc = new HWPFDocument(fis);
                WordExtractor extractor = new WordExtractor(doc);
                for (String paragraph : extractor.getParagraphText()) {
                    extractIntegersFromLine(paragraph, numbers);
                }
            }
        } else if (fileName.endsWith(".docx")) {
            try (FileInputStream fis = new FileInputStream(file)) {
                XWPFDocument docx = new XWPFDocument(fis);
                XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
                extractIntegersFromLine(extractor.getText(), numbers);
            }
        } else {
            throw new IllegalArgumentException("Unsupported file type. Please upload .txt, .doc, or .docx only.");
        }

        return numbers;
    }

    private static void extractIntegersFromLine(String line, List<Integer> numbers) {
        String[] tokens = line.trim().split("[ ,]+");
        for (String token : tokens) {
            try {
                numbers.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                // Skip invalid integers, or log if needed
            }
        }
    }
}

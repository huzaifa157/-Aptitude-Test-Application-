package testapplication;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {
    private static final String FILE_NAME = "exam_results.txt";

    // Initialize file with header if it doesn't exist
    public FileManager() {
        initializeFile();
    }

    private void initializeFile() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            try (FileWriter writer = new FileWriter(FILE_NAME)) {
                // Box-drawing header
                String header =
                        "┌──────────────────┬──────────┬───────┬──────────┬────┬──────┬──────┬──────┬───────────────────┐\n" +
                                "│      Name         │ Roll No  │ Total │ Obtained │ CS │ Phy  │ Maths│ Eng  │     Timestamp     │\n" +
                                "├──────────────────┼──────────┼───────┼──────────┼────┼──────┼──────┼──────┼───────────────────┤\n";
                writer.write(header);
            } catch (IOException e) {
                System.err.println("Error creating file header: " + e.getMessage());
            }
        }
    }

    public void saveResult(String name, String rollNo, int totalMarks, int obtainedMarks, int[] subjectScores) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()); // Removed seconds

            // Box-drawing row format
            String result = String.format(
                    "│ %-16s │ %-8s │ %5d │ %8d │ %2d │ %4d │ %4d │ %4d │ %16s │%n",
                    name, rollNo, totalMarks, obtainedMarks,
                    subjectScores[0], subjectScores[1], subjectScores[2], subjectScores[3],
                    timestamp
            );

            writer.write(result);

            // Add closing line after last entry
            if (new File(FILE_NAME).length() == result.length()) { // If first entry
                writer.write("└──────────────────┴──────────┴───────┴──────────┴────┴──────┴──────┴──────┴───────────────────┘\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving result: " + e.getMessage());
        }
    }
}
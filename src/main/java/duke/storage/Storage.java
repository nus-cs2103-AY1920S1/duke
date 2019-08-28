package duke.storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<String> fileEntries;
    private String filePath;

    public Storage(String filePath) {
        this.fileEntries = processFile(filePath);
    }

    /**
     * Processes and returns a list of strings from the input file.
     *
     * @param filePath File path from user.
     * @return fileInput Processed file entries.
     */
    public ArrayList<String> processFile(String filePath) {
        ArrayList<String> fileInput = new ArrayList<>();
        this.filePath = filePath;

        // Try to create the file if it is not found
        // and throw IOException if file cannot be created
        try {
            File file = new File(filePath);
            if (!file.isFile() && !file.createNewFile()) {
                throw new IOException("Error creating new file " +
                        file.getAbsolutePath());
            }
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()) {
                String nextLine = fileScanner.nextLine();
                fileInput.add(nextLine);
            }

            fileScanner.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return fileInput;
    }

    /**
     * Returns the stored file entries.
     *
     * @return fileEntries Processed file entries from the file.
     */
    public ArrayList<String> load() {
        return fileEntries;
    }

    public void writeBackToFile(String formattedList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            System.out.println(formattedList);
            fileWriter.write(formattedList);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    };
}

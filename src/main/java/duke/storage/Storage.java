package duke.storage;

import duke.todo.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private List<String> fileEntries;
    private String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath File path of the data file.
     */
    public Storage(String filePath) {
        this.fileEntries = parseFromFile(filePath);
    }

    /**
     * Processes and returns a list of strings from the input file.
     *
     * @param filePath File path from user.
     * @return fileInput Processed file entries.
     */
    public List<String> parseFromFile(String filePath) {
        List<String> fileInput = new ArrayList<>();
        this.filePath = filePath;

        // Try to create the file if it is not found
        // and throw IOException if file cannot be created
        try {
            File file = new File(filePath);

            if (!file.isFile() && !file.createNewFile()) {
                throw new IOException("Error creating new file " + file.getAbsolutePath());
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
    public List<String> load() {
        return fileEntries;
    }

    /**
     * Writes back to the data file with the current list formatted.
     *
     * @param tasks Current list of Todos.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(tasks.outputTasksInString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

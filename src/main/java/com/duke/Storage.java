package com.duke;

import com.duke.exception.DukeFileNotFoundException;
import com.duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Encapsulates a file I/O class to deal with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath File path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends text to the back of a file.
     *
     * @param input Text to append.
     * @throws DukeFileNotFoundException If file not found.
     * @throws IOException               If failed to read or write to file.
     */
    public void appendText(String input) throws DukeFileNotFoundException, IOException {
        try {
            writeToFile(input + System.lineSeparator(), true);
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("File " + filePath + " not found by Duke");
        }
    }

    /**
     * Inserts text to specified position in file.
     *
     * @param position The position where the text is inserted.
     * @param text     The text to be inserted.
     * @throws IOException If failed to read or write to file.
     */
    public void insertText(int position, String text) throws IOException {
        try {
            writeToFile(validateTextOutput(position, text), false);
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("File " + filePath + " not found by Duke");
        }
    }

    /**
     * Replaces chosen text in the file with a new String.
     *
     * @param oldString Text to be overwritten.
     * @param newString New text.
     * @throws IOException If failed to read or write to file.
     */
    public void overwriteText(String oldString, String newString) throws IOException {
        try {
            String output = validateTextOutput(oldString, newString, true);
            writeToFile(output, false);
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("File " + filePath + " not found by Duke");
        }
    }

    /**
     * Deletes the chosen text from the file.
     *
     * @param toDelete Text to delete.
     * @throws IOException If failed to read or write to file.
     */
    public void deleteText(String toDelete) throws IOException {
        try {
            String output = validateTextOutput(toDelete, "", false);
            writeToFile(output, false);
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException("File " + filePath + " not found by Duke");
        }
    }

    private void writeToFile(String input, boolean isAppendable) throws IOException {
        FileWriter fw = new FileWriter(filePath, isAppendable);
        fw.write(input);
        fw.close();
    }

    private String validateTextOutput(String oldString, String newString, boolean isOverwrite) throws IOException {
        List<String> inputList = Files.readAllLines(Paths.get(filePath));
        String output = "";
        for (String s : inputList) {
            if (!s.equals(oldString)) {
                output += s + System.lineSeparator();
            } else if (isOverwrite) {
                output += newString + System.lineSeparator();
            }
        }
        return output;
    }

    private String validateTextOutput(int index, String text) throws IOException {
        List<String> inputList = Files.readAllLines(Paths.get(filePath));

        return IntStream.rangeClosed(0, inputList.size())
                .mapToObj(i -> i > index
                        ? inputList.get(i - 1) + System.lineSeparator()
                        : i < index
                        ? inputList.get(i) + System.lineSeparator()
                        : text + System.lineSeparator())
                .reduce("", (x, y) -> x + y);
    }

    /**
     * Generates a TaskList from file input.
     *
     * @return TaskList with tasks remaining in the file.
     */
    TaskList genTaskListFromFile() {
        TaskList taskList = new TaskList();
        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            while (fileScanner.hasNextLine()) {
                String nextLineOfFile = fileScanner.nextLine().trim();
                Task task = Task.genTaskFromFileString(nextLineOfFile);
                taskList.addTask(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    /**
     * Gets file content.
     *
     * @return String representation of file content.
     */
    String getFileContent() {
        File file = new File(filePath);

        try {
            Scanner fileScanner = new Scanner(file);
            StringBuilder output = new StringBuilder("You have these remaining tasks:\n");
            while (fileScanner.hasNextLine()) {
                output.append(fileScanner.nextLine().trim()).append("\n");
            }
            return String.valueOf(output);
        } catch (FileNotFoundException e) {
            File dir = new File(file.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return "Task record " + filePath + " not found by Duke. New task list generated.";
        }
    }
}

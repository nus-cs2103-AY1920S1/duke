package dude.helper;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.scene.layout.VBox;

/**
 * Handles reading from and writing to save file on hard disk.
 */
public class Storage {
    private String filePath;
    private VBox dialogContainer;

    public Storage(String filePath, VBox dialogContainer) {
        this.filePath = filePath;
        this.dialogContainer = dialogContainer;
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Produces ArrayList of Tasks containing items listed in save file.
     *
     * @return ArrayList of Tasks from file.
     * @throws IOException If enclosing file directory or file itself does not exist.
     * @throws NoSuchElementException If there is no description after type and isDone in save file.
     */
    public ArrayList<Task> load() throws IOException, NoSuchElementException {
        ArrayList<Task> tasks = new ArrayList<>();
        File listFile = new File(filePath);
        listFile.createNewFile(); // Directory data needs to exist already

        Scanner fileScanner = new Scanner(listFile);
        while (fileScanner.hasNext()) {
            String type = fileScanner.next();
            int isDone = Integer.parseInt(fileScanner.next());
            String remaining = fileScanner.nextLine().trim();   // throws NoSuchElementException if no description
            String[] remainingSplit = remaining.split(" \\| ");
            switch (type) {
            case "T":
                tasks.add(new Todo(remaining, isDone));
                break;
            case "D":
                tasks.add(new Deadline(remainingSplit[0], isDone, remainingSplit[1]));
                break;
            case "E":
                tasks.add(new Event(remainingSplit[0], isDone, remainingSplit[1]));
                break;
            default:
                throw new AssertionError("Invalid item in external save file");
                // assert false : "Invalid item in external save file";
            }
        }
        return tasks;
    }

    /**
     * Writes to save file.
     *
     * @param filePath Directory path of save file.
     * @param textToAdd String to add to save file.
     * @param isAppend Determines whether String is appended to existing file contents or added to a new file.
     */
    public void writeToFile(String filePath, String textToAdd, boolean isAppend) {
        try {
            FileWriter fw = isAppend ? new FileWriter(filePath, true) : new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException ioe) {
            new Ui(dialogContainer, null)
                    .printError(":( Failed to save changes to file. Please try again.");
        }
    }

    /**
     * Overwrites save file with a new file.
     *
     * @param listOfTasks Tasks used to populate list in new file.
     */
    public void overwriteFile(ArrayList<Task> listOfTasks) {
        String saveToFile = "";
        for (Task t : listOfTasks) {
            saveToFile += String.format("%s %d %s", t.getType(), t.getIsDone(), t.getDescription());
            if ("D".equals(t.getType())) {
                saveToFile += " | " + ((Deadline) t).getEndTime();
            } else if ("E".equals(t.getType())) {
                saveToFile += " | " + ((Event) t).getEventPeriod();
            }
            saveToFile += "\n";
        }
        writeToFile(filePath, saveToFile, false);
    }
}

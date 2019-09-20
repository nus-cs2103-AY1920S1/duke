package main;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of tasks on local text file.
 */
public class Storage {

    String filePath;
    File file;

    /**
     * Creates a storage object, attempts to read text
     * file or create the text file if not found.
     *
     * @param filePath path of .txt file to be read or saved.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;

        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.file = file;
    }

    /**
     * Initialize the txt file by copying the contents of the text file
     * onto a Task array.
     *
     * @return Task array.
     * @throws FileNotFoundException If file is not found.
     * @throws DukeException if task type cannot be determined.
     */
    public ArrayList<Task> fileInitialization() throws FileNotFoundException, DukeException {

        Scanner s = new Scanner(file);
        ArrayList<Task> clone = new ArrayList<Task>();

        while (s.hasNext()) {
            String input = s.nextLine();
            String[] inputArr = input.split(" \\| ");
            boolean done;
            done = inputArr[1].equals("1");

            switch (inputArr[0]) {
            case "T":
                clone.add(new ToDos(inputArr[2], done));
                break;
            case "D":
                clone.add(new Deadlines(inputArr[2], TaskList.dateTimeParser(inputArr[3]), done));
                break;
            case "E":
                clone.add(new Events(inputArr[2], TaskList.dateTimeParser(inputArr[3]), done));
                break;
            default:
                throw new DukeException("Task type cannot be determined from file.");
            }


        }

        return clone;
    }

    /**
     * Replaces text file with new text file.
     *
     * @param textToAdd Content of the new text file.
     * @throws IOException If the file is not found.
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Converts the given array of Task into a string that is written onto
     * the text file.
     *
     * @param arr Array of Task to be translated into the text file.
     * @throws IOException If the file is not found.
     */
    public void arrayToFile(ArrayList<Task> arr) throws IOException {
        String memo = "";

        for (Task i : arr) {
            int done;
            if (i.isDone()) {
                done = 1;
            } else {
                done = 0;
            }

            if (i instanceof ToDos) {
                memo = memo + "T | " + done + " | " + i.getDescription() + "\n";
            } else if (i instanceof Deadlines) {
                memo = memo + "D | " + done + " | " + i.getDescription() + " | "
                        + TaskList.localDateTimeToString(((Deadlines) i).getDate()) + "\n";
            } else {
                memo = memo + "E | " + done + " | " + i.getDescription() + " | "
                        + TaskList.localDateTimeToString(((Events) i).getDate()) + "\n";
            }
        }

        writeToFile(memo);
    }
}

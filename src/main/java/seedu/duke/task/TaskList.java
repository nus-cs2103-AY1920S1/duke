package seedu.duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.exception.DukeException;
import seedu.duke.priority.Priority;

/**
 * Represents an ArrayList that takes in objects of type Task and its subclasses.
 */
public class TaskList extends ArrayList<Task> {

    /** This is the first constructor which is used to initialise an empty TaskList. */
    public TaskList() {
    }

    /** This is the second constructor which is used to initialise a non-empty TaskList.
     * It reads the existing Task objects from a file and adds them to the TaskList.
     * @param f Represents the file to be read.
     * @throws DukeException  If f cannot be found.
     */
    public TaskList(File f) throws DukeException {
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                assert (parts[0].equals("")) == false : "Error in file recording.";
                assert (parts[1].equals('0') || parts[1].equals('1')) == false : "Error in file recording.";
                switch (parts[0]) {
                case "T":
                    add(new Todo(parts[2]));
                    this.get(this.size() - 1).isDone = (parts[1].equals("1"));
                    if (parts.length > 3) {
                        this.get(this.size() - 1).priority = readPriority(parts[3]);
                    }
                    break;
                case "E":
                    assert !parts[2].equals("") : "Error in file recording.";
                    add(new Event(parts[2], parts[3]));
                    this.get(this.size() - 1).isDone = (parts[1].equals("1"));
                    if (parts.length > 4) {
                        this.get(this.size() - 1).priority = readPriority(parts[4]);
                    }
                    break;
                case "D":
                    assert !parts[2].equals("") : "Error in file recording.";
                    add(new Deadline(parts[2], parts[3]));
                    this.get(this.size() - 1).isDone = (parts[1].equals("1"));
                    if (parts.length > 4) {
                        this.get(this.size() - 1).priority = readPriority(parts[4]);
                    }
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("");
        }
    }

    private Priority readPriority(String strPriority) {
        switch (strPriority) {
        case "HIGH":
            return Priority.HIGH;
        case "MEDIUM":
            return Priority.MEDIUM;
        case "LOW":
            return Priority.LOW;
        default:
            return null;
        }
    }
}

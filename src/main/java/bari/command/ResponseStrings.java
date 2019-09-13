package bari.command;

import bari.storage.Storage;
import bari.task.TaskList;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that handles building this program's responses to user input.
 * There is an ArrayList in this class, to which lines can be added.
 * The concatenation of all these lines (by <code>\n</code>) can then be exported out
 * as a single String.
 */
public class ResponseStrings {
    private ArrayList<String> lines;

    public ResponseStrings() {
        this.lines = new ArrayList<>();
    }

    /**
     * Adds an arbitrary String to this instance's ArrayList.
     *
     * @param line The String to add.
     */
    public void add(String line) {
        lines.add(line);
    }

    /**
     * Parses a String representing an integer, checking if it represents a valid index
     * in the given TaskList (between 1 and <code>tl.size()</code>, inclusive).
     *
     * @return The parsed integer.
     * @throws IllegalArgumentException If the given String did not represent an integer,
     *         or if that integer was out of range.
     */
    public int checkInteger(String str, TaskList tl) throws IllegalArgumentException {
        int index;
        int n = tl.size();
        String rangeMessage = String.format("You have %d task%s. ", n, n == 1 ? "" : "s")
                + "The index must be between 1 and that number.";

        try {
            index = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(rangeMessage);
        }
        if (index < 1 || n < index) {
            throw new IllegalArgumentException(rangeMessage);
        }
        return index;
    }

    /**
     * Adds a line informing about the number of tasks in the given TaskList.
     *
     * @param tl The TaskList whose tasks are to be enumerated.
     */
    public void numTasksInList(TaskList tl) {
        int n = tl.size();
        lines.add(String.format("%d task%s in your list.", n, n == 1 ? " is" : "s are"));
    }

    /**
     * Adds lines describing the Tasks in the given TaskList.
     *
     * @param tl The TaskList whose tasks are to be described.
     */
    public void listTasks(TaskList tl) {
        if (tl.size() == 0) {
            lines.add("You have no tasks.");
        } else {
            lines.add("You have these tasks:");
            for (int i = 1; i <= tl.size(); i++) {
                lines.add(i + ". " + tl.get(i));
            }
        }
    }

    /**
     * Adds lines describing the Tasks in the given TaskList matching the given keyword String.
     *
     * @param tl The TaskList to filter.
     * @param keyword The keyword that will be matched against each task.
     */
    public void listFilteredTasks(TaskList tl, String keyword) {
        ArrayList filteredTasks = tl.filter(keyword);
        if (filteredTasks.size() == 0) {
            lines.add("No tasks match.");
        } else {
            lines.add("These tasks match:");
            for (int i = 1; i <= filteredTasks.size(); i++) {
                lines.add(i + ". " + filteredTasks.get(i - 1));
            }
        }
    }

    /**
     * Writes the contents of the given TaskList to disk. If an exception is thrown
     * in the process, add a line saying this.
     *
     * @param tl The TaskList whose contents are to be written to disk.
     * @param storage The Storage object responsible for writing said contents.
     */
    public void writeToStorage(TaskList tl, Storage storage) {
        try {
            storage.write(tl.export());
        } catch (IOException e) {
            lines.add("Error writing tasks to file!");
        }
    }

    /**
     * Returns the string representation of this object.
     * This is defined as the joining by <code>\n</code> of all lines in this object's ArrayList.
     *
     * @return The concatenation of all lines in this object.
     */
    @Override
    public String toString() {
        return String.join("\n", lines);
    }
}

package duke.task;

import duke.DukeException;
import duke.Storage;
import duke.command.Command;

import java.util.List;

public class Deadline extends TimeTask {
    Deadline(String description, String time) {
        super(description, time);
    }

    /**
     * Returns a Command which generates a Deadline given the input line.
     *
     * @param tasks The shared list of tasks.
     * @param storage Storage to save the tasks after adding the deadline.
     * @return A Command which generates deadlines.
     */
    public static Command getCommand(TaskList tasks, Storage storage) {
        return words -> {
            List<String> wordList = List.of(words);
            int separator = wordList.indexOf("/by");
            if (separator == -1) {
                throw new DukeException("A deadline must have a time.");
            } else if (separator == 1) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (separator == words.length - 1) {
                throw new DukeException("The time of a deadline cannot be empty.");
            }
            String description = String.join(" ", wordList.subList(1, separator));
            String time = String.join(" ", wordList.subList(separator + 1, words.length));
            Task task = new Deadline(description, time);
            tasks.add(task);
            storage.store(tasks.getAsLines());
            return List.of("Got it. I've added this task:", "  " + task,
                    "Now you have " + tasks.size() + " tasks in the list.");
        };
    }

    /**
     * Returns the type of this task.
     *
     * @return Type of this task as a string.
     */
    @Override
    String getTaskType() {
        return "D";
    }

    /**
     * Returns this task as a string to display to the user.
     *
     * @return This task as a string.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + getTimeString() + ")";
    }
}

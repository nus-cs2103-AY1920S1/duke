package duke.task;

import duke.DukeException;
import duke.Storage;
import duke.command.Command;

import java.util.Arrays;
import java.util.List;

public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    /**
     * Returns a Command which generates objects of this task given the input line.
     *
     * @param tasks The shared list of tasks.
     * @param storage Storage to save the tasks after adding this task.
     * @return A Command which generates tasks.
     */
    public static Command getCommand(TaskList tasks, Storage storage) {
        return words -> {
            if (words.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            String[] nameArr = Arrays.copyOfRange(words, 1, words.length);
            Task task = new ToDo(String.join(" ", nameArr));
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
        return "T";
    }
}

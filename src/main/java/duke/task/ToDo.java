package duke.task;

import duke.DukeException;
import duke.Storage;
import duke.command.Command;

import java.util.Arrays;

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
                throw new DukeException("To create a todo, enter the command \"todo <description>\".");
            }
            String[] nameArr = Arrays.copyOfRange(words, 1, words.length);
            return addTask(new ToDo(String.join(" ", nameArr)), tasks, storage);
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

package duke.command;

import duke.task.TaskList;

import java.util.Arrays;

public class FindCommand extends ListCommand {
    /**
     * Generates a new find command with a header and criteria of whether the task contains the given words.
     *
     * @param tasks List of tasks.
     */
    public FindCommand(TaskList tasks) {
        super(tasks,
            words -> "Here are the matching tasks in your list:",
            words -> task -> task.toString().contains(extractArgument(words)));
    }
}

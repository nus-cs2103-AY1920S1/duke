package duke.commands;

import java.lang.StringBuilder;

/**
 * Implements the list command.
 * @author Lim Yong Shen, Kevin
 */
public class ListCommand extends Command {

    private static final String EMPTY_TASK_LIST_MESSAGE = "You have 0 tasks in the list.\n";
    private static final String FILLED_TASK_LIST_MESSAGE = "Here are the tasks in your list:\n";
    public static final String COMMAND_WORD = "list";

    /**
     * Executes this list command and returns its command result.
     * @return This list command's command result.
     */
    @Override
    public CommandResult execute() {
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            appendEmptyTaskListMessage(message);
        } else {
            appendFilledTaskListMessage(message);
        }
        return new CommandResult(message.toString());
    }


    /**
     * Appends the empty task list message to the specified StringBuilder.
     * @param message The specified StringBuilder.
     */
    private void appendEmptyTaskListMessage(StringBuilder message) {
        message.append(EMPTY_TASK_LIST_MESSAGE);
    }

    /**
     * Appends the filled task list message and the specified task list's
     * items to the specified StringBuilder.
     * @param message The specified StringBuilder.
     *
     */
    private void appendFilledTaskListMessage(StringBuilder message) {
        message.append(FILLED_TASK_LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            message.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
    }

}

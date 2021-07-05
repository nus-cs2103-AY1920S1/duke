package duke.commands;

import duke.data.TaskList;

import java.lang.StringBuilder;

/**
 * Implements the find command.
 * @author Lim Yong Shen, Kevin
 */
public class FindCommand extends Command {

    private String keyword;
    private static final String EMPTY_TASK_LIST_MESSAGE = "No matching tasks found.\n";
    private static final String FILLED_TASK_LIST_MESSAGE = "Here are the matching tasks in your list:\n";
    public static final String COMMAND_WORD = "find";

    /**
     * Constructs a find command with the specified keyword.
     * @param keyword The specified keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this find command and returns its command result.
     * @return This find command's command result.
     */
    @Override
    public CommandResult execute() {
        StringBuilder message = new StringBuilder();
        TaskList subTaskList = tasks.subTaskListContainingKeyWord(keyword);
        if (subTaskList.isEmpty()) {
            appendEmptyTaskListMessage(message);
        } else {
            appendFilledTaskListMessage(message, subTaskList);
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
    private void appendFilledTaskListMessage(StringBuilder message, TaskList tasks) {
        message.append(FILLED_TASK_LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            message.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
    }

}

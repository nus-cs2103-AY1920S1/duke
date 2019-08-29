package duke.commands;

import duke.data.TaskList;

import java.lang.StringBuilder;

/**
 * Implements the find command.
 * @author Lim Yong Shen, Kevin
 */
public class FindCommand extends Command {

    private String keyword;
    public static final String COMMAND_WORD = "find";

    /**
     * Constructs a find command with the specified keyword.
     * @param keyword The specified keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this FindCommand and returns its CommandResult.
     * @return This FindCommand's CommandResult.
     */
    @Override
    public CommandResult execute() {
        StringBuilder successMessage = new StringBuilder();
        TaskList subTaskList = tasks.subTaskListContainingKeyWord(keyword);
        if (subTaskList.isEmpty()) {
            successMessage.append("No matching tasks found.\n");
        } else {
            successMessage.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < subTaskList.size(); i++) {
                successMessage.append((i + 1) + ". " + subTaskList.get(i) + "\n");
            }
        }
        return new CommandResult(successMessage.toString());
    }

}

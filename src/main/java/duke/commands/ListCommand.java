package duke.commands;

import java.lang.StringBuilder;

/**
 * Implements the list command.
 * @author Lim Yong Shen, Kevin
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Executes this ListCommand and returns its CommandResult.
     * @return This ListCommand's CommandResult.
     */
    @Override
    public CommandResult execute() {
        StringBuilder successMessage = new StringBuilder();
        if (tasks.isEmpty()) {
            successMessage.append("You have 0 tasks in the list.\n");
        } else {
            successMessage.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                successMessage.append((i + 1) + ". " + tasks.get(i) + "\n");
            }
        }
        return new CommandResult(successMessage.toString());
    }

}

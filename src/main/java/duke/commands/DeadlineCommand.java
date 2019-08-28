package duke.commands;

import duke.data.tasks.Deadline;

/**
 * Implements the deadline command.
 * @author Lim Yong Shen, Kevin
 */
public class DeadlineCommand extends AddCommand {

    private Deadline deadline;
    public static final String COMMAND_WORD = "deadline";

    /**
     * Constructs a DeadlineCommand with the specified deadline task.
     * @param deadline The specified deadline task.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes this DeadlineCommand and returns its CommandResult.
     * @return This DeadlineCommand's CommandResult.
     */
    @Override
    public CommandResult execute() {
        tasks.add(deadline);
        return new CommandResult(String.format(SUCCESS_MESSAGE, deadline.toString(), tasks.size()));
    }

}

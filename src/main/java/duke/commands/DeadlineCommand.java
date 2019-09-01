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
     * Constructs a deadline command with the specified deadline task.
     * @param deadline The specified deadline task.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes this deadline command and returns its command result.
     * @return This deadline command's command result.
     */
    @Override
    public CommandResult execute() {
        tasks.add(deadline);
        return new CommandResult(String.format(SUCCESS_MESSAGE, deadline.toString(), tasks.size()));
    }

}

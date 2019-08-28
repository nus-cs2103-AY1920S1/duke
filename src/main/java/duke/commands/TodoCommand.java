package duke.commands;

import duke.data.tasks.Todo;

/**
 * Implements the todo command.
 * @author Lim Yong Shen, Kevin
 */
public class TodoCommand extends AddCommand {

    private Todo todo;
    public static final String COMMAND_WORD = "todo";

    /**
     * Constructs a TodoCommand with the specified todo task.
     * @param todo The specified todo task.
     */
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    /**
     * Executes this TodoCommand and returns its CommandResult.
     * @return This TodoCommand's CommandResult.
     */
    @Override
    public CommandResult execute() {
        tasks.add(todo);
        return new CommandResult(String.format(SUCCESS_MESSAGE, todo.toString(), tasks.size()));
    }

}

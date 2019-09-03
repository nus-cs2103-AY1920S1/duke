/**
 * Encapsulates a command from user input String "todo".
 */
public class CommandTodo extends Command {

    public CommandTodo(String command) {
        super(command);
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        if (this.command.isBlank()) {
            throw new MissingDescriptionException(
                    "☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task todoTask = new Todo(command.trim());
        sh.add(todoTask);
        stor.save(sh.getList());
    }

    @Override
    public String toString() {
        return "Todo: " + command;
    }
}

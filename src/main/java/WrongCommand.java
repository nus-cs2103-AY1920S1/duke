/**
 * Wrong command inherits from Command, refers to a command that is not recognised.
 */
public class WrongCommand extends Command {

    /**
     * Constructs a new WrongCommand object.
     *
     * @param input user input.
     */
    public WrongCommand(String input) {
        super(input);
    }

    /**
     * Prints a message to inform user that the command is wrong and not recognised by the program.
     *
     * @param tasks   current list of tasks.
     * @param ui      Ui object.
     * @param storage Storage object to save and load files.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        throw new DukeException ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }


}


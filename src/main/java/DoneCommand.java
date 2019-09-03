/**
 * Handles completion of tasks upon user input eg. "done 2"
 */
public class DoneCommand extends Command {
    /**
     * Integer indicating task to be deleted
     */
    private int listPointer;

    /**
     * Constructor that takes in keywords and indentation for execution of completion function
     * @param command First keyword entered by user determining command type
     * @param commandDetails Following integer indicating list index of task to be marked complete
     * @param INDENT Constant indentation from start of line (formatting)
     */
    public DoneCommand(String command, String commandDetails, String INDENT) {
        super(command, commandDetails, INDENT);
    }

    /**
     * Handles completion of task in the list
     * @param tasks Contains task list and operations to mark as done on the list
     * @param ui Handles user interaction
     * @param storage Updates new task list to file
     * @throws DukeException Custom exception
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            listPointer = Integer.parseInt(commandDetails);
            tasks.markTaskAsDone(listPointer - 1);
            ui.printResponse("Nice! I've marked this task as done: \n"
                    + INDENT + "   " + tasks.getList().get(listPointer - 1));
            storage.updateTodoFile(tasks.getListString());
        } catch (Exception err) {
            throw new DukeException(err.getMessage());
        }

    }
}

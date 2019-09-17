/**
 * Update command that inherits from Command, updates the TaskList.
 */
public class UpdateCommand extends Command {
    public Actions action;

    /**
     * Constructs a new UpdateCommand object.
     *
     * @param input  user input.
     * @param action type of action to be performed.
     */
    public UpdateCommand(String input, Actions action) {
        super(input);
        this.action = action;
    }

    /**
     * Executes the update command to either mark task as done or print all the tasks.
     *
     * @param tasks   current list of tasks.
     * @param ui      Ui object.
     * @param storage Storage object to save and load files.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        int num;
        String[] inputArr = input.split(" ");
        switch (action) {
        case DONE:
                //no input number
                if (inputArr.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please input a valid number.");
                }
                num = Integer.parseInt(inputArr[1]);
                //invalid num, will index out of bounds
                if (num > tasks.getSize()) {
                    throw new DukeException("☹ OOPS!!! Please input a valid number.");
                } else {
                    Task task = tasks.getTask(num - 1);
                    task.markAsDone();
                    ui.setResponse("    ____________________________________________________________\n"
                            +
                            "     Nice! I've marked this task as done:\n"
                            +
                            "     " + task + "\n"
                            +
                            "    ____________________________________________________________\n");
                    storage.save(tasks);
                }
            break;
        case LIST:
            ui.setResponse(tasks.listTasks());
            break;
        default:
            break;
        }
    }


}

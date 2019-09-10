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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int num;
        String[] inputArr = input.split(" ");
        switch (action) {
        case DONE:
            try {
                //no input number
                if (inputArr.length == 1) {
                    throw new NumberFormatException();
                }
                num = Integer.parseInt(inputArr[1]);
                //invalid num, will index out of bounds
                if (num > tasks.getSize()) {
                    throw new NumberFormatException();
                } else {
                    tasks.getTask(num - 1).markAsDone();
                }
            } catch (NumberFormatException e) {
                Duke.print("☹ OOPS!!! Please input a valid number.");
                return ("☹ OOPS!!! Please input a valid number.");

            }
            break;
        case LIST:
            return tasks.listTasks();
        default:
            break;
        }
        return null;
    }


}

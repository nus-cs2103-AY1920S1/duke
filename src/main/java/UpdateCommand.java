public class UpdateCommand extends Command {
    public Actions action;

    public UpdateCommand(String input, Actions action) {
        super(input);
        this.action = action;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int num;
        switch (action) {
            case DONE:
                try {
                    //no input number
                    if (ui.getInputArr().length == 1) {
                        throw new NumberFormatException();
                    }
                    num = Integer.parseInt(ui.getInputArr()[1]);
                    //invalid num, will index out of bounds
                    if (num > tasks.getSize()) {
                        throw new NumberFormatException();
                    } else {
                        tasks.getTask(num - 1).markAsDone();
                    }
                } catch (NumberFormatException e) {
                    Duke.print("☹ OOPS!!! Please input a valid number.");
                }
                break;
            case LIST:
                tasks.listTasks();
                break;
            default:
                break;
        }
    }


}

public class AddCommand extends Command {
    public Actions action;

    public AddCommand(String input, Actions action) {
        super(input);
        this.action = action;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int num;
        String desc;
        Task task = null;
        switch (action) {
            case TODO:
                //trim so that cannot pass with just spaces
                desc = ui.getTodoDesc();
                if (desc.equals("")) {
                    Duke.print("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    task = new Todo(desc);
                }
                break;
            case DEADLINE:
                num = ui.getInput().indexOf("/by");
                //length == 1 means only has 'deadline', and temp[1] equal /by means no desc as well
                if (ui.getInputArr().length == 1 || ui.getInputArr()[1].equals("/by")) {
                    Duke.print("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                //-1 means /by is not found
                else if (num == -1) {
                    Duke.print("☹ OOPS!!! Please type /by before inputting the deadline.");

                } else {
                    desc = ui.getInput().substring(9, num);
                    //trim so that cannot pass with just spaces
                    String by = ui.getInput().substring(num + 3).trim();
                    //no input time after /by
                    if (by.equals("")) {
                        Duke.print("☹ OOPS!!! Please input a deadline after /by");
                    } else {
                        task = new Deadline(desc, by);
                    }
                }
                break;
            case EVENT:
                num = ui.getInput().indexOf("/at");
                //length == 1 means only has 'event', and temp[1] equal /at means no desc as well
                if (ui.getInputArr().length == 1 || ui.getInputArr()[1].equals("/at")) {
                    Duke.print("☹ OOPS!!! The description of a event cannot be empty.");
                }
                //-1 means /at is not found
                else if (num == -1) {
                    Duke.print("☹ OOPS!!! Please type /at before inputting the time.");
                } else {
                    desc = ui.getInput().substring(6, num);
                    //trim so that cannot pass with just spaces
                    String at = ui.getInput().substring(num + 3).trim();
                    //no input time after /at
                    if (at.equals("")) {
                        Duke.print("☹ OOPS!!! Please input a time after /at");
                    } else {
                        task = new Event(desc, at);
                    }
                }
                break;
            default:
                break;
        }
        if (task == null) {
            // if task is still null do nothing
        } else {
            tasks.addTask(task);
            Duke.print("Got it. I've added this task:\n" +
                    "       " + task + "\n" +
                    "     Now you have " + tasks.getSize() + " tasks in the list.");
        }
    }


}

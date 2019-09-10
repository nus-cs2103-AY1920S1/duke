import java.util.ArrayList;

class UpdateCommand extends Command {
    // update [number] desc/time
    public UpdateCommand(String command, String remainingCommand) {
        super(command, remainingCommand);
    }

    public void changeDesc(int n, ArrayList<Task> list, String desc) {
        Task task = list.get(n);
        task.changeDesc(remainingCommand);
    }

    public void changeTime(int n, ArrayList<Task> list, String desc) throws DukeException {
        Task task = list.get(n);
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            deadline.changeTime(remainingCommand);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            event.changeTime(remainingCommand);
        } else {
            throw new DukeException("The task is not a deadline!");
        }
    }

    public void execute(TaskList tasks, Ui ui) throws DukeException {
        String[] parts = remainingCommand.split(" ");
        int number = Integer.valueOf(parts[0]);
        String toChange = parts[1];

        ArrayList<Task> list = tasks.getList();
        String desc = remainingCommand.substring(2+toChange.length());

        switch(toChange){
        case "desc":
            changeDesc(number, list, desc);
            break;

        case "time":
            // ensure the type is deadline
            changeTime(number, list, desc);
        }
    }
}
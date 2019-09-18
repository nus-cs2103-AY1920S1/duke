import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class UpdateCommand extends Command {
    private static SimpleDateFormat formatterIn = new SimpleDateFormat("d/M/yyyy HHmm");
    private static SimpleDateFormat formatterOut = new SimpleDateFormat("d MMM yyyy ha");

    // update [number] desc/time
    public UpdateCommand(String command, String remainingCommand) {
        super(command, remainingCommand);
    }

    public Task changeDesc(int n, ArrayList<Task> list, String desc) {
        Task task = list.get(n-1);
        task.changeDesc(desc);
        return task;
    }

    public Task changeTime(int n, ArrayList<Task> list, String desc) throws DukeException, ParseException {
        Date date = formatterIn.parse(desc);
        desc = formatterOut.format(date);
        Task task = list.get(n-1);
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            deadline.changeTime(desc);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            event.changeTime(desc);
        } else {
            throw new DukeException("The task is not a deadline!");
        }
        return task;
    }

    public void execute(TaskList tasks, Ui ui) throws DukeException, ParseException {
        String[] parts = remainingCommand.split(" ");
        int number = Integer.valueOf(parts[0]);
        String toChange = parts[1];

        ArrayList<Task> list = tasks.getList();
        String desc = remainingCommand.substring(2+toChange.length()).trim();
        Task task;

        switch(toChange){
        case "desc":
            task = changeDesc(number, list, desc);
            ui.print("Noted. I've changed the description of this task:\n  " + task.toString());
            break;

        case "time":
            // ensure the type is deadline
            task = changeTime(number, list, desc);
            ui.print("Noted. I've changed the time of this task:\n  " + task.toString());
            break;

        default:
            ui.print("Please include type of change!");
        }
    }
}
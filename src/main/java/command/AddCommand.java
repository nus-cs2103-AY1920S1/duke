package command;
import task.*;
import java.util.ArrayList;
import main.*;

public class AddCommand implements Command {
    ArrayList<String> arguments;
    String taskType;

    public AddCommand(String taskType, ArrayList<String> arguments) {
        this.arguments = arguments;
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InsufficientTaskArgumentException, InvalidTaskException {
        Task task = null;
        if (taskType.equals("todo")) {
            if (arguments.size() < 1) {
                throw new InsufficientTaskArgumentException("Sorry! ToDo requires at least one argument: Description");
            }
            task = new ToDo(arguments.get(0));
        } else if (taskType.equals("deadline")) {
            if (arguments.size() < 2) {
                throw new InsufficientTaskArgumentException("Sorry! Deadline requires at least two arguments: Description & Date");
            }
            task = new Deadline(arguments.get(0), arguments.get(1));
        } else if (taskType.equals("event")) {
            if (arguments.size() < 3) {
                throw new InsufficientTaskArgumentException("Sorry! Event requires at least three arguments: Description & Date & Time");
            }
            task = new Event(arguments.get(0), arguments.get(1), arguments.get(2));
        } else {
            return ;
        }
        tasks.addTask(task);
        String result = "    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       "+ task.toString() + "\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________";
        ui.nextLine(result);
        storage.updateTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

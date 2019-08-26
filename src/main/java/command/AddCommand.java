package command;
import task.*;
import java.util.ArrayList;
import main.*;

public class AddCommand implements Command {
    ArrayList<String> arguments;
    String taskType;

    /**
     * Constructor to construct a AddCommand.
     * @param taskType String taskType to be added into the app (Example: ToDo, Event, Deadline).
     * @param arguments ArrayList<String> contains the arguments for a particular taskType.
     */
    public AddCommand(String taskType, ArrayList<String> arguments) {
        this.arguments = arguments;
        this.taskType = taskType;
    }

    /**
     * execute performs the command in the Duke app.
     * @param tasks TaskList that contains the list of tasks that is tracked.
     * @param ui Ui of the app.
     * @param storage Storage is the class that manages file reading and file writing of the data passed into the app.
     * @throws InsufficientTaskArgumentException exception thrown when command does not have enough arguments.
     * @throws InvalidTaskException exception thrown when task is invalid.
     */
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

    /**
     * isExit checks if the command is an exit command.
     * @return boolean whether if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * toString() returns the command and its arguments.
     * @return String.
     */
    @Override
    public String toString() {
        String args = "";
        for (int i = 0; i < arguments.size(); i++) {
            args = args + arguments.get(i) + " ";
        }
        args = args.trim();
        return "Command: " + taskType + ", Arguments: " + args;
    }
}

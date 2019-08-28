import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class AddCommand extends Command {
    private String fullCommand;

    /**
     * @param fullCommand String which is the full line that the user inputs
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * returns whether the command is an ExitCommand
     * @return whether the command is an ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds and determines the type of given task to the saved task-list and prints
     * a confirmation if the addition of the new task is successful
     * @param tasks TaskList containing the user's saved tasks
     * @param ui Ui object to handle the user input
     * @param storage storage object to determine where the executed results are stored
     * @throws DukeException if user input is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (fullCommand.substring(0, 4).equals("todo")) {
            if (fullCommand.length() == 4) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.addTask(new ToDo(fullCommand.substring(5)));
        } else if (fullCommand.substring(0, 5).equals("event")) {
            if (fullCommand.length() == 5) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            int i = fullCommand.indexOf('/');
            String dateAndTime = fullCommand.substring(i + 4);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
            tasks.addTask(new Event(fullCommand.substring(6, i - 1), dateTime));
        } else if (fullCommand.substring(0, 8).equals("deadline")) {
            if (fullCommand.length() == 8) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            int i = fullCommand.indexOf('/');
            String dateAndTime = fullCommand.substring(i + 4);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
            tasks.addTask(new Deadline(fullCommand.substring(9, i - 1), dateTime));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        try {
            storage.addToFile(tasks.getTask(tasks.getNumOfTasks() - 1).toSaveString());
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.getTask(tasks.getNumOfTasks() - 1));
            System.out.println("Now you have " + tasks.getNumOfTasks() +
                    (tasks.getNumOfTasks() == 1 ? " task" : " tasks") + " in the list.");
        } catch (IOException ex) {
            System.out.println("Cannot save new task in file");
        }
    }
}

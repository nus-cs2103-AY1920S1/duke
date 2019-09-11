import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String fullCommand;
    private String command;
    private String description;
    private String dateTime;

    /**
     * Instantiates AddCommand object.
     * @param fullCommand String which is the full line that the user inputs
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Returns whether the command is an ExitCommand.
     * @return whether the command is an ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds and determines the type of given task to the saved task-list and prints
     * a confirmation if the addition of the new task is successful.
     * @param tasks TaskList containing the user's saved tasks
     * @param ui Ui object to handle the user input
     * @param storage storage object to determine where the executed results are stored
     * @throws Exception if user input is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        setFields(fullCommand);
        if (command.equals("todo")) {
            tasks.addTask(new ToDo(description));
        } else {
            try {
                if (command.equals("event")) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime dateAndTime = LocalDateTime.parse(dateTime, format);
                    tasks.addTask(new Event(description, dateAndTime));
                } else { //deadline
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime dateAndTime = LocalDateTime.parse(dateTime, format);
                    tasks.addTask(new Deadline(description, dateAndTime));
                }
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter date and time in format: dd/MM/yyyy HHmm");
            }
        }

        try {
            storage.addToFile(tasks.getTask(tasks.getNumOfTasks() - 1).toSaveString());
            return "Got it. I've added this task:\n"
                    + "  " + tasks.getTask(tasks.getNumOfTasks() - 1) + "\n"
                    + "Now you have " + tasks.getNumOfTasks()
                    + (tasks.getNumOfTasks() == 1 ? " task" : " tasks") + " in the list.\n";
        } catch (IOException ex) {
            return "Cannot save new task in file\n";
        }
    }

    //Used to set the command, description and date and time for the add command
    private void setFields(String line) {
        String[] fields = line.split(" ", 2);
        this.command = fields[0];
        if (fields[0].equals("deadline")) {
            String[] descAndDateTime = fields[1].split(" /by ", 2);
            this.description = descAndDateTime[0];
            this.dateTime = descAndDateTime[1];
        } else if (fields[0].equals("event")) {
            String[] descAndDateTime = fields[1].split(" /at ", 2);
            this.description = descAndDateTime[0];
            this.dateTime = descAndDateTime[1];
        } else {
            this.description = fields[1];
        }
    }
}

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class EditCommand extends Command {
    private String fullCommand;
    private int editIndex;
    private String field;
    private String replacement;

    public EditCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Edits the task in the list of tasks, and saved the task to the saved file.
     * @param tasks TaskList containing the user's saved tasks
     * @param ui Ui object to handle the user input
     * @param storage storage object to determine where the executed results are stored
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        setFields(fullCommand);
        Task task = tasks.getTask(editIndex - 1);
        String reply = "The task: " + task.toString() + " has been changed to ";

        if (field.equals("description")) {
            task.editDescription(replacement);
        } else if (field.equals("datetime")) {
            if (task instanceof Event) {
                Event event = (Event) task;
                event.editDateTime(replacement);
                tasks.setTask(editIndex - 1, event);
            } else if (task instanceof Deadline){
                Deadline deadline = (Deadline) task;
                deadline.editDateTime(replacement);
                tasks.setTask(editIndex - 1, deadline);
            } else {
                throw new DukeException("You can't edit the date time of a todo task");
            }
        }

        try {
            storage.editTaskInFile(editIndex, tasks.getTask(editIndex - 1).toSaveString());
            reply += tasks.getTask(editIndex - 1).toString() + "\n";
            return reply;
        } catch (IOException ex) {
            return "Cannot save new task in file\n";
        }
    }

    //sets the fields, the edit index, field (description or time and date) and new text
    private void setFields(String line) {
        String[] fields = line.split(" ", 3);
        this.editIndex = Integer.parseInt(fields[0]);
        this.field = fields[1];
        this.replacement = fields[2];
    }
}

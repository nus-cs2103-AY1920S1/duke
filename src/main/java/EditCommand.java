import java.io.IOException;

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
     * @return message response to user
     */
    @Override
    public String executeAndReturnMessage(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        setFields(fullCommand);
        Task task;

        try {
            task = tasks.getTask(editIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task number specified does not exist!");
        }

        String reply = "The task: " + task.toString() + " has been changed to ";
        editTask(task, tasks);

        try {
            storage.editTaskInFile(editIndex, tasks.getTask(editIndex - 1).toSaveString());
            reply += tasks.getTask(editIndex - 1).toString() + "\n";
            return reply;
        } catch (IOException ex) {
            return "Cannot save new task in file\n";
        }
    }

    //sets the fields, the edit index, field (description or time and date) and new text
    private void setFields(String line) throws DukeException {
        String[] fields = line.split(" ", 3);
        try {
            this.editIndex = Integer.parseInt(fields[0]);
            this.field = fields[1];
            this.replacement = fields[2];
        } catch (Exception ex) {
            throw new DukeException("Please use the following command format:\n"
                    + "edit [task number] ['description'/'datetime'] [new description/date and time]");
        }
    }

    //helper function to execute the editing of the task in the task-list
    private void editTask(Task task, TaskList tasks) throws DukeException {
        if (field.equals("description")) {
            task.editDescription(replacement);
        } else if (field.equals("datetime")) {
            if (task instanceof Event) {
                Event event = (Event) task;
                event.editDateTime(replacement);
                tasks.setTask(editIndex - 1, event);
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                deadline.editDateTime(replacement);
                tasks.setTask(editIndex - 1, deadline);
            } else {
                throw new DukeException("You can't edit the date time of a todo task");
            }
        } else {
            throw new DukeException("Please use the following command format:\n"
                    + "edit [task number] ['description'/'datetime'] [new description/date and time]");
        }
    }
}

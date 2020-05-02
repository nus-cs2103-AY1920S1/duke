package seedu.duke.model;

import seedu.duke.core.Storage;
import seedu.duke.core.Ui;
import seedu.duke.exception.DukeException;
import seedu.duke.model.dto.Task;

import java.io.IOException;
import java.util.List;

public class CmdDone extends Cmd {

    /**
     * Subclass of Cmd, which is a type of action command.
     * @param description task description for creating task object.
     * @param taskList task list (Array List).
     * @param ui handles displaying the task in the fx GUI.
     * @param storage handles saving the task in the text file.
     * @throws DukeException catches error if list index is not entered.
     * @throws IOException catches error if there is any corruption in loading the text file (duke.txt).
     */
    public CmdDone(String description, List<Task> taskList,
                   Ui ui, Storage storage) throws DukeException, IOException {
        super(description);

        if (description.equals("") || description == null) {
            throw new DukeException("Oops! list index not entered for 'done'");
        } else {
            boolean isValidIndex = validateIndex(taskList, description);
            if (isValidIndex) {
                int index = Integer.valueOf(description) - 1;
                Task task = taskList.get(index);
                if (checkIfNotMarked(task)) {
                    taskList.get(index).markAsDone();

                    storage.saveTask(taskList);
                    String message = "Nice! I've marked this task as done:\n"
                            + ui.displayTask(this.getMsg(), taskList, index);
                    this.setMsg(message);
                } else {
                    this.setMsg("Already marked as done!");
                }
            }
        }
    }

    private boolean checkIfNotMarked(Task task) {
        if (task.getIsDone()) {
            return false;
        }
        return true;
    }

    private boolean validateIndex(List<Task> taskList, String description) {
        boolean isValidIndex = false;
        try {
            int index = Integer.valueOf(description) - 1;
            if (index >= 0 && index < taskList.size()) {
                isValidIndex = true;
            } else {
                throw new DukeException("Index out of bound.. please enter a valid index!");
            }
        } catch (NumberFormatException | DukeException e) {
            this.setMsg(e.toString());
        }
        return isValidIndex;
    }
}

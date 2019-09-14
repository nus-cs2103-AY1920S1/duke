package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

public class NoteCommand extends Command {
    private int taskNum;
    private String notes;

    public NoteCommand(int taskNum, String notes) {
        this.taskNum = taskNum;
        this.notes = notes;
    }

    /**
     * This method is used to delete one item from the task list
     * after user request.
     *
     * @return duke response after adding notes
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.getTask(taskNum);

            t.addNotes(notes);
            assert tasks.getList() != null;
            storage.updateList(tasks.getList());
            return ("Noted. I've added notes for this task: " + t);
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task number :(";
        } catch (Exception e) {
            return "Invalid input";
        }

    }
}

package duke.command;

import duke.UndoableStub;

import duke.exception.DukeIllegalArgumentException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.module.UndoStack;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the "find" command supported by Duke.
 */
public class FindCommand extends Command {

    /** Sequence of characters to match against the descriptions of <code>Task</code>s. */
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds a <code>Task</code> in the <code>TaskList</code> with a description that contains {@link #keyword}.
     *
     * @param taskList List of tasks to manage.
     * @param undoStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     * @throws DukeIllegalArgumentException When the keyword is missing.
     */
    @Override
    public void execute(TaskList taskList, UndoStack undoStack, Ui ui, Storage storage)
            throws DukeIllegalArgumentException {
        if (keyword.isEmpty()) {
            throw new DukeIllegalArgumentException(AutoResponse.ERROR_MISSING_KEYWORD);
        }
        List<Task> foundTasks = taskList.findAllTasksContaining(this.keyword);
        // Add UndoableStub to the undoStack
        undoStack.addUndoable(new UndoableStub("find"));
        // Display the result to the user
        ui.printToUser(this.listTasks(foundTasks));
    }

    /**
     * List all <code>Task</code>s in the given taskList.
     * Implemented to aid in displaying all tasks found containing the {@link #keyword}.
     *
     * @param taskList List of <code>Task</code>s to display to user.
     * @return A String array of lines to display to user.
     */
    private String[] listTasks(List<Task> taskList) {
        if (taskList.size() == 0) {
            return new String[] { AutoResponse.DUKE_NO_FOUND_TASKS, String.format("  \"%s\"", this.keyword) };
        }

        List<String> tasks = new ArrayList<>();
        tasks.add(AutoResponse.DUKE_FOUND_TASKS);
        for (int i = 0; i < taskList.size(); i++) {
            tasks.add(String.format("  %d.%s",
                    i + 1,
                    taskList.get(i).getStatus()));
        }
        return tasks.toArray(new String[0]);
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}

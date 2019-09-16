package com.commands;

import com.TaskList;
import com.tasks.Task;
import com.util.Storage;
import com.util.StaticStrings;

import com.exceptions.DukeException;
import com.exceptions.command.*;

public class DoneCommand extends Command {

    protected int doneIdx;
    public DoneCommand(int doneTaskIdx) {
        super("done");
        this.doneIdx = doneTaskIdx;
    }

    /**
     * Marks a task done, saves changes in text file.
     * @param taskList
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        // Checks if index of task to mark done is within range
        int numTasks = taskList.getNumTasks();
        if (numTasks == 0) {
            throw new DukeCommandException(StaticStrings.NO_TASKS_DONE);
        }
        if (doneIdx < 1 || doneIdx > numTasks) {
            throw new DukeExecuteException(StaticStrings.NOT_IN_RANGE + numTasks);
        }
        try {
            assert doneIdx < numTasks : "index of done task should be equals to or less than number of tasks";
            Task doneTask = taskList.markTaskDone(doneIdx);
            storage.save(taskList.getTaskArr());
            ui.showMarkTaskDoneResponse(doneTask);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public void print() {
        super.print();
        System.out.println("Index of task to mark done: " + doneIdx);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        // Not even the same class
        if (!(o instanceof DoneCommand)) {
            return false;
        }
        DoneCommand c = (DoneCommand) o;
        return c.doneIdx == doneIdx;
    }
}
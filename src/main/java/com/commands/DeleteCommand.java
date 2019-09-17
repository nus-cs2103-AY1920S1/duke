package com.commands;

import com.TaskList;
import com.util.Storage;

import com.tasks.Task;
import com.util.StaticStrings;

import com.exceptions.DukeException;
import com.exceptions.command.*;
import com.util.stats.DukeStatistics;

public class DeleteCommand extends Command {

    protected int deleteIdx;
    public DeleteCommand(int deleteTaskIdx) {
        super("delete");
        this.deleteIdx = deleteTaskIdx;
    }

    /**
     * Deletes task from list, saves changes in text file.
     * @param taskList
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        // Checks if index of task to delete is within range
        int numTasks = taskList.getNumTasks();
        if (numTasks == 0) {
            throw new DukeExecuteException(StaticStrings.NO_TASKS_DELETE);
        }
        if (deleteIdx < 1 || deleteIdx > numTasks) {
            throw new DukeExecuteException(StaticStrings.NOT_IN_RANGE + numTasks);
        }

        Task deletedTask = taskList.deleteTask(deleteIdx);
        storage.save(taskList.getTaskArr());
        new DukeStatistics().addLog(this, deletedTask);
        ui.showDeleteTaskResponse(deletedTask, taskList.getTaskArr());
    }

    public int getDeleteIdx() {
        return deleteIdx;
    }

    public void print() {
        super.print();
        System.out.println("Index of task to mark done: " + deleteIdx);
    }

    @Override
    public boolean equals(Object o) {
        // Not even same class
        if (!(o instanceof Command)) {
            return false;
        }
        // Same object
        if (o == this) {
            return true;
        }
        DeleteCommand c = (DeleteCommand) o;
        boolean sameDeleteIdx = deleteIdx == c.getDeleteIdx();
        return super.equals(o) && !sameDeleteIdx;
    }
}
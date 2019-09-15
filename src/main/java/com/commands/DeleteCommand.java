package com.commands;

import java.io.IOException;

public class DeleteCommand extends Command {

    protected int deleteIdx;
    public DeleteCommand(int deleteTaskIdx) {
        super("delete");
        this.deleteIdx = deleteTaskIdx;
    }

    // TODO make better javadocs comments
    /**
     * Deletes task from list, saves changes in text file.
     * @param duke
     * @throws DukeException
     * @throws IOException
     */
    public void execute(Duke duke) throws DukeException, IOException {
        TaskList taskList = duke.getTaskList();
        Storage storage = duke.getStorage();
        Ui ui = duke.getUi();
        // Checks if index of task to delete is within range
        int numTasks = taskList.getNumTasks();
        if (numTasks == 0) {
            throw new DukeException("You have no tasks to delete!");
        }
        if (deleteIdx < 1 || deleteIdx > numTasks) {
            throw new DukeException("Please provide a positive integer that is " +
                    numTasks + " and below.");
        }

        Task deletedTask = taskList.deleteTask(deleteIdx);
        storage.save(taskList.getTaskArr());
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
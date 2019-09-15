package com;

import com.exceptions.DukeException;
import com.tasks.*;
import com.util.StaticStrings;

import java.io.IOException;

import java.util.ArrayList;

/**
 * Mainly contains the ArrayList of tasks
 * Supports operations to modify this AL
 */
public class TaskList {

    private ArrayList<Task> taskArr;

    public TaskList() {
        taskArr = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> listOfTask) {
        taskArr = listOfTask;
    }

    /////////////////
    // OPERATIONS //
    ///////////////

    /**
     * Adds new task to list.
     * Number of tasks in list increases by one.
     * @param newTask
     * @return
     */
    public Task addTask(Task newTask) {
        taskArr.add(newTask);
        return newTask;
    }

    /**
     * Marks task at given list index as done
     * @param doneIdx
     * @return Task that was marked done
     */
    public Task markTaskDone(int doneIdx) throws DukeException {
        Task doneTask = taskArr.get(doneIdx-1);
        if (doneTask.isDone()) {
            throw new DukeException(StaticStrings.TASK_ALREADY_DONE);
        }
        doneTask.markDone();
        return doneTask;
    }

    /**
     * Removes task at given list index.
     * Number of tasks in list decreases by one.
     * @param deleteIdx
     * @return
     */
    public Task deleteTask(int deleteIdx) {
        Task deletedTask = taskArr.get(deleteIdx-1);
        taskArr.remove(deletedTask);
        return deletedTask;
    }

    ////////////////////
    // HELPER METHODS //
    ///////////////////

    public ArrayList<Task> getTaskArr() {
        return taskArr;
    }
    public int getNumTasks() {
        return taskArr.size();
    }

}

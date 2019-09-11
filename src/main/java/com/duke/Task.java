package com.duke;

import com.duke.exceptions.DukeIllegalArgumentException;
import com.duke.exceptions.RedundantOperationException;

/**
 * Encapsulates tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description task description.
     * @throws DukeIllegalArgumentException if encountered invalid user input.
     */
    public Task(String description) throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException("Please specify your task description after your command");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[" + "Y" + "]" : "[" + "N" + "]"); //return Y or N symbols
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        if (this.isDone) {
            throw new RedundantOperationException("The task has already been done");
        }
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        if (!this.isDone) {
            throw new RedundantOperationException("The task has already been done");
        }
        this.isDone = false;
    }

    /**
     * Converts String in file to Task.
     *
     * @param s String representation of tasks in file.
     * @return Task specified by the String.
     */
    public static Task genTaskFromFileString(String s) {
        String status = s.charAt(4) + "";
        System.out.println("status: " + status);
        Task task = genDefaultTaskFromFileString(s);
        task.validateStatus(status);
        return task;
    }

    private void validateStatus(String status) {
        if (status.equals("Y")) {
            this.markAsDone();
        }
    }

    private static Task genDefaultTaskFromFileString(String s) {
        char taskType = s.charAt(1);
        int index = s.indexOf("] ");
        String taskContent = s.substring(index + 1).trim();

        switch (taskType) {
        case 'T':
            return new ToDo(taskContent);
        case 'D':
            String[] deadlineContentArray = taskContent.split("by:");
            return new Deadline(deadlineContentArray[0].trim(), deadlineContentArray[1].trim());
        case 'E':
            String[] eventContentArray = taskContent.split("at:");
            return new Event(eventContentArray[0].trim(), eventContentArray[1].trim());
        default:
            throw new DukeIllegalArgumentException("Illegal input from file");
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}

package com.duke.task;

import com.duke.exception.DukeIllegalArgumentException;
import com.duke.exception.RedundantOperationException;

/**
 * Encapsulates tasks.
 */
public class Task {
    protected String description;
    private boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description Task description.
     * @throws DukeIllegalArgumentException If encountered invalid user input.
     */
    public Task(String description) throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException("Please specify your task description after your command");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon.
     *
     * @return "Y" if done. "N" if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[" + "Y" + "]" : "[" + "N" + "]"); //return Y or N symbols
    }

    private String getDescription() {
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
     * @return Task Specified by the String.
     */
    public static Task genTaskFromFileString(String s) {
        String status = s.charAt(4) + "";
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
            return Deadline.genDeadlineFromFile(deadlineContentArray[0].trim(), deadlineContentArray[1].trim());
        case 'E':
            String[] eventContentArray = taskContent.split("at:");
            return Event.genEventFromFile(eventContentArray[0].trim(), eventContentArray[1].trim());
        default:
            throw new DukeIllegalArgumentException("Invalid input from file");
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}

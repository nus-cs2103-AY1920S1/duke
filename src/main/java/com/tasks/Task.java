package com.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;

/**
 * Represents a task (something that needs to be done)
 * Parent class of different types of Task
 */
public class Task {
    protected String description;
    protected String subDescription; // For /by, /at
    protected boolean isDone;

    public Task(String description, String subDescription) {
        this.description = description;
        this.subDescription = subDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone? "v" : "x");
    }

    public String getDescription() {
        return description;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void markDone() {
        isDone = true;
    }
    public boolean isDone() { return isDone; }

    //Display "[status-icon] task-description"
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +
                this.description;
    }

    // Mainly for use in saving data to hard disk
    public String getTaskType() { return "?"; }

    // Returns true if description or subdescription contains keyword!
    public boolean containsKeyword(String keyword) {
        String lcKeyword = keyword.toLowerCase();
        String lcDescription = description.toLowerCase();
        return lcDescription.contains(lcKeyword);
    }
}
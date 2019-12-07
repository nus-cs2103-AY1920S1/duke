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

    /**
     * Returns whether description for /by is of
     * recognised date-time format
     * Format: 2/12/2019 1800
     * Which can then be converted to format: 2 December 2019 6:00 pm
     * @param str by description
     * @return Whether description for /by can be converted
     */
    protected boolean isValidDateTimeFormat(String str) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyy hhmm");
        try {
            inputFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    protected String getNewDateTimeFormat(String subDescription) throws ParseException {
        SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
        // Note: SimpleDateFormat will also recognise dd/MM/yy (where yy A.D.)
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date = inputFormat.parse(subDescription);
        // Output: 2nd of December 2019, 6pm
        String newSubDescription = displayFormat.format(date);
        return newSubDescription;
    }
}
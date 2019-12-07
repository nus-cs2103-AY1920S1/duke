package com.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    /**
     * Constructor for Deadline tasks
     * @param description description of task
     * @param by when to finish the task by
     */
    public Deadline(String description, String by) {
        super(description, by);
        // If deadline /by is of correct format
        try {
            if (isValidDateTimeFormat(by)) {
                this.subDescription = getNewDateTimeFormat(by);
            }
        } catch (ParseException e) {
            this.subDescription = by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + subDescription + ")";
    }

    public String getTaskType() {
        return "D";
    }

    public boolean containsKeyword(String keyword) {
        String lcKeyword = keyword.toLowerCase();
        String lcDescription = description.toLowerCase();
        String lcSubDescription = subDescription.toLowerCase();
        return lcDescription.contains(lcKeyword) || lcSubDescription.contains(lcKeyword);
    }

}

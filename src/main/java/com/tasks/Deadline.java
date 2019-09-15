package com.tasks;

import com.util.Ui;

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
                SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                Date date = inputFormat.parse(by);
                // Output: 2nd of December 2019, 6pm
                String newBy = displayFormat.format(date);
                this.subDescription = newBy;
            }
        } catch (ParseException e) {
            new Ui().showMessage("Deadline Task \"by\" input was not of recognised format to convert. Input is left as is.",
                    "(Example: Recognised format \"2/12/2019 1800\"; converts to \"2nd of December 2019, 6pm\")");
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
        return description.contains(keyword) || subDescription.contains(keyword);
    }

    /**
     * Returns whether description for /by is of
     * recognised date-time format
     * Format: 2/12/2019 1800
     * Which can then be converted to format: 2 December 2019 6:00 pm
     * @param str by description
     * @return Whether description for /by can be converted
     */
    private boolean isValidDateTimeFormat(String str) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyy hhmm");
        try {
            inputFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}

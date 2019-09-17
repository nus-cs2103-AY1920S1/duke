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
                SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
                // Note: SimpleDateFormat will also recognise dd/MM/yy (where yy A.D.)
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                Date date = inputFormat.parse(by);
                // Output: 2nd of December 2019, 6pm
                String newBy = displayFormat.format(date);
                this.subDescription = newBy;
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

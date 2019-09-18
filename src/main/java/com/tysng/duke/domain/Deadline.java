package com.tysng.duke.domain;

import java.util.Date;

/**
 * This class contains a Date by which the Deadline is due.
 */
public class Deadline extends Task {
    private Date by;

    public Deadline(String taskName, Date by) {
        super(taskName);
        this.by = by;
    }

    public Date getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

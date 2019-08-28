package com.tysng.duke.domain;

import java.util.Date;

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


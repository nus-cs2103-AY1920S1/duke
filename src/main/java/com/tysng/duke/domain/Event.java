package com.tysng.duke.domain;

import java.util.Date;

/**
 * This class contains a Data at which the event is happening.
 */
public class Event extends Task {
    private Date at;

    public Event(String taskName, Date at) {
        super(taskName);
        this.at = at;
    }

    public Date getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}


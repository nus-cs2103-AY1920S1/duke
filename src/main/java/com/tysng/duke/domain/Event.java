package com.tysng.duke.domain;

import java.util.Date;

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


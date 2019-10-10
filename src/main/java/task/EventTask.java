package com.leeyiyuan.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class EventTask extends Task {

    /** At value of the EventTask. */
    protected LocalDateTime at;

    /**
     * Constructs an EventTask.
     */
    public EventTask() {
        super();
    }

    /**
     * Returns the at value of the EventTask.
     *
     * @return At value of the EventTask.
     */
    public LocalDateTime getAt() {
        return this.at;
    }

    /**
     * Sets the at value of the EventTask.
     *
     * @param at At value.
     */
    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (at: %s)",
                this.isDone ? "✓" : "✗",
                this.title,
                this.at.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }
}

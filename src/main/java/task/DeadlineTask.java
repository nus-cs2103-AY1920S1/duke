package com.leeyiyuan.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {

    /** By value of the DeadlineTask. */
    protected LocalDateTime by;

    /**
     * Constructs a DeadlineTask.
     */
    public DeadlineTask() {
        super();
    }

    /** 
     * Returns the by value of the DeadlineTask.
     *
     * @return By value of the DeadlineTask.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Sets the by value of the DeadlineTask.
     *
     * @param by By value.
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                this.isDone ? "✓" : "✗",
                this.title,
                this.by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }
}

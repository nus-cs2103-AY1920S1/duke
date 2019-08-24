package com.leeyiyuan.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    protected LocalDateTime at;

    public EventTask() {
        super();
    }

    public LocalDateTime getAt() {
        return this.at;
    }

    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (at: %s)",
                this.isDone ? "✓" : "✗",
                this.title,
                this.at.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }
}

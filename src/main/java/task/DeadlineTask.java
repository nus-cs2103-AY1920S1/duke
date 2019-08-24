package com.leeyiyuan.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.leeyiyuan.task.Task;

public class DeadlineTask extends Task {
        
    protected LocalDateTime by;

    public DeadlineTask() {
        super();
    }
    
    public LocalDateTime getBy() {
        return this.by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }
    
    @Override
    public String toString() { 
        return String.format("[D][%s] %s (by: %s)",
                this.isDone ? "✓" : "✗",
                this.title,
                this.by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

}

package com.leeyiyuan.task;

import com.leeyiyuan.task.Task;

public class DeadlineTask extends Task {
        
    protected String deadline;

    public DeadlineTask() {
        super();
    }
    
    public String getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    
    @Override
    public String toString() { 
        return String.format("[D][%s] %s (by: %s)",
                this.isDone ? "✓" : "✗",
                this.title,
                this.deadline); 
    }

}

package com.leeyiyuan;

import com.leeyiyuan.Task;

public class EventTask extends Task {

    protected String time;

    public EventTask() {
        super();
    }
    
    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    @Override
    public String toString() { 
        return String.format("[E][%s] %s (at: %s)",
                this.isDone ? "✓" : "✗",
                this.title,
                this.time); 
    }

}

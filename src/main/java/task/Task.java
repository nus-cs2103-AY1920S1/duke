package com.leeyiyuan.task;

/**
 * Represents a task.
 */
public class Task {

    /** Title of the Task. */
    protected String title;

    /** Done status of the Task. */
    protected boolean isDone;

    /** 
     * Constructs a Task.
     */
    public Task() {
    
    }

    /**
     * Returns the title of the Task.
     *
     * @return Title of the Task.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title of the Task.
     *
     * @param title Title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the done status of the Task.
     *
     * @return Done status of the Task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets the done status of the Task.
     *
     * @param isDone Done status.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}

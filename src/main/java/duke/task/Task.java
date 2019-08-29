package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task {
    protected String desc;
    protected boolean isDone;
    static String gap = "  ";

    /**
     * Initialize a Task object.
     *
     * @param desc User inputted description.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Initialize a Task object.
     *
     * @param desc User inputted description.
     * @param done true if task is done, default is false.
     */
    public Task(String desc, boolean done) {
        this.desc = desc;
        this.isDone = done;
    }

    /**
     * Getter method for variable desc.
     *
     * @return Task description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for variable desc.
     *
     * @param desc Task description.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter method for done variable.
     *
     * @return True if task is done, false if task is not done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Setter method for done variable.
     * Set task to done.
     */
    public void setDone() {
        setDone(true);
    }

    /**
     * Setter method for done variable.
     *
     * @param done Done state of task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns done status of task.
     *
     * @return Tick if done, cross if not done.
     */
    public String getDoneStatus() {
        return isDone() ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + this.getDoneStatus() + "] " + getDesc();
    }
}

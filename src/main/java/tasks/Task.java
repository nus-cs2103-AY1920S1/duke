package tasks;

import exceptions.DukeException;

import java.text.ParseException;

public class Task {
    String name;
    boolean isDone;

    /**
     * Create a task object, is called by task, deadline and event.
     *
     * @param name description of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task markAsDone() throws DukeException {
        return null;
    }

    public String printForStorage() {
        return "";
    }

    /**
     * Show the user the task. Called by task, deadline and event classes.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        String str = "";
        if (this.isDone) {
            str += "[✓]";
        } else {
            str += "[✗]";
        } 
        return str += " " + this.name;
    }

}
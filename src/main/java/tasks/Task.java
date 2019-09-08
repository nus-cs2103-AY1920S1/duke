package tasks;

import java.awt.*;
import java.util.ArrayList;

public class Task {
    private String description;
    private boolean isDone;
    String symbol;
    private boolean hasNotes = false;
    ArrayList<String> noteList;

    public Task(String description) {
        noteList = new ArrayList<>();
        assert description != null;
        this.description = description;
        this.isDone = false;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * This method is used return status icon of a task.
     *
     * @return String status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * This method is used return description of a task.
     *
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method is used to return extra information such as
     * the time of a task (if any).
     *
     * @return String extra information
     */
    public String getExtraInfo() {
        if (symbol.equals("T")) {
            return "";
        } else if (symbol.equals("E")) {
            Event t = (Event) this;
            return t.at;
        } else {
            assert symbol.equals("D");
            Deadline t = (Deadline) this;
            return t.by;
        }
    }
    /**
     * This method is used to mark a task as done.
     */
    public void markAsDone() {
        assert !isDone;
        isDone = true;
    }

    public void addNotes(String notes) {
        noteList.add(notes);
        hasNotes = true;
    }


    public void postpone(int daysToPostpone, int hoursToPostpone, int minutesToPostpone) {};

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getNotes() {
        if (!hasNotes) {
            return "";
        }
        String output = "";
        for (String note: noteList) {
            output += (" #" + note);
        }
        return output;
    }
}


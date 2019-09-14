package bot.duke.task;

import java.text.SimpleDateFormat;

import bot.duke.ui.Checkbox;

/**
 * Represents the Task object
 * One Task object per task.
 */
public class Task {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /** Name of the Task. */
    private String taskName;
    /** Done State of the Task. */
    private boolean isDone;

    /**
     * Constructs the Task object.
     *
     * @param taskName Name of the Task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the Task name.
     *
     * @return Task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns whether the Task is done.
     *
     * @return Done state of the Task
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the Done state of the Task.
     *
     * @param isDone Intended Done state
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the Representing Letter to distinguish the Task types.
     *
     * @return Representing Letter
     */
    public char getRepLetter() {
        return ' ';
    }

    /**
     * Returns a bar delimited string for storage-related purposes.
     *
     * @return Bar delimited string
     */
    public String toDelimitedString() {
        return String.format("%c | %c | %s", this.getRepLetter(), this.isDone() ? 'T' : 'F', this.taskName);
    }

    @Override
    public String toString() {
        String checkbox = this.isDone() ? Checkbox.TICK.icon : Checkbox.CROSS.icon;
        return "[" + this.getRepLetter() + "]" + checkbox + " " + this.getTaskName();
    }

}

package duke.task;

import duke.ui.Checkbox;

/**
 * Represents the Duke.Task.Duke.Task object
 * One Duke.Task.Duke.Task object per task.
 */
public class Task {

    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public char getRepLetter() {
        return ' ';
    }

    @Override
    public String toString() {
        String checkbox = this.isDone() ? Checkbox.TICK.icon : Checkbox.CROSS.icon;
        return "[" + this.getRepLetter() + "]" + checkbox + " " + this.getTaskName();
    }

}

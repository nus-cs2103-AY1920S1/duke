package task;

import exception.DukeException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Abstract class which all other Task classes extend. (Event etc...)
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean deleted;


    /**
     *Constructor method of Task.
     * @param description contains information of Task. Includes task name and may include date and time.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.deleted = false;
    }


    /**
     * Called when execute method of findCommand is called.
     * Checks to see if description of task contains keyword.
     * @param keyword word/phrase to find in description.
     * @return boolean value indicating if word/phrase can be found in description.
     */

    public boolean findWord(String keyword) {
        if (description.indexOf(keyword) == -1) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Called in toString method. Indicates status of completion of task.
     * @return tick if task is completed, and cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "+" : "-");
    }

    /**
     * sets isDone value to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Generates uncompleted clone of task.
     * @return
     * @throws DukeException
     */

    public abstract String toString();

    public abstract String parse();

}

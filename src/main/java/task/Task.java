package task;

/**
 * parent class for all tasks.
 */
public class Task {

    protected String command;
    Boolean done;

    /**
     * constructor.
     *
     * @param command is the user input string
     */
    Task(String command) {
        this.command = command;
        this.done = false;
    }

    /**
     * prints a task in the format required for a list.
     *
     * @return the string to print
     */
    public String printer() {
        if (done) {
            return "[✓] " + command;
        } else {
            return "[✗]" + command;
        }
    }

    /**
     * dummy method to be overriden by child class.
     *
     * @return dummy String
     */
    public String printToOutput() {
        return "";
    }


    /**
     * marks the task as done.
     *
     */
    public void taskDone() {
        done = true;
    }

    /**
     * to get the command.
     *
     * @return command
     */
    public String getCommand() {
        return command;
    }
}
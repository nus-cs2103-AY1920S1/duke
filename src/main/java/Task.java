import java.text.ParseException;

public class Task {
    /**
     * attributes for the user input string and to flag whether task is done
     */
    protected String command;
    protected Boolean done;

    /**
     * constructor
     * @param command is teh user input string
     */
    public Task(String command){
        this.command = command;
        this.done = false;
    }

    /**
     * prints a task in the format required for a list
     * @return the string to print
     */
    public String printer(){
        if (done) {
            String result = "[✓] " + command;
            return result;
        } else {
            String result = "[✗]" + command;
            return result;
        }
    }

    /**
     * dummy method to be overriden by child class
     * @return dummy String
     */
    public String printToOutput() {
        return "";
    }


    /**
     * marks the task as done;
     */
    public void taskDone(){
        done = true;
    }

    public String getCommand(){
        return command;
    }
}
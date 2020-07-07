package duke.logic;

import java.lang.reflect.Array;

/**
 * Deals with interpreting user input.
 */
public class Parser {
    private String inputString;
    private String command;
    private String taskDetails;

    public Parser(String inputString) {
        this.inputString = inputString.toLowerCase();
    }

    /**
     * Separates user input into command and task details.
     */
    public void parse() {   //cannot use "|" as a replacement
        String temp = this.inputString.replaceFirst(" ", ":");
        String[] tempArr = temp.split(":");
        command = (String) Array.get(tempArr, 0);
        if (tempArr.length > 1) {  //account for the fact that commands like "list" do not have duke.task details
            taskDetails = ((String) Array.get(tempArr, 1)).trim();
        }
    }

    /**
     * Returns the user command.
     *
     * @return user command.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Returns the task details.
     *
     * @return task details.
     */
    public String getTaskDetails() {
        return this.taskDetails;
    }
}

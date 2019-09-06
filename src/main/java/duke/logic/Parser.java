package duke.logic;

import java.lang.reflect.Array;

public class Parser {
    private String inputString;
    private String command;
    private String taskDetails;

    public Parser(String inputString) {
        this.inputString = inputString;
    }

    public void parse() {   //cannot use "|" as a replacement
        String temp = this.inputString.replaceFirst(" ", ":");
        System.out.println(temp);
        String[] tempArr = temp.split(":");
        command = (String)Array.get(tempArr, 0);
        if(tempArr.length > 1) {  //account for the fact that commands like "list" do not have duke.task details
            taskDetails = ((String) Array.get(tempArr, 1)).trim();
        }
    }
    public String getCommand() {
        return this.command;
    }
    public String getTaskDetails() {
        return this.taskDetails;
    }
}

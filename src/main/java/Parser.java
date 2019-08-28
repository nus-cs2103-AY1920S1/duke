
/**
 * Represents a Parser Class which is used to process the user
 * inputs to allow the rest of the program to use the processed
 * information.
 */

public class Parser {

    public static String getInputCommand(String inputInstruction) {
        String inputCommand = inputInstruction.split(" ")[0];
        return inputCommand;
    }

    public static int getTaskNum(String inputInstruction) {
        String[] taskNumString = inputInstruction.split(" ");
        int taskNum = Integer.parseInt(taskNumString[1]);
        return taskNum;
    }

}


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

    /**
     * Returns the task index from the inputInstructions. Helps to identify the index number
     * @param inputInstruction user string input which includes task index and description
     * @return the task index after separating from the rest of the string
     */
    public static int getTaskNum(String inputInstruction) {
        String[] taskNumString = inputInstruction.split(" ");
        int taskNum = Integer.parseInt(taskNumString[1]);
        return taskNum;
    }

}

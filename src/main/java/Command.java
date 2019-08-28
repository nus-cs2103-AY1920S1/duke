public class Command {
    private String commandType;
    private String instruction;
    private String date;

    /**
     * Represents the command given by the user to the chatbot.
     * @param commandType refers to the command type
     * @param instruction refers to the instructions for the particular commmand type
     * @param date represents the time by which the task should be complete
     */

    public Command(String commandType, String instruction, String date) {
        this.commandType = commandType;
        this.instruction = instruction;
        this.date = date;
    }

    /**
     * Returns the type of the command.
     * @return String commandType
     */

    public String getCommandType() {
        return commandType;
    }

    /**
     * Returns instruction of the task.
     * @return String instruction
     */

    public String getInstruction() {
        return instruction;
    }

    /**
     * Returns the data by which the task must be completed.
     * @return String date
     */

    public String getDate() {
        return date;
    }
}

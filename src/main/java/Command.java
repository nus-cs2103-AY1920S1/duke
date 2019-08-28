public class Command {
    private String commandType;
    private String instruction;
    private String date;

    public Command(String commandType, String instruction, String date) {
        this.commandType = commandType;
        this.instruction = instruction;
        this.date = date;
    }

    public String getCommandType() {
        return commandType;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getDate() {
        return date;
    }
}

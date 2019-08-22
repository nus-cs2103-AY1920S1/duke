/*
Parses command to get information
 */
public class Command {
    private CommandType type;
    private int targetIndex;
    private String addedTask;

    private Command(CommandType type) {
        // for primitive command
        this.type = type;
    }
    private Command(CommandType type, int taskNumber) {
        // for DONE
        this.type = type;
        this.targetIndex = taskNumber - 1;
    }
    private Command(CommandType type, String addedTask) {
        // for adding tasks
        this.type = type;
        this.addedTask = addedTask;
    }

    public static Command NewCommand(String instruction) {
        String[] command = instruction.split(" ");
        switch (command[0]) {
            case "list":
                return new Command(CommandType.LIST);
            case "bye":
                return new Command(CommandType.BYE);
            case "done":
                return new Command(CommandType.DONE, Integer.parseInt(command[1]));
            default:
                return new Command(CommandType.ADD, instruction);
        }
    }

    public int getTargetIndex() {
        return targetIndex;
    }


    public CommandType getType() {
        return type;
    }

    public String getAddedTask() {
        return addedTask;
    }

    enum CommandType {
        BYE, LIST, DONE, ADD
    }
}

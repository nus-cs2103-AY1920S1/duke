import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Parses command to get information
 */
public class Command {
    private CommandType type;
    private int targetIndex;
    private Task addedTask;

    private Command(CommandType type) {
        // for primitive command
        this.type = type;
    }

    private Command(CommandType type, int taskNumber) {
        // for DONE
        this.type = type;
        this.targetIndex = taskNumber - 1;
    }

    private Command(CommandType type, Task addedTask) {
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
            case "todo":
                return new Command(CommandType.ADD, new Todo(
                        String.join(" ", Arrays.copyOfRange(command, 1, command.length))));
            case "deadline":
                List<String> deadlineDetails = Stream.of(String.join(" ", Arrays.copyOfRange(command, 1, command.length))
                        .split("/by")).map(String::trim).collect(Collectors.toList());
                return new Command(CommandType.ADD, new Deadline(deadlineDetails.get(0), deadlineDetails.get(1)));

            case "event":
                List<String> eventDetails = Stream.of(String.join(" ", Arrays.copyOfRange(command, 1, command.length))
                        .split("/at")).map(String::trim).collect(Collectors.toList());;
                return new Command(CommandType.ADD, new Event(eventDetails.get(0), eventDetails.get(1)));

            default:
                // unidentified command
                return new Command(CommandType.ECHO);
        }
    }
    public int getTargetIndex() {
        return targetIndex;
    }

    public CommandType getType() {
        return type;
    }

    public Task getAddedTask() {
        return addedTask;
    }

    enum CommandType {
        BYE, LIST, DONE, ADD, ECHO
    }
}

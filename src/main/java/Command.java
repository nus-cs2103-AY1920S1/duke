import java.util.Arrays;
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

    public static Command NewCommand(String instruction) throws CommandException {
        String[] command = instruction.split(" ");
        switch (command[0]) {
        case "list":
            return new Command(CommandType.LIST);
        case "bye":
            return new Command(CommandType.BYE);
        case "done":
            if (command.length <= 1) {
                throw new CommandException("☹ OOPS!!! You must specify a task number.");
            }
            return new Command(CommandType.DONE, Integer.parseInt(command[1]));
        case "delete":
            if (command.length <= 1) {
                throw new CommandException("☹ OOPS!!! You must specify a task number.");
            }
            return new Command(CommandType.DELETE, Integer.parseInt(command[1]));
        case "todo":
            if (command.length <= 1) {
                throw new CommandException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new Command(CommandType.ADD, new Todo(
                    String.join(" ", Arrays.copyOfRange(command, 1, command.length))));
        case "deadline":
            List<String> deadlineDetails = Stream.of(String.join(" ", Arrays.copyOfRange(command, 1, command.length))
                    .split("/by")).map(String::trim).collect(Collectors.toList());
            if (deadlineDetails.size() != 2) {
                throw new CommandException("☹ OOPS!!! Something is wrong with your input.");
            }
            return new Command(CommandType.ADD, new Deadline(deadlineDetails.get(0), deadlineDetails.get(1)));

        case "event":
            List<String> eventDetails = Stream.of(String.join(" ", Arrays.copyOfRange(command, 1, command.length))
                    .split("/at")).map(String::trim).collect(Collectors.toList());
            ;
            if (eventDetails.size() != 2) {
                throw new CommandException("☹ OOPS!!! Something is wrong with your input.");
            }
            return new Command(CommandType.ADD, new Event(eventDetails.get(0), eventDetails.get(1)));

        default:
            throw new CommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

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

    public enum CommandType {
        BYE, LIST, DONE, ADD, ECHO, DELETE
    }
}

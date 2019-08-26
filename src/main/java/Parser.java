import java.util.Arrays;

public class Parser {

    public static Command parse(String fullCommand) throws IllegalArgumentException{
        String[] splitCommand = fullCommand.split(" ", 0);
        System.out.println(Arrays.toString(splitCommand));
        if(splitCommand[0].equals("todo") || splitCommand[0].equals("deadline")
                || splitCommand[0].equals("event")) {
            return new AddCommand(fullCommand, splitCommand);
        } else if (splitCommand[0].equals("done")) {
            return new DoneCommand(fullCommand, splitCommand);
        } else if (splitCommand[0].equals("list")) {
            return new ListCommand(fullCommand, splitCommand);
        } else if (splitCommand[0].equals("bye")) {
            return new ExitCommand(fullCommand, splitCommand);
        } else if (splitCommand[0].equals("delete")) {
            return new DeleteCommand(fullCommand, splitCommand);
        } else {
            throw new IllegalArgumentException("Invalid Command");
        }
    }
}

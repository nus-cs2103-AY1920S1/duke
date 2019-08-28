public class Parser {

    public static Command parse(String commandText) {
        String[] arr = commandText.split(" ", 2);
        String command = arr[0];

        switch (command) {
        case "list":
            return new PrintCommand();

        case "done":
            return new DoneCommand(commandText);

        case "delete":
            System.out.println(arr[1]);
            return new DeleteCommand(arr[1]);

        case "todo":
            return new AddCommand(arr[0], arr[1]);

        case "deadline":
            return new AddCommand(arr[0], arr[1]);

        case "event":
            return new AddCommand(arr[0], arr[1]);

        case "bye":
            return new ExitCommand();

        default :
            return new InvalidCommand();
        }
    }
}

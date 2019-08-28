/**
 * Parser class to handle user input.
 */
public class Parser {

    /**
     * Parses the input string and determines the type of command given.
     * @param str The input string supplied to Duke.
     * @return Returns the corresponding command depending on the first word of the input string.
     */
    public static Command parse(String str) {
        String[] arr = str.split(" ");
        String next = arr[0];
        Command c;
        switch (next) {
        case "find":
            c = new FindCommand(arr[1]);
            break;
        case "bye":
            c = new ExitCommand();
            break;
        case "list":
            c = new ListCommand();
            break;
        case "done":
            c = new DoneCommand(Integer.parseInt(arr[1]) - 1);
            break;
        case "delete":
            c = new DeleteCommand(Integer.parseInt(arr[1]) - 1);
            break;
        default:
            c = new AddCommand(arr);
            break;
            }
            return c;
    }
}

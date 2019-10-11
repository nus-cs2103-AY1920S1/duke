import java.text.ParseException;

/**
 * Parser class to handle user input.
 */
public class Parser {

    /**
     * Parses the input string and determines the type of command given.
     * @param str The input string supplied to Duke.
     * @return Returns the corresponding command depending on the first word of the input string.
     */
    public static Command parse(String str) throws DukeException, ParseException {
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
        case "update":
            int a = 3;
            String taskName = "";
            while (!arr[a].contains("/to")) {
                taskName += " " + arr[a];
                a++;
            }
            String updatedInfo = arr[a + 1];
            for (int i = a + 2; i < arr.length; i++) {
                updatedInfo += " " + arr[i];
            }
            if (arr[1].contains("description")) {
                c = new UpdateDescriptionCommand(taskName.trim(), updatedInfo);
            } else if (arr[1].contains("date")) {
                c = new UpdateDateCommand(taskName.trim(), updatedInfo);
            } else {
                throw new UnknownTaskException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            break;
        default:
            String description = "";
            Task t;
            if (next.equals("todo") || next.equals("deadline") || next.equals("event")) {
                if (arr.length == 1) {
                    throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + next + " cannot be empty.");
                } else {
                    for (int i = 1; i < arr.length; i++) {
                        description += " " + arr[i];
                    }
                }
            } else {
                throw new UnknownTaskException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (next.equals("todo")) {
                t = new Todos(description.trim());

            } else {
                int index = description.indexOf("/");
                String date = description.substring(index + 4);
                String desc = description.substring(1, index - 1);
                if (next.equals("deadline")) {
                    t = new Deadline(desc, date);
                } else {
                    t = new Event(desc, date);
                }
            }
            c = new AddCommand(t);
        }
        return c;
    }
}

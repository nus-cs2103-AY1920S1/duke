package duke;

import commands.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {

    /**
     * This method is used to parse the user command the task list from disk.
     *
     * @param fullCommand command which is read as a string by the UI.
     * @return commands.Command command which can be executed by application.
     */
    public static Command parse(String fullCommand) {
        String[] input = fullCommand.split(" ", 2);
        if (input[0].equals("bye")) {
            return new ExitCommand();
        } else if (input[0].equals("list")) {
            return new ListCommand();
        } else if (input[0].equals("done")) {
            int num = Integer.parseInt(input[1]);
            return new DoneCommand(num);
        } else if (input[0].equals("delete")) {
            int num = Integer.parseInt(input[1]);
            return new DeleteCommand(num);
        } else if (input[0].equals("find")) {
            return new FindCommand(input[1].trim());
        } else if (input[0].length() < 4) {
            return new ErrorCommand("Me no understand");
        } else {
            Task t;
            switch (input[0]) {
            case "todo":
                t = new Todo(input[1].trim());
                break;
            case "deadline": {
                String[] str = input[1].trim().split("/");
                t = new Deadline(str[0], str[1].substring(3));
                break;
            }
            case "event": {
                String[] str = input[1].trim().split("/", 2);
                t = new Event(str[0], str[1].substring(3));
                break;
            }
            default:
                return new ErrorCommand("Me no understand");

            }
            return new AddCommand(t);
        }
    }
}

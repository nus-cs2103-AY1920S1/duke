/**
 * Represents a class for understanding the input.
 */
public class Parser {

    /**
     * Extracts a command from input.
     * @param input a line of user input
     * @return a Command object
     * @throws DukeException if the input doesn't fit the format of a standard command
     */
    static Command parse(String input) throws DukeException {
        if (input.equals("bye")) { // exit
            return new Bye();
        } else if (input.equals("list")) { // show all tasks
            return new PrintList();
        } else if (input.split(" ")[0].equals("done")) {
            try {
                int num = Integer.parseInt(input.split(" ")[1]);
                return new Done(num);
            } catch (NumberFormatException ex) {
                throw new DukeException("Task number should be integer.");
            }
        } else if (input.split(" ")[0].equals("delete")) { // delete a specific task
            try {
                int num = Integer.parseInt(input.split(" ")[1]);
                return new Delete(num);
            } catch (NumberFormatException ex) {
                throw new DukeException("Task number should be integer.");
            }
        } else { // add new task
            if (input.split(" ").length == 1) {
                String type = input.split(" ")[0];
                switch (type) {
                    case "todo":
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    case "event":
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    case "deadline":
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            String type = input.substring(0, input.indexOf(" "));
            String description = input.substring(input.indexOf(" ") + 1);
            switch (type) {
                case "todo":
                    return new Add("todo", description);
                case "event":
                    return new Add("event", description);
                case "deadline":
                    return new Add("deadline", description);
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
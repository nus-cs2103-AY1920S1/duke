package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Encapsulates a parser.
 */
public class Parser {
    /**
     * Parses user input.
     *
     * @param input  User input received by the chat bot.
     * @return Command described by user input.
     * @throws DukeException  If description of todo is empty or input format is invalid.
     */
    public static Command parse(String input) throws DukeException {
        String lowercaseInput = input.toLowerCase();
        if (lowercaseInput.equals("bye")) {
            return new ByeCommand();
        } else if (lowercaseInput.equals("list")) {
            return new ListCommand();
        } else if (lowercaseInput.startsWith("done")) {
            int doneIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DoneCommand(doneIndex);
        } else if (lowercaseInput.startsWith("delete")) {
            int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DeleteCommand(deleteIndex);
        } else if (lowercaseInput.startsWith("find")) {
            input = input.replaceFirst("(?i)^find", "");
            String keyword = input.substring(input.indexOf(" ") + 1);
            return new FindCommand(keyword);
        } else if (lowercaseInput.startsWith("load")) {
            input = input.replaceFirst("(?i)^load", "");
            String filePath = input.substring(input.indexOf(" ") + 1);
            return new FileCommand(filePath);
        }
        Task task;
        if (lowercaseInput.startsWith("todo")) {
            input = input.replaceFirst("(?i)^todo", "");
            if (input.substring(input.indexOf(" ") + 1).isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
            }
            task = new ToDo(input.substring(input.indexOf(" ") + 1));
        } else if (lowercaseInput.startsWith("deadline")) {
            task = new Deadline(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                    DateTime.parse(input.substring(input.indexOf("/") + 4)));
        } else if (lowercaseInput.startsWith("event")) {
            task = new Event(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                    DateTime.parse(input.substring(input.indexOf("/") + 4)));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
        return new AddCommand(task);
    }
}
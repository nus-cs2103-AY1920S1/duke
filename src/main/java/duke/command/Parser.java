package duke.command;

import duke.DukeException.DukeException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.trim();
        String[] commandWords = fullCommand.split("\\s+");
        String commandType = commandWords[0];
        if (commandType.equals("todo")) {
            String toDoDescription = fullCommand.substring(5).trim();
            return new ToDoCommand(toDoDescription);
        } else if (commandType.equals("event")) {
            String eventDescription = fullCommand.split("/at")[0].substring(6).trim();
            String at = fullCommand.split("/at")[1].trim();
            return new EventCommand(eventDescription, at);
        } else if (commandType.equals("deadline")) {
            String description = fullCommand.split("/by")[0].substring(9).trim();
            String by = fullCommand.split("/by")[1].trim();
            return new DeadlineCommand(description, by);
        } else if (commandType.equals("bye")) {
            return new ByeCommand();
        } else if (commandType.equals("list")) {
            return new ListCommand();
        } else if (commandType.equals("done")) {
            int indexDone = Integer.parseInt(commandWords[1]);
            return new DoneCommand(indexDone);
        } else if (commandType.equals("delete")) {
            int indexDelete = Integer.parseInt(commandWords[1]);
            return new DeleteCommand(indexDelete);
        } else if (commandType.equals("find")) {
            String searchPhrase = fullCommand.substring(5).trim();
            return new FindCommand(searchPhrase);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }
}
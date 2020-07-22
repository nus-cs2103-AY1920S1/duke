package duke;

import duke.command.AddCommand;
import duke.command.ArchiveCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RestoreCommand;

public class Parser {
    /**
     * Parses a rawCommand string into the corresponding Command object.
     *
     * @param rawCommand the raw command string.
     * @return the Command object parsed from the raw command string supplied.
     * @throws DukeException if the raw command string cannot be parsed into any Command objects.
     */
    public static Command parse(String rawCommand) throws DukeException {
        rawCommand = rawCommand.strip();
        String[] words = rawCommand.split(" ");
        switch (words[0]) {
        case "todo":
            if (rawCommand.length() < words[0].length() + 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new AddCommand("todo", rawCommand.substring(words[0].length() + 1));
        case "deadline":
        case "event":
            StringBuilder argumentBuilder = new StringBuilder();
            String option = "";
            StringBuilder optionArgumentBuilder = new StringBuilder();
            boolean isOptionArgument = false;
            for (int i = 1; i < words.length; i++) {
                if (words[i].charAt(0) == '/') {
                    option = words[i].substring(1);
                    isOptionArgument = true;
                } else if (!isOptionArgument) {
                    argumentBuilder.append((argumentBuilder.length() == 0 ? "" : " ") + words[i]);
                } else {
                    optionArgumentBuilder.append((optionArgumentBuilder.length() == 0 ? "" : " ") + words[i]);
                }
            }
            return new AddCommand(words[0], argumentBuilder.toString(), option, optionArgumentBuilder.toString());
        case "list":
            if (rawCommand.length() > words[0].length() + 1) {
                return new ListCommand(rawCommand.substring(words[0].length() + 1));
            } else {
                return new ListCommand();
            }
        case "delete":
            if (rawCommand.length() < words[0].length() + 2) {
                throw new DukeException("Please specify the task to be deleted.");
            }
            return new DeleteCommand(rawCommand.substring(7));
        case "done":
            if (rawCommand.length() < words[0].length() + 2) {
                throw new DukeException("Please specify the task to be marked as done.");
            }
            return new DoneCommand(rawCommand.substring(5));
        case "find":
            if (rawCommand.length() < words[0].length() + 2) {
                throw new DukeException("Please specify a search keyword.");
            }
            return new FindCommand(rawCommand.substring(5));
        case "archive":
            if (rawCommand.length() < words[0].length() + 2) {
                throw new DukeException("Please specify the task to be archived.");
            }
            return new ArchiveCommand(Integer.parseInt(rawCommand.substring(8)));
        case "restore":
            if (rawCommand.length() < words[0].length() + 2) {
                throw new DukeException("Please specify the task to be restored.");
            }
            return new RestoreCommand(Integer.parseInt(rawCommand.substring(8)));
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException("I'm sorry, but I don't know what that means ╥﹏╥");
        }
    }
}

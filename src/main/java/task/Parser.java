package task;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.ListCommand;
import command.FindCommand;

public class Parser {

    /**
     * Parse the inputs from the user.
     * 
     * @param textInput Inputs from the user.
     * @return Formatted string of the input after parsing.
     */
    public static String parse(String textInput) {
        try {
            Command command = parseCommands(textInput);
            return command.execute();
        } catch (DukeException e) {
            return Ui.printException(e);
        }
    }

    private static Command parseCommands(String textInput) throws DukeException {
        if (textInput.equals("list")) {
            Command listCommand = new ListCommand();
            return listCommand;
        } else if (textInput.startsWith("done")) {
            Command doneCommand = new DoneCommand(textInput);
            return doneCommand;
        } else if (textInput.startsWith("delete")) {
            Command deleteCommand = new DeleteCommand(textInput);
            return deleteCommand;
        } else if (textInput.startsWith("find")) {
            Command findCommand = new FindCommand(textInput);
            return findCommand;
        } else if (textInput.equals("bye")) {
            Command exitCommand = new ExitCommand();
            return exitCommand;
        } else {
            return parseTasks(textInput);
        }
    }

    private static Command parseTasks(String textInput) throws DukeException {
        Command addCommand = new AddCommand(textInput);
        return addCommand;
    }
}
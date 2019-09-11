package oop;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.InvalidCommand;
import commands.PrintCommand;

/**
 * The Parser class makes sense of the user command taken in, it
 * decides the next methods executed by the application.
 */
public class Parser {
    /**
     * A static method used for reading in user command and deciding
     * the next method to be executed.
     * @param commandText The command text entered by a user.
     * @return Returns the corresponding type of Command to be executed.
     */
    public static Command parse(String commandText) {
        String[] arr = commandText.split(" ", 2);
        String command = arr[0];
        int size = arr.length;

        switch (command) {
        case "list":
            return new PrintCommand();

        case "done":
            return new DoneCommand(commandText);

        case "delete":
            return new DeleteCommand(commandText);

        case "todo":
            return new AddCommand(commandText);

        case "deadline":
            return new AddCommand(commandText);

        case "event":
            return new AddCommand(commandText);

        case "bye":
            return new ExitCommand();

        case "find":
            return new FindCommand(arr[1]);

        default :
            return new InvalidCommand();
        }
    }
}

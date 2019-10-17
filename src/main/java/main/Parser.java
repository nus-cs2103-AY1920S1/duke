package main;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.FindCommand;
import command.ListCommand;
import command.SortCommand;

import java.io.IOException;

/**
 * Represents the interpretation of user inputs.
 */
public class Parser {

    /**
     * Splits the user input into a String array in order to understand
     * the given command.
     *
     * @param input User input String.
     * @return An command object representing the user command.
     * @throws IOException If file is not found.
     */
    public static Command parse(String input) throws IOException {

        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("sort")) {
            return new SortCommand();
        } else {
            String[] temp = input.split(" ");
            if (temp[0].equals("done")) {
                return new DoneCommand(temp);

            } else if (temp[0].equals("delete")) {
                return new DeleteCommand(temp);

            } else if (temp[0].equals("find")) {
                return new FindCommand(temp);
            } else {
                return new AddCommand(input, temp);
            }
        }

    }
}

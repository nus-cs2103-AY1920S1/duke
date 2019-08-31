package main;

import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ListCommand;
import command.AddCommand;

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
        } else {
            String[] temp = input.split(" ");
            if (temp[0].equals("done")) {
                return new DoneCommand(temp);

            } else if (temp[0].equals("delete")) { //command to delete task
                return new DeleteCommand(temp);

            } else { //command to add task to list
                return new AddCommand(input, temp);
            }
        }

    }
}

package duke;

import duke.command.Command;
import duke.command.Commands;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;

import java.util.Arrays;
import java.util.Map;
import java.util.EnumMap;

/**
 * Abstraction of a parser responsible for resolving user or file input.
 */
class Parser {
    private static int MAP_BUCKET_SIZE = 6;

    static Command parseCommand(String commandString)
            throws DukeInvalidCommandException, DukeInvalidArgumentException {

        String[] inputs = commandString.split("\\s+");
        Commands commandType;
        Command command = null;

        try {
            commandType = Commands.valueOf(inputs[0]);
            inputs = Arrays.copyOfRange(inputs, 1, inputs.length);
        } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
            throw new DukeInvalidCommandException(
                    " \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        switch (commandType) {
            case bye:
                command = new ByeCommand(inputs);
                break;
            case list:
                command = new ListCommand(inputs);
                break;
            case done:
                command = new DoneCommand(inputs);
                break;
            case delete:
                command = new DeleteCommand(inputs);
                break;
            case todo:
                command = new TodoCommand(inputs);
                break;
            case deadline:
                command = new DeadlineCommand(inputs);
                break;
            case event:
                command = new EventCommand(inputs);
                break;
            default:
                //covered in try catch above with enums
        }

        return command;
    }

    static Map<StorageKey, String> parseJsonLine(String line)
            throws DukeTaskFileParseException {
        Map<StorageKey, String> lineMap = new EnumMap<StorageKey, String>(StorageKey.class);

        char[] lineArr = line.toCharArray();

        //first word should be a key
        boolean isKey = true;

        StringBuilder currentKey = new StringBuilder();
        StringBuilder currentValue = new StringBuilder();

        for (char c : lineArr) {
            switch (c) {
            case '{':
                break;
            case '}':
                break;
            case ':':
                isKey = false;
                break;
            case ',':
                isKey = true;
                try {
                    StorageKey key = StorageKey.valueOf(currentKey.toString().trim());
                    lineMap.put(key, currentValue.toString().trim());
                } catch (IllegalArgumentException ex) {
                    throw new DukeTaskFileParseException(
                            "Invalid key found in storage file (line will be skipped)",
                            " \u2639 OOPS!!! I found an invalid storage key in your storage file,\n"
                                    + "   I'll skip that line!");
                }
                break;
            default:
                if (isKey) {
                    currentKey.append(c);
                } else {
                    currentValue.append(c);
                }
            }
        }

        return lineMap;
    }


}

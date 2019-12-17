package com.duke;

import com.duke.command.ByeCommand;
import com.duke.command.Command;
import com.duke.command.DeadlineCommand;
import com.duke.command.DeleteCommand;
import com.duke.command.DoneCommand;
import com.duke.command.EventCommand;
import com.duke.command.FindCommand;
import com.duke.command.ListCommand;
import com.duke.command.ToDoCommand;
import com.duke.command.UndoCommand;
import com.duke.exception.DukeArrayIndexOutOfBoundsException;
import com.duke.exception.DukeIllegalArgumentException;

/**
 * Encapsulates a parser to deal with making sense of the user command.
 */
class Parser {
    private TaskList list;

    /**
     * Constructs a Parser object.
     *
     * @param list Task list.
     */
    Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Parses user's command to instructions for the bot to execute.
     *
     * @param input Command from user input.
     * @return Command The command to be executed.
     */
    Command parseCommand(String input) {
        if (input.isBlank()) {
            throw new DukeIllegalArgumentException("Empty user input is not allowed");
        }
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand(this.list);
        } else if (input.equals("undo")) {
            return new UndoCommand(this.list, Duke.recycleBin);
        }

        String[] inputArray = input.split(" ");
        if (inputArray[0].equals("done") || inputArray[0].equals("delete")) {
            try {
                return getCommand(inputArray[0], inputArray[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeArrayIndexOutOfBoundsException("Please enter an integer after " + "'"
                        + inputArray[0] + "'");
            }
        }

        String description = reformString(inputArray, 1, inputArray.length - 1);
        switch (inputArray[0]) {
        case "todo":
            return new ToDoCommand(this.list, description);
        case "deadline":
            return new DeadlineCommand(this.list, description);
        case "event":
            return new EventCommand(this.list, description);
        case "find":
            return new FindCommand(this.list, description);
        default:
            throw new DukeIllegalArgumentException(getExceptionMessage());
        }
    }

    private String getExceptionMessage() {
        return "Invalid user input.\n"
                + "Permissible commands: todo, deadline, event, list, done, delete, find, undo, bye";
    }

    private Command getCommand(String command, String position) {
        try {
            int index = Integer.parseInt(position) - 1;
            if (command.equals("done")) {
                return new DoneCommand(this.list, index);
            } else if (command.equals("delete")) {
                return new DeleteCommand(this.list, index);
            } else {
                throw new DukeIllegalArgumentException("Please enter either 'done' or 'delete'");
            }
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException("Input is not an integer. "
                    + "Please enter an integer after " + "'" + command + "'");
        } catch (IndexOutOfBoundsException e) {
            if (list.isEmpty()) {
                throw new DukeArrayIndexOutOfBoundsException("The task list empty. You cannot perform " + command
                        + " on it.");
            } else {
                throw new DukeIllegalArgumentException("Please input a number between 1 and "
                        + list.size() + " (inclusive)");
            }
        }
    }

    private static String reformString(String[] arr, int start, int end) {
        String output = "";
        for (int i = start; i <= end; i++) {
            output += arr[i] + " ";
        }
        return output.trim();
    }
}

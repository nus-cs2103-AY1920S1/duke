package duke.text;

import duke.DukeException;
import duke.command.Command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Parser {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);

    public static Command parse(String fullCommand) throws DukeException {
        String[] fullCommandSplit = fullCommand.split(" ", 2);
        String[] argumentsSplit;
        String commandWord = fullCommandSplit[0];
        String desc;
        Command command = new Command(commandWord);
        int taskListIndex;

        switch (commandWord) {
        case "list":
        case "bye":
            if (fullCommandSplit.length == 1) {
                command = new Command(fullCommand);
            } else {
                throw new DukeException("No parameters expected");
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            if (fullCommandSplit.length > 1) {
                switch (commandWord) {
                case "todo":
                    desc = fullCommandSplit[1];
                    command = new Command(commandWord, desc);
                    break;
                case "deadline":
                    argumentsSplit = fullCommandSplit[1].split(" /by ");
                    desc = argumentsSplit[0];
                    if (argumentsSplit.length == 2) {
                        try {
                            LocalDateTime deadline = LocalDateTime.parse(argumentsSplit[1], formatter);
                            command = new Command(commandWord, desc, deadline);
                        } catch (DateTimeParseException e) {
                            throw new DukeException(
                                    "\u2754 OOPS!!! The date inputted is not in 'DD/MM/YYYY HHmm' format");
                        }
                    } else {
                        throw new DukeException("\u2754 OOPS!!! The due date of a deadline cannot be empty");
                    }
                    break;
                case "event":
                    argumentsSplit = fullCommandSplit[1].split(" /at ");
                    desc = argumentsSplit[0];
                    if (argumentsSplit.length == 2) {
                        String[] dateTimeSplit = argumentsSplit[1].split(" - ");
                        if (dateTimeSplit.length == 2) {
                            try {
                                LocalDateTime startDateTime = LocalDateTime.parse(dateTimeSplit[0], formatter);
                                LocalDateTime endDateTime = LocalDateTime.parse(dateTimeSplit[1], formatter);
                                if (!startDateTime.isBefore(endDateTime)) {
                                    throw new DukeException("\u2754 OOPS!!! StartDate > EndDate");
                                }
                                command = new Command(commandWord, desc, startDateTime, endDateTime);
                            } catch (DateTimeParseException e) {
                                throw new DukeException(
                                        "\u2754 OOPS!!! The date inputted is not in 'DD/MM/YYYY HHmm' format");
                            }
                        } else {
                            throw new DukeException("\u2754 OOPS!!! Please input startDate and EndDate");
                        }
                    } else {
                        throw new DukeException("\u2754 OOPS!!! Please input startDate and EndDate");
                    }
                    break;
                }
            } else {
                throw new DukeException("\u2754 OOPS!!! The description of a " + commandWord + " cannot be empty");
            }
            break;
        case "done":
        case "delete":
            if (fullCommandSplit.length > 1) {
                if (fullCommandSplit[1].matches("(0|[1-9]\\d*)")) {
                    taskListIndex = Integer.parseInt(fullCommandSplit[1]);
                    command = new Command(commandWord, taskListIndex);
                } else {
                    throw new DukeException("\u2754 OOPS!!! The index of " + commandWord
                            + " operation must be a positive integer.");
                }
            } else {
                throw new DukeException("\u2754 OOPS!!! The index of " + commandWord
                        + " operation cannot be empty.");
            }
            break;
        default:
            throw new DukeException("\u2754 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}

package com.tysng.duke.service;

import com.tysng.duke.domain.Deadline;
import com.tysng.duke.domain.Event;
import com.tysng.duke.domain.Task;
import com.tysng.duke.domain.Todo;
import com.tysng.duke.exception.CommandException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class parses raw user input text into a Command object that can be process by Duke.
 */
public class Command {
    private CommandType type;
    private int targetIndex;
    private Task addedTask;
    private String keyword;

    private Command(CommandType type) {
        // for primitive command
        this.type = type;
    }

    private Command(CommandType type, int taskNumber) {
        // for DONE
        this.type = type;
        this.targetIndex = taskNumber - 1;
    }

    private Command(CommandType type, String keyword) {
        // for DONE
        this.type = type;
        this.keyword = keyword;
    }

    private Command(CommandType type, Task addedTask) {
        // for adding tasks
        this.type = type;
        this.addedTask = addedTask;
    }

    private static void checkArgsLength(String[] command) throws CommandException {
        switch (command[0]) {
        case "done":
        case "delete":
            if (command.length <= 1) {
                throw new CommandException("☹ OOPS!!! You must specify a task number.");
            }
            break;

        case "find":
            if (command.length <= 1) {
                throw new CommandException("☹ OOPS!!! You must specify a keyword.");
            }
            break;
        case "todo":
            if (command.length <= 1) {
                throw new CommandException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        }
    }


    /**
     * Creates a new command from the user input. This static factory method parses a line of user
     * input into a Command object.
     *
     * @param instruction a line of user input
     * @return a command class with the user input wrapped inside
     * @throws CommandException if the user input cannot be successfully parsed
     */
    public static Command newCommand(String instruction) throws CommandException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM/YYYY HHmm");
        Date date;

        String[] command = instruction.split(" ");

        Command.checkArgsLength(command);

        switch (command[0]) {
        case "list":
            if ((command.length > 1) && (command[1].equals("archive"))) {
                return new Command(CommandType.LIST, "archive");
            }
            return new Command(CommandType.LIST);
        case "bye":
            return new Command(CommandType.BYE);
        case "done":
            return new Command(CommandType.DONE, Integer.parseInt(command[1]));
        case "archive":
            return new Command(CommandType.ARCHIVE, Integer.parseInt(command[1]));
        case "delete":
            return new Command(CommandType.DELETE, Integer.parseInt(command[1]));
        case "find":
            return new Command(CommandType.FIND, command[1]);
        case "todo":
            return new Command(CommandType.ADD, new Todo(
                    String.join(" ", Arrays.copyOfRange(command, 1, command.length))));
        case "deadline":
            List<String> deadlineDetails = Stream.of(String.join(" ", Arrays.copyOfRange(command, 1, command.length))
                    .split("/by")).map(String::trim).collect(Collectors.toList());
            if (deadlineDetails.size() != 2) {
                throw new CommandException("☹ OOPS!!! Something is wrong with your input.");
            }

            try {
                date = dateFormat.parse(deadlineDetails.get(1));
            } catch (ParseException e) {
                throw new CommandException("☹ OOPS!!! Please check the date format.");
            }

            return new Command(CommandType.ADD, new Deadline(deadlineDetails.get(0), date));

        case "event":
            List<String> eventDetails = Stream.of(String.join(" ", Arrays.copyOfRange(command, 1, command.length))
                    .split("/at")).map(String::trim).collect(Collectors.toList());
            if (eventDetails.size() != 2) {
                throw new CommandException("☹ OOPS!!! Something is wrong with your input.");
            }
            try {
                date = dateFormat.parse(eventDetails.get(1));
            } catch (ParseException e) {
                throw new CommandException("☹ OOPS!!! Please check the date format.");
            }

            return new Command(CommandType.ADD, new Event(eventDetails.get(0), date));

        default:
            throw new CommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

        }
    }


    public int getTargetIndex() {
        return targetIndex;
    }

    public CommandType getType() {
        return type;
    }

    public Task getAddedTask() {
        return addedTask;
    }

    public String getKeyword() {
        return keyword;
    }

    /**
     * The only types of Command allowed.
     */
    public enum CommandType {
        BYE, LIST, DONE, ADD, ECHO, DELETE, FIND, ARCHIVE
    }
}

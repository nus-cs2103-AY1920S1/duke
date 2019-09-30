package com.tysng.duke.service;

import com.tysng.duke.domain.Deadline;
import com.tysng.duke.domain.Event;
import com.tysng.duke.domain.Todo;
import com.tysng.duke.dto.ParsedCommand;
import com.tysng.duke.exception.CommandException;
import com.tysng.duke.service.command.*;
import com.tysng.duke.util.Index;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    static void checkArgsLength(String[] command) throws CommandException {
        switch (command[0]) {
        case "done":
        case "archive":
        case "unarchive":
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy HHmm");
        Date date;

        String[] command = instruction.split(" ");

        checkArgsLength(command);

        switch (command[0]) {
        case "list":
            if ((command.length > 1) && (command[1].equals("archive"))) {
                return new ListCommand(ParsedCommand.builder().keyword("archive").build());
            }
            return new ListCommand(ParsedCommand.builder().build());
        case "bye":
            return new ExitCommand(ParsedCommand.builder().build());
        case "done":
            return new DoneCommand(ParsedCommand.builder().targetIndex(new Index(Integer.parseInt(command[1]))).build());
        case "archive":
            return new ArchiveCommand(ParsedCommand.builder().targetIndex(new Index(Integer.parseInt(command[1]))).build());
        case "unarchive":
            return new UnarchiveCommand(ParsedCommand.builder().targetIndex( new Index(Integer.parseInt(command[1]))).build());
        case "delete":
            return new DeleteCommand(ParsedCommand.builder().targetIndex(new Index(Integer.parseInt(command[1]))).build());
        case "find":
            return new FindCommand(ParsedCommand.builder().keyword(command[1]).build());
        case "todo":
            return new AddCommand(ParsedCommand.builder().addedTask(new Todo(
                    String.join(" ", Arrays.copyOfRange(command, 1, command.length)))).build());
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

            return new AddCommand(ParsedCommand.builder().addedTask(new Deadline(deadlineDetails.get(0), date)).build());

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

            return new AddCommand(ParsedCommand.builder().addedTask( new Event(eventDetails.get(0), date)).build());

        default:
            throw new CommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

        }
    }
}
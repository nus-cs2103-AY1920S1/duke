package duke.utils;

import duke.exceptions.DukeException;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ByeCommand;
import duke.commands.AddCommand;
import duke.commands.ListCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;

import java.util.Scanner;
import java.util.ArrayList;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        Scanner sc = new Scanner(fullCommand);
        String command = sc.next();
        switch (command.toLowerCase()) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new AddCommand(parseToDo(sc.nextLine()));
        case "event":
            return new AddCommand(parseEvent(sc.nextLine()));
        case "deadline":
            return new AddCommand(parseDeadline(sc.nextLine()));
        case "done":
            return new DoneCommand(sc.nextInt());
        case "delete":
            return new DeleteCommand(sc.nextInt());
        case "find":
            return new FindCommand(sc.nextLine());
        default:
            throw new DukeException("Unrecognised command: " + command);
        }
    }

    public static ArrayList<String> parseToDo(String remainingParams) throws DukeException {
        String taskDescription = remainingParams.trim();
        if (taskDescription.length() == 0) {
            throw new DukeException("Task Description is empty!");
        } else {
            ArrayList<String> commandParams = new ArrayList<String>();
            commandParams.add("todo");
            commandParams.add(taskDescription);
            return commandParams;
        }
    }

    public static ArrayList<String> parseEvent(String remainingParams) throws DukeException {
        try {
            String[] strArr = remainingParams.split("/at");
            String taskDescription = strArr[0].trim();
            String duration = strArr[1].trim();
            String[] durationArr = duration.split("-");

            if (taskDescription.length() == 0 || durationArr.length == 0) {
                throw new DukeException("Event is of invalid form!");
            }

            ArrayList<String> commandParams = new ArrayList<String>();
            commandParams.add("event");
            commandParams.add(taskDescription);
            commandParams.add(durationArr[0].trim());
            commandParams.add(durationArr[1].trim());

            return commandParams;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Event is of invalid form!");
        }
    }

    public static ArrayList<String> parseDeadline(String remainingParams) throws DukeException {
        try {
            String[] strArr = remainingParams.split("/by");
            String taskDescription = strArr[0].trim();
            String deadline = strArr[1].trim();

            if (taskDescription.length() == 0 || deadline.length() == 0) {
                throw new DukeException("Deadline is of invalid form!");
            }

            ArrayList<String> commandParams = new ArrayList<String>();
            commandParams.add("deadline");
            commandParams.add(taskDescription);
            commandParams.add(deadline);

            return commandParams;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Deadline is of invalid form!");
        }
    }
}

package duke.parser;

import duke.command.*;
import duke.exception.*;

public class Parser {

    String fullCommand;

    private static Command command;

    public Parser(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            command = new ByeCommand();
        } else if (fullCommand.equals("list")) {
            command = new SendTasksCommand();
        } else if (fullCommand.startsWith("done")) {
            // to identify which task completed, split command at space
            String[] splitStr = fullCommand.split(" ");
            // if less than 2 words
            if (splitStr.length < 2) {
                throw new MissingTaskIndexException();
            } else {
                // take 2nd word
                String itemIndex = splitStr[1];
                command = new DoneTaskCommand(itemIndex);
            }
        } else if (fullCommand.startsWith("todo")) {
            // split command at first space
            String[] splitStr = fullCommand.split(" ", 2);
            // if less than 2 words
            if (splitStr.length < 2) {
                throw new MissingTodoException();
            } else {
                // take 2nd word onwards
                String item = splitStr[1];
                command = new AddTodoCommand(item);
            }
        } else if (fullCommand.startsWith("deadline")) {
            // split command at first space
            String[] splitStr = fullCommand.split(" ", 2);
            // if less than 2 words
            if (splitStr.length < 2) {
                throw new MissingDeadlineException();
            } else {
                // take 2nd word onwards
                String item = splitStr[1];
                try {
                    String[] data = item.split("/by", 2);
                    if (data.length == 2 && !data[1].isEmpty() && !data[1].equals(" ")) {
                        String task = data[0];
                        String deadline = data[1];
                        command = new AddDeadlineCommand(task, deadline);
                    } else {
                        throw new MissingDateTimeException();
                    }
                } catch (Exception e) {
                    throw new MissingDateTimeException();
                }
            }
        } else if (fullCommand.startsWith("event")) {
            // split command at first space
            String[] splitStr = fullCommand.split(" ", 2);
            // if less than 2 words
            if (splitStr.length < 2) {
                throw new MissingEventException();
            } else {
                // take 2nd word onwards
                String item = splitStr[1];
                try {
                    String[] data = item.split("/at", 2);
                    if (data.length == 2 && !data[1].isEmpty() && !data[1].equals(" ")) {
                        String task = data[0];
                        String time = data[1];
                        command = new AddEventCommand(task, time);
                    } else {
                        throw new MissingDateTimeException();
                    }
                } catch (Exception e) {
                    throw new MissingDateTimeException();
                }
            }
        } else if (fullCommand.startsWith("delete")) {
            // to identify which task completed, split command at space
            String[] splitStr = fullCommand.split(" ");
            // if less than 2 words
            if (splitStr.length < 2) {
                throw new MissingTaskIndexException();
            } else {
                // take 2nd word
                String itemIndex = splitStr[1];
                command = new DeleteTaskCommand(itemIndex);
            }
        } else {
            throw new InvalidCommandException();
        } return command;
    }

}

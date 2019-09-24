package duke.parser;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.SendTasksCommand;
import duke.command.DoneTaskCommand;
import duke.command.AddTodoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.DeleteTaskCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;
import duke.exception.MissingTaskIndexException;
import duke.exception.MissingTodoException;
import duke.exception.MissingDeadlineException;
import duke.exception.MissingEventException;
import duke.exception.MissingDateTimeException;
import duke.exception.MissingKeywordException;
import duke.exception.InvalidCommandException;

/**
 * Deals with making sense of the user command.
 * Determines which command to be executed by Duke.
 */
public class Parser {

    String fullCommand;

    private static Command command;

    public Parser(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Recognises certain user inputs as a valid Duke command to be executed.
     *
     * @param fullCommand raw user input.
     * @return a suitable command.
     * @throws DukeException if incomplete or invalid user input given.
     */
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
        } else if (fullCommand.startsWith("find")) {
            // to extract keyword, split command at space
            String[] splitStr = fullCommand.split(" ", 2);
            // if less than 2 words
            if (splitStr.length < 2) {
                throw new MissingKeywordException();
            } else {
                // take 2nd word
                String keyword = splitStr[1];
                command = new FindCommand(keyword);
            }
        } else {
            throw new InvalidCommandException();
        }
        return command;
    }

}

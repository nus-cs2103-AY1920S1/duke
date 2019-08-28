package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.IndexCommand;
import duke.command.CompleteCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.exception.DukeException;

public class Parser {

    /**
     * Parses user input and returns an executable <code>Command</code> object.
     * @param command The raw user input
     * @return An executable <code>Command</code> object
     * @throws DukeException when the command is unknown
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new IndexCommand();
        } else if (command.matches("^done\\s+\\d+$")) {
            int taskId = Integer.parseInt(command.split("\\s+")[1]);
            return new CompleteCommand(taskId);
        } else if (command.matches("^(todo|deadline|event).*")) {
            String[] commandArgs = command.split("\\s+", 2);
            String taskType = commandArgs[0];
            String taskParams;
            if (commandArgs.length < 2) {
                taskParams = "";
            } else {
                taskParams = commandArgs[1];
            }
            return new AddCommand(taskType, taskParams);
        } else if (command.matches("^delete\\s+\\d+$")) {
            int taskId = Integer.parseInt(command.split("\\s+")[1]);
            return new DeleteCommand(taskId);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
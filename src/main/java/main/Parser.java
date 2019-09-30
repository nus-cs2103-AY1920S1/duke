package main;

import command.AddCommand;
import command.Command;
import command.AdminCommand;
import command.DeleteCommand;
import command.ExitCommand;
import command.UndoCommand;
import command.HelpCommand;
import command.FindCommand;
import java.util.ArrayList;

public class Parser {
    /**
     * Parser.parse parses the full input command and returns the appropriate command for execution.
     * @param fullCommand String. One full command from the user input. (Example: todo Fix broken laptop).
     * @return Command that will be executed.
     * @throws DukeException if the full command does not match any command type.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandComponents = fullCommand.split(" ");
        Command c = null;
        ArrayList<String> commandArgs = new ArrayList<>();
        if (commandComponents[0].equals("todo")) {
            String description = fullCommand.substring(4);
            description = description.trim();
            if (!description.equals("")) {
                commandArgs.add(description);
            }
            c = new AddCommand(commandComponents[0], commandArgs);
        } else if (commandComponents[0].equals("deadline")) {
            String descriptions = fullCommand.substring(8);
            descriptions = descriptions.trim();
            String[] descComponents = descriptions.split("/by");
            for (int i = 0; i < descComponents.length; i = i + 1) {
                if (!descComponents[i].equals("")) {
                    commandArgs.add(descComponents[i].trim());
                }
            }
            c = new AddCommand(commandComponents[0], commandArgs);
        } else if (commandComponents[0].equals("event")) {
            String descriptions = fullCommand.substring(5);
            descriptions = descriptions.trim();
            String[] descComponents1 = descriptions.split("/at");
            String[] descComponents = descComponents1[1].trim().split(" ");
            commandArgs.add(descComponents1[0].trim());
            for (int i = 0; i < descComponents.length; i = i + 1) {
                if (!descComponents[i].equals("")) {
                    commandArgs.add(descComponents[i].trim());
                }
            }
            c = new AddCommand(commandComponents[0], commandArgs);
        } else if (commandComponents[0].equals("list")) {
            c = new AdminCommand(commandComponents[0]);
        } else if (commandComponents[0].equals("done")) {
            c = new AdminCommand(commandComponents[0], Integer.parseInt(commandComponents[1]));
        } else if (commandComponents[0].equals("delete") && commandComponents.length == 2) {
            c = new DeleteCommand(Integer.parseInt(commandComponents[1]));
        } else if (commandComponents[0].equals("bye")) {
            c = new ExitCommand();
        } else if (commandComponents[0].equals("find")) {
            c = new FindCommand(commandComponents[1]);
        } else if (commandComponents[0].equals("undo")) {
            c = new UndoCommand();
        } else if (commandComponents[0].equals("help")) {
            c = new HelpCommand();
        } else {
            throw new DukeException("    ____________________________________________________________\n"
                    + "    Sorry! I don't know what this command does:\n"
                    + "    "
                    + fullCommand
                    + "\n"
                    + "    ____________________________________________________________");
        }
        return c;
    }
}

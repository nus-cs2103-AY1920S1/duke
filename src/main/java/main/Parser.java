package main;

import command.*;

import java.util.ArrayList;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException{
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
        } else if (commandComponents[0].equals("delete")) {
            c = new DeleteCommand(Integer.parseInt(commandComponents[1]));
        } else if (commandComponents[0].equals("bye")) {
            c = new ExitCommand();
        } else if (commandComponents[0].equals("find")) {
            c = new FindCommand(commandComponents[1]);
        } else {
            throw new DukeException("Sorry! I don't know what this command does: " + fullCommand);
        }
        return c;
    }
}

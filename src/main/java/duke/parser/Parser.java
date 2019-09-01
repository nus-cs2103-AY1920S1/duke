package duke.parser;

import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.CommandNotFoundException;
import duke.command.FindCommand;
import duke.command.DoneCommand;
import duke.command.CommandType;
import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;


/**
 * Represents a parser to parse user input.
 * Has parse functions for user input and file.
 */
public class Parser {
    private static ArrayList<String> commandList;

    /**
     * Initialize the parser,
     * by preparing command list to cross check if command input by user/file
     * is valid.
     *
     * @throws CommandNotFoundException If zone is <= 0.
     */
    public static void initialize() {
        commandList = new ArrayList<>();
        commandList.add("DEADLINE");
        commandList.add("TODO");
        commandList.add("EVENT");
        commandList.add("DELETE");
        commandList.add("DONE");
        commandList.add("LIST");
        commandList.add("BYE");
        commandList.add("FIND");
    }

    /**
     * Checks if user input follows format of: command description time
     * Throws CommandNotFoundException if command is not found.
     * Return user command in command object.
     *
     * @param input String input of command from user.
     * @return Command object of the user.
     * @throws CommandNotFoundException If command entered by user is not found.
     */
    public static Command parse(String input) throws CommandNotFoundException, IncorrectNumberOfArgumentsException {
        Task task;
        Command command;
        boolean isFound = false;
        String commandName = "";
        String commandType = "";

        for (int i = 0; i < commandList.size(); i++) {
            if (input.toUpperCase().startsWith(commandList.get(i))) {
                commandName = commandList.get(i);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new CommandNotFoundException();
        }

        if (commandName.equals("TODO") || commandName.equals("DEADLINE") || commandName.equals("EVENT")) {
            commandType = "ADD";
        } else {
            commandType = commandName;
        }

        CommandType type = CommandType.valueOf(commandType);

        if (type == CommandType.ADD) {
            if (commandName.equals("TODO")) {
                task = new ToDo(commandName.toUpperCase().charAt(0),
                input.substring("Todo".length()).trim(), false);

            } else if (commandName.equals("DEADLINE")) {
                String subsequent = input.substring("Deadline".length());
                int lastIndexSlash = subsequent.lastIndexOf((char) '/');

                String date = subsequent.substring(lastIndexSlash + 1);
                String taskInfo = subsequent.substring(0, lastIndexSlash + "by ".length() + 1).trim();

                task = new Deadline(commandName.toUpperCase().charAt(0), taskInfo, false, date);

            } else {
                String subsequent = input.substring("Event".length());
                int lastIndexSlash = subsequent.lastIndexOf((char) '/');
                String date = subsequent.substring(lastIndexSlash + 1);

                String taskInfo = subsequent.substring(0, lastIndexSlash
                                                          + "at ".length() + 1).trim();
                task = new Event(commandName.toUpperCase().charAt(0), taskInfo, false, date);
            }
            command = new AddCommand(commandType, task);

        } else if (type == CommandType.BYE) {
            task = new Task();
            command = new ByeCommand(commandName, task);

        } else if (type == CommandType.DELETE) {
            String subsequent = input.substring("Delete ".length()).trim();

            int numberToDelete = Integer.parseInt(subsequent);
            task = new Task();
            command = new DeleteCommand(commandName, task, numberToDelete);

        } else if (type == CommandType.DONE) {
            String subsequent = input.substring("Done ".length()).trim();

            int numberDone = Integer.parseInt(subsequent);
            task = new Task();
            command = new DoneCommand(commandName, task, numberDone);

        } else if (type == CommandType.LIST) {
            command = new ListCommand(commandName);

        } else if (type == CommandType.FIND) {
            if (input.toLowerCase().equals("find")) {
                throw new IncorrectNumberOfArgumentsException();
            }

            String keyword = input.substring("Find ".length()).trim();

            if (keyword.equals("")) {
                throw new IncorrectNumberOfArgumentsException();
            }
            command = new FindCommand(commandName, keyword, new Task());
        } else {

            throw new CommandNotFoundException();
        }

        return command;
    }

    /**
     * Checks if file input follows format of: command description time
     * Throws IncorrectFileFormatException if command is not found.
     * Return task in task object.
     *
     * @param input String input of command from user.
     * @return task object of the file.
     * @throws IncorrectFileFormatException If input entered by user is not recognized.
     */
    public static Task parseFromFile(String input) throws IncorrectFileFormatException {
        Task task = new Task();
        String subsequent;
        boolean isDone = false;

        boolean check = input.startsWith("[D]") || input.startsWith("[E]") || input.startsWith("[T]");
        if (!check) {
            throw new IncorrectFileFormatException();

        } else {
            subsequent = input.substring("[X]".length());

            if (subsequent.startsWith("[1]")) {
                isDone = true;

            } else if (subsequent.startsWith("[0]")) {
                isDone = false;

            } else {
                throw new IncorrectFileFormatException();
            }

            // Move to after cross/tick and space
            subsequent = subsequent.substring(4).trim();

            if (input.startsWith("[T]")) {
                task = new ToDo('T', subsequent, isDone);

            } else if (input.startsWith("[D]") || input.startsWith("[E]")) {
                int slashIndex = subsequent.indexOf('/');
                if (slashIndex == -1) {
                    throw new IncorrectFileFormatException();
                }

                String taskDescription = subsequent.substring(0, slashIndex).trim();
                String date = subsequent.substring(slashIndex + "xx: ".length() + 1);

                if (input.startsWith("[D]")) {
                    task = new Deadline('D', taskDescription, isDone, date);

                } else if (input.startsWith("[E]")) {
                    task = new Event('E', taskDescription, isDone, date);

                } else {
                    throw new IncorrectFileFormatException();
                }
            }
        }
        return task;
    }
}

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
import duke.ui.Ui;

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

    private static boolean isCommandValid(String userInput) {
        for (String s : commandList) {
            if (userInput.toUpperCase().startsWith(s)) {
                return true;
            }
        }
        return false;
    }

    private static String classifyCommand(String command){
        if (command.equals("TODO") || command.equals("DEADLINE") || command.equals("EVENT")) {
            return "ADD";
        }
        return command;
    }

    private static String getCommandName(String userInput){
        String command = "";
        for (String s : commandList) {
            if (userInput.toUpperCase().startsWith(s)) {
                command = s;
                break;
            }
        }
        return command;
    }

    private static ArrayList<String> parseTaskInformationAndDate(String userInput, String commandName,
                                                                 String separatorAfterFirstSlash){
        ArrayList<String> taskInformation = new ArrayList<>();
        String subsequentStringAfterTaskDescription = userInput.substring(commandName.length());
        int firstIndexSlash = subsequentStringAfterTaskDescription.indexOf((char) '/');

        // Handle exception later
        String date = subsequentStringAfterTaskDescription.substring(firstIndexSlash + separatorAfterFirstSlash.length() + 1);
        String taskDescription = subsequentStringAfterTaskDescription.substring(0, firstIndexSlash).trim();

        System.out.println(date);
        taskInformation.add(date);
        taskInformation.add(taskDescription);
        return taskInformation;
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
        boolean isCommandFound = isCommandValid(input);
        if(!isCommandFound) {
            throw new CommandNotFoundException();
        }
        String commandName = getCommandName(input);
        String commandType = classifyCommand(commandName);
        CommandType type = CommandType.valueOf(commandType);

        Task task;
        Command command;
        if (type == CommandType.ADD) {
            if (commandName.equals("TODO")) {
                task = new ToDo(commandName.toUpperCase().charAt(0), input.substring("Todo".length()).trim(), false);

            } else if (commandName.equals("DEADLINE")) {
                ArrayList<String> taskInfo = parseTaskInformationAndDate(input, commandName, "by ");
                task = new Deadline(commandName.toUpperCase().charAt(0), taskInfo.get(1), false, taskInfo.get(0));

            } else {
                ArrayList<String> taskInfo = parseTaskInformationAndDate(input, commandName, "at ");
                task = new Event(commandName.toUpperCase().charAt(0),taskInfo.get(1), false, taskInfo.get(0));

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
    public static Task parseFromFile(String input, Ui ui) throws IncorrectFileFormatException {
        Task task = new Task();
        boolean isTaskDone = false;

        boolean isStartWithChar = input.startsWith("[D]") || input.startsWith("[E]") || input.startsWith("[T]");
        if (!isStartWithChar) {
            throw new IncorrectFileFormatException(ui.getLoadingError());
        }

        String subsequentString = input.substring("[X]".length());
        if (subsequentString.startsWith("[1]")) {
            isTaskDone = true;
        } else if (subsequentString.startsWith("[0]")) {
            isTaskDone = false;
        } else {
            throw new IncorrectFileFormatException(ui.getLoadingError());
        }

        // Move to after cross/tick and space
        subsequentString = subsequentString.substring(4).trim();

        if (input.startsWith("[T]")) {
            task = new ToDo('T', subsequentString, isTaskDone);

        } else if (input.startsWith("[D]") || input.startsWith("[E]")) {
            int slashIndex = subsequentString.indexOf('/');
            if (slashIndex == -1) {
                throw new IncorrectFileFormatException(ui.getLoadingError());
            }

            String taskDescription = subsequentString.substring(0, slashIndex).trim();
            String date = subsequentString.substring(slashIndex + "xx: ".length() + 1);

            if (input.startsWith("[D]")) {
                task = new Deadline('D', taskDescription, isTaskDone, date);

            } else if (input.startsWith("[E]")) {
                task = new Event('E', taskDescription, isTaskDone, date);

            } else {
                throw new IncorrectFileFormatException(ui.getLoadingError());
            }
        }
        return task;
    }
}

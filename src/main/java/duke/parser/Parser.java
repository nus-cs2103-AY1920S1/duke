package duke.parser;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

// This class deals with deciphering the user commands

/**
 * Represents a location in a 2D space. A <code>Point</code> object corresponds to
 * a coordinate represented by two integers e.g., <code>3,6</code>
 */
public class Parser {
    private static ArrayList<String> commandList;

    public static void initialize() {
        commandList = new ArrayList<>();
        commandList.add("DEADLINE");
        commandList.add("TODO");
        commandList.add("EVENT");
        commandList.add("DELETE");
        commandList.add("DONE");
        commandList.add("LIST");
        commandList.add("BYE");

    }

    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param input X coordinate of position.
     * @return Lateral location.
     * @throws CommandNotFoundException If zone is <= 0.
     */
    public static Command parse(String input) throws CommandNotFoundException {
        Task task;
        Command command;
        boolean isFound = false;
        String commandName = "", commandType = "";

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
        } else commandType = commandName;

        COMMAND_TYPE type = COMMAND_TYPE.valueOf(commandType);

        if (type == COMMAND_TYPE.ADD) {
            if (commandName.equals("TODO")) {
                task = new ToDo(commandName.toUpperCase().charAt(0), input.substring("Todo".length()).trim(), false);
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

                String taskInfo = subsequent.substring(0, lastIndexSlash + "at ".length() + 1).trim();
                task = new Event(commandName.toUpperCase().charAt(0), taskInfo, false, date);
            }
            command = new AddCommand(commandType, task);
        } else if (type == COMMAND_TYPE.BYE) {
            task = new Task();
            command = new ByeCommand(commandName, task);
        } else if (type == COMMAND_TYPE.DELETE) {
            String subsequent = input.substring("Delete ".length()).trim();
            int numberToDelete = Integer.parseInt(subsequent);
            task = new Task();
            command = new DeleteCommand(commandName, task, numberToDelete);
        } else if (type == COMMAND_TYPE.DONE) {
            String subsequent = input.substring("Done ".length()).trim();
            int numberDone = Integer.parseInt(subsequent);
            task = new Task();
            command = new DeleteCommand(commandName, task, numberDone);
        } else if (type == COMMAND_TYPE.LIST) {
            command = new ListCommand(commandName);
        } else throw new CommandNotFoundException();


        return command;
    }


    public static Task parseFromFile(String input) throws IncorrectFileFormatException {
        // Split the entire line of task using "\"
        // Assumes format of file to be same as saved in arraylist
        // [T][tick] jrjrf fwjeiojf  /by 2/12/2019 180

        Task task = new Task();
        String subsequent;
        boolean isDone = false;
        char tick = '\u2713', cross = '\u274C';

        //System.out.println(input.startsWith("[E]"));
        boolean check = input.startsWith("[D]") || input.startsWith("[E]") || input.startsWith("[T]");
        if (!check) {
            throw new IncorrectFileFormatException();
        } else {
            subsequent = input.substring("[X]".length());
            //System.out.println(subsequent);
            if (subsequent.startsWith("[1]")) {
                isDone = true;
            } else if (subsequent.startsWith("[0]")) {
                isDone = false;
            } else throw new IncorrectFileFormatException();

            // Move to after cross/tick and space
            subsequent = subsequent.substring(4).trim();
            //System.out.println(subsequent);

            if (input.startsWith("[T]")) {
                task = new ToDo('T', subsequent, isDone);
            } else if (input.startsWith("[D]") || input.startsWith("[E]")) {
                int slashIndex = subsequent.indexOf('/');
                if (slashIndex == -1) throw new IncorrectFileFormatException();


                String taskDescription = subsequent.substring(0, slashIndex).trim();
                //System.out.println(taskDescription);

                String date = subsequent.substring(slashIndex + "xx: ".length() + 1);
                //System.out.println(date);

                if (input.startsWith("[D]")) {
                    task = new Deadline('D', taskDescription, isDone, date);
                } else if (input.startsWith("[E]")) {
                    task = new Event('E', taskDescription, isDone, date);
                } else throw new IncorrectFileFormatException();
            }
        }
        return task;
    }
}

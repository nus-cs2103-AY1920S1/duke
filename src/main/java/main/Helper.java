package main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Helper class for the user.
 */
public class Helper {

    private static int NUMBER_OF_COMMANDS = 11;
    String[] arr = new String[NUMBER_OF_COMMANDS + 1];


    /**
     * Constructs a new Helper object.
     */
    public Helper() {
        arr[0] = "Here are the list of commands and their allowable input formats";
        arr[1] = "bye";
        arr[2] = "list";
        arr[3] = "done <task ID>";
        arr[4] = "delete <task ID>";
        arr[5] = "todo <task name>";
        arr[6] = "event <task name> / dd/MM/yyyy HHmm";
        arr[7] = "deadline <task name> / dd/MM/yyyy HHmm";
        arr[8] = "help <specific command>";
        arr[9] = "clear";
        arr[10] = "find <keyword>";
        arr[11] = "archive all/today/taskID/done";
    }

    /**
     * Returns an array of allowable input formats.
     *
     * @return An array with the list of allowable input formats
     */
    public String[] getCommands() {
        return arr;
    }

    /**
     * Helps the user identify the purpose of the command and its correct syntax.
     * @param command the command
     * @return the purpose of the command and the respective command syntax.
     */
    public String[] getSpecificCommand(String command) {
        String[] res;
        switch (command) {
        case "bye":
            res = new String[2];
            res[0] = "bye: Exits the program.";
            res[1] = "syntax: bye";
            break;
        case "list":
            res = new String[2];
            res[0] = "list: I will list out all the tasks you have in your task list";
            res[1] = "syntax: list";
            break;
        case "todo":
            res = new String[2];
            res[0] = "todo: Add a new todo task into your task list";
            res[1] = "syntax: todo <task name>";
            break;
        case "event":
            res = new String[2];
            res[0] = "event: Add a new event item into your task list";
            res[1] = "syntax: event <event name> / dd/MM/yyyy HHmm";
            break;
        case "deadline":
            res = new String[2];
            res[0] = "deadline: Add a new deadline item into your task list";
            res[1] = "syntax: deadline <task name> / dd/MM/yyyy HHmm";
            break;
        case "find":
            res = new String[2];
            res[0] = "find: Finds tasks in your task list by a specific keyword";
            res[1] = "syntax: find <keyword>";
            break;
        case "done":
            res = new String[2];;
            res[0] = "done: Marks a task as completed in the task list";
            res[1] = "syntax: done <task ID>";
            break;
        case "delete":
            res = new String[2];
            res[0] = "delete: Permanently removes a task from your task list";
            res[1] = "syntax: delete <task ID>";
            break;
        case "archive":
            res = new String[6];
            res[0] = "archive all: Move all tasks into your archive task list.";
            res[1] = "archive done: Move all completed tasks into your archive task list.";
            res[2] = "archive today: Move all expired tasks into your archive task list.";
            res[3] = "archive <taskID>: Move the task with specified task ID into your archive task list.";
            res[4] = "Note that all archived tasks will be removed from your existing task list.";
            res[5] = "syntax: archive all/done/today/<taskID>";
            break;
        case "clear":
            res = new String[2];
            res[0] = "clear: Empties your current task list by permanently removing all existing tasks.";
            res[1] = "syntax: clear";
            break;
        default:
            res = new String[2];
            res[0] = command + " is not yet a valid command.";
            res[1] = "Type 'help' to see the list of all commands";
        }
        return res;
    }
}

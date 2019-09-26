package utils;

import commands.Command;
import commands.DoneCommand;
import commands.ListCommand;
import commands.FindCommand;
import commands.ExitCommand;
import commands.DeleteCommand;
import commands.UndoCommand;
import commands.AddCommand;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import exceptions.DukeException;

import java.util.Stack;


public class Parser {

    private static final String ERROR_EMPTY_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_INPUT_FORMAT = "Please check the format of your input!";
    private static final String ERROR_TODO = "☹ OOPS!!! The description of a " + "todo" + " cannot be empty.";
    private static final String ERROR_TIME_EMPTY = "Please input the time as well!";
    private static Stack<Command> recentCommands = new Stack<>();

    /**
     * Parses user input into command for execution, and creates the
     * relevant command based on the said input.
     *
     * @param fullCommand the user input to parse
     * @return command to execute
     * @throws DukeException for user input errors.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Command c;
        String[] commandArr = fullCommand.split(" ");
        String[] keywords;
        int i;
        int taskId;
        String userCommand = commandArr[0];

        try {
            switch (userCommand) {
            case "todo":
                String name = "";

                if (commandArr.length < 2) {
                    throw new DukeException(ERROR_TODO);
                }

                for (i = 1; i < commandArr.length; i++) {
                    name += commandArr[i] + " ";
                }
                c = new AddCommand(new Todo(name.trim()));
                recentCommands.push(c);
                break;

            case "deadline":
                keywords = splitCommands(commandArr, "/by", "deadline");
                c = new AddCommand(new Deadline(keywords[0], new StringToDate(keywords[1])));
                recentCommands.push(c);
                break;

            case "event":
                keywords = splitCommands(commandArr, "/at", "event");
                c = new AddCommand(new Event(keywords[0], new StringToDate(keywords[1])));
                recentCommands.push(c);
                break;

            case "list":
                c = new ListCommand();
                break;

            case "done":
                taskId = Integer.parseInt(commandArr[1]);
                c = new DoneCommand(taskId);
                recentCommands.push(c);
                break;

            case "delete":
                taskId = Integer.parseInt(commandArr[1]);
                c = new DeleteCommand(taskId);
                recentCommands.push(c);
                break;

            case "find":
                String keyword = commandArr[1];
                c = new FindCommand(keyword);
                break;

            case "undo":
                if (!recentCommands.empty()) {
                    c = new UndoCommand(recentCommands.pop());
                    break;
                } else {
                    throw new DukeException("No actions to undo!");
                }
            case "bye":
                c = new ExitCommand();
                break;

            default:
                throw new DukeException(ERROR_EMPTY_INPUT);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ERROR_INPUT_FORMAT);
        }
        return c;

    }

    /**
     * Parse the deadline and event input to obtain name of task
     * and time.
     *
     * @param commandArr array of commands from user
     * @param keyword to look for
     * @param taskType the type of task
     * @return the name of task and time
     * @throws DukeException in case of invalid input
     */
    private static String[] splitCommands(String[] commandArr, String keyword, String taskType) throws DukeException {
        String name = "";
        String time = "";
        boolean flag = false;
        int i;
        for (i = 1; i < commandArr.length; i++) {
            if (commandArr[i].equals(keyword)) {
                flag = true;
            } else if (flag) {
                time += commandArr[i] + " ";
            } else {
                name += commandArr[i] + " ";
            }
        }

        // checks if the name of event or deadline and their timings are given by user
        // appropriate errors are then thrown if needed
        if (name.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
        } else if (!flag) {
            throw new DukeException("Please remember to put " + keyword + " before the timing.");
        } else if (time.isEmpty()) {
            throw new DukeException(ERROR_TIME_EMPTY);
        } else {
            return new String[]{name.trim(), time.trim()};
        }
    }
}

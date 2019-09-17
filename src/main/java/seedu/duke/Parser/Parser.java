package seedu.duke.Parser;

import seedu.duke.TaskList.Deadlines;
import seedu.duke.TaskList.Events;
import seedu.duke.TaskList.ToDos;
import seedu.duke.Command.Command;
import seedu.duke.Command.AddCommand;
import seedu.duke.Command.ByeCommand;
import seedu.duke.Command.DeleteCommand;
import seedu.duke.Command.DoneCommand;
import seedu.duke.Command.ListCommand;
import seedu.duke.Command.FindCommand;

public class Parser {
    public Parser() {}

    /**
     * Parse in the full command and return the command accordingly
     * @param fullCommand
     * @return Command
     */
    public static Command parse(String fullCommand) {
        int i = fullCommand.indexOf(' ');
        String first = getFirstWord(fullCommand);
        switch (first) {
            case "delete":
                return new DeleteCommand(Integer.parseInt(fullCommand.substring(i + 1)));
            case "done":
                return new DoneCommand(Integer.parseInt(fullCommand.substring(i + 1)));
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            case "find":
                return new FindCommand(fullCommand.substring((i + 1)));
            case "todo":
                addNewTodo(fullCommand);
            case "deadline":
                addNewDeadline(fullCommand);
            case "event":
                addNewEvent(fullCommand);
            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-()");
                assert false : first;
                return null;
        }
    }

    private static AddCommand addNewEvent(String fullCommand) {
        int p = fullCommand.indexOf("/");
        int q = fullCommand.indexOf(" ");
        int length = fullCommand.length();
        switch (length) {
            case 5:
                System.out.println("OOPS!!! The description of an event cannot be empty.");
                return null;
            default:
                Events taskEvent = new Events(fullCommand.substring(q + 1, p - 1));
                taskEvent.setTime(fullCommand.substring(p + 4));
                taskEvent.setTaskType("E");
                return new AddCommand(taskEvent);
        }
    }

    private static AddCommand addNewDeadline(String fullCommand) {
        int j = fullCommand.indexOf("/");
        int k = fullCommand.indexOf(" ");
        int length = fullCommand.length();
        switch (length) {
            case 8:
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                return null;
            default:
                Deadlines taskDeadline = new Deadlines(fullCommand.substring(k + 1, j - 1));
                taskDeadline.setTime(fullCommand.substring(j + 4));
                taskDeadline.setTaskType("D");
                return new AddCommand(taskDeadline);
        }
    }

    private static AddCommand addNewTodo(String fullCommand) {
        int i = fullCommand.indexOf(' ');
        int length = fullCommand.length();
        switch (length) {
            case 4:
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                return null;
            default:
                ToDos taskTodo = new ToDos(fullCommand.substring(i + 1));
                taskTodo.setTaskType("T");
                return new AddCommand(taskTodo);
        }
    }


    /**
     * to get the first word in a string of input
     * @param input
     * @return  String
     */
        public static String getFirstWord(String input) {
            if (input.indexOf(" ") > -1) {
                return input.substring(0, input.indexOf(" "));
            } else {
                return input;
            }
        }
    }
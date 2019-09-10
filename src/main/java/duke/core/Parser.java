package duke.core;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Represents a <code>Parser</code> that parses user input into a specific 
 * type of <code>Command</code>. The <code>Parser</code> also parses tasks
 * related to the command, and checks for illegal instructions.
 */
public class Parser {
    /**
     * Creates a string from a sub array of a string array.
     *
     * @param words The string array to be processed.
     * @param start The starting position in the string array.
     * @param end The ending position in the string array.
     * @return A string consisting of all the words from the starting position
     *          to the ending position, separated by spaces.
     */
    private static String subString(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(words[i] + " ");
        }
        return sb.toString().trim();
    }

    /**
     * Finds the index of a given string in a string array.
     *
     * @param words The string array to be inspected.
     * @param s The string to be searched.
     * @return The index of the given string in the string array. Returns -1 if
     *          the given string does not occur in the array.
     */
    private static int findIdx(String[] words, String s) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Parses a <code>Task</code> from a string array.
     *
     * @param words The string array to be parsed.
     * @return The <code>Task</code> that is parsed from the string array.
     */
    private static Task parseTask(String[] words) {
        if (words[0].equals("todo")) {
            return new ToDo(subString(words, 1, words.length));
        } else if (words[0].equals("deadline")) {
            int i = findIdx(words, "/by");
            String description = subString(words, 1, i);
            String by = subString(words, i + 1, words.length);
            return new Deadline(description, by);
        } else {
            assert words[0].equals("event") : "Instruction type should be event";
            int i = findIdx(words, "/at");
            String description = subString(words, 1, i);
            String at = subString(words, i + 1, words.length);
            return new Event(description, at);
        }
    }

    /**
     * Checks for illegal user input and throws exceptions accordingly.
     *
     * @param words The string array to be checked.
     */
    private static void checkIllegalInstruction(String[] words) throws DukeException {
        String fw = words[0];
        if (!(fw.equals("done") || fw.equals("todo") || fw.equals("deadline") || fw.equals("event")
                    || fw.equals("delete") || fw.equals("list") || fw.equals("bye") || fw.equals("find"))) {
            throw new DukeException(" \u2639  OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if ((fw.equals("todo") || fw.equals("deadline") || fw.equals("event")) && words.length < 2) {
            throw new DukeException(" \u2639  OOPS!!! The description of a " + fw + " cannot be empty.");
        }
        if ((fw.equals("deadline") && findIdx(words, "/by") == -1)
                || (fw.equals("event") && findIdx(words, "/at") == -1)) {
            throw new DukeException(" \u2639  OOPS!!! The time of a " + fw + " cannot be empty.");
        }
        if ((fw.equals("done") || fw.equals("delete")) && words.length < 2) {
            throw new DukeException(" \u2639  OOPS!!! The task number of a " + fw + " cannot be empty.");
        }
        if ((fw.equals("done") || fw.equals("delete")) && Integer.parseInt(words[1]) < 1) {
            throw new DukeException(" \u2639  OOPS!!! The task number of a " + fw + " cannot be less than 1.");
        }
        if (fw.equals("find") && words.length < 2) {
            throw new DukeException(" \u2639  OOPS!!! The keyword of a " + fw + " cannot be empty.");
        }
    }

    /**
     * Parses a line of user instruction into respective <code>Command</code> 
     * types.
     *
     * @param s The string that represents user input.
     * @return A <code>Command</code> that represents the specific type of
     *          command the user gives.
     */
    public static Command parse(String s) throws DukeException {
        String[] words = s.split(" ");
        checkIllegalInstruction(words);
        String fw = words[0];
        assert fw.equals("bye") || fw.equals("done") || fw.equals("delete")
                || fw.equals("list") || fw.equals("find") || fw.equals("todo")
                || fw.equals("event") || fw.equals("deadline") : "Invalid user input";
        switch (fw) {
        case "bye":
            return new ExitCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(words[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(words[1]));
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(words[1]);
        default:
            assert fw.equals("todo") || fw.equals("event") || fw.equals("deadline")
                    : "First word should be todo / deadline / event";
            Task t = parseTask(words);
            return new AddCommand(t);
        }
    }
}


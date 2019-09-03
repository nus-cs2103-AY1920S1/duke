package duke;

import duke.command.*;
import duke.DukeException;
import duke.task.*;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parses input entered by user.
 */
public class Parser {
    protected static HashMap<String, Command> keywordToCommand = createMap();
    protected static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    static HashMap<String, Command> createMap() {
        HashMap<String, Command> keywordToCommand = new HashMap<>();
        keywordToCommand.put("bye", new ExitCommand());
        keywordToCommand.put("list", new ListCommand());
        keywordToCommand.put("done", new DoneCommand());
        keywordToCommand.put("delete", new DeleteCommand());
        keywordToCommand.put("todo", new AddCommand());
        keywordToCommand.put("deadline", new AddCommand());
        keywordToCommand.put("event", new AddCommand());
        return keywordToCommand;
    }

    /**
     * Returns the command as specified by the input.
     * If input cannot be parsed, throws DukeException.
     *
     * @param input Input entered by user.
     * @return Command object specified by input.
     * @throws DukeException If input cannot be parsed.
     */
    public static Command parse(String input) throws DukeException {
        if(keywordToCommand.containsKey(input)) {
            return keywordToCommand.get(input);
        } else {
            String keyword = input.split(" ")[0];
            Command command;
            if(keywordToCommand.containsKey(keyword)) {
                command = keywordToCommand.get(keyword).clone(input);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return command;
        }
    }

    /**
     * Returns the itemId for the task to be marked done.
     * If input cannot be parsed, throws DukeException.
     *
     * @param input Input entered by user.
     * @return itemId for object to be marked done.
     * @throws DukeException If input has incorrect format.
     */
    public static int parseDone(String input) throws DukeException {
        String[] words = input.split(" ");
        int itemId;
        try {
            assert(words.length == 2);
            String command = words[0];
            assert(command.equals("done"));
            itemId = Integer.parseInt(words[1]);
        } catch(AssertionError e) {
            throw new DukeException("☹ OOPS!!! Incorrect format for done command.");
        }
        return itemId;
    }

    /**
     * Returns the itemId for the task to be deleted.
     * If input cannot be parsed, throws DukeException.
     *
     * @param input Input entered by user.
     * @return itemId for object to be marked done.
     * @throws DukeException If input has incorrect format.
     */
    public static int parseDelete(String input) throws DukeException {
        String[] words = input.split(" ");
        int itemId;
        try {
            assert(words.length == 2);
            String command = words[0];
            assert(command.equals("delete"));
            itemId = Integer.parseInt(words[1]);
        } catch(AssertionError e) {
            throw new DukeException("☹ OOPS!!! Incorrect format for delete command.");
        }
        return itemId;
    }

    /**
     * Returns the Task to be added to the TaskList.
     * If input cannot be parsed, throws DukeException.
     *
     * @param input Input entered by user.
     * @return Task to be added.
     * @throws DukeException If input has incorrect format.
     */
    public static Task parseTask(String input) throws DukeException {
        String[] words = input.split(" ");
        String type = words[0];
    
        String info = input.substring((type + " ").length());
        switch(type) {
        case "todo":
            if(info.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new Todo(info);
        case "deadline":
            String[] args = info.split("/by");
            String description = args[0].trim();
            Date date;
            try {
                date = dateFormatter.parse(args[1].trim());
            } catch(Exception e) {
                throw new DukeException("☹ OOPS!!! Incorrect date format.");
            }
            return new Deadline(description, date);
        case "event":
            args = info.split("/at");
            description = args[0].trim();
            try {
                date = dateFormatter.parse(args[1].trim());
            } catch(Exception e) {
                throw new DukeException("☹ OOPS!!! Incorrect date format.");
            }
            return new Event(description, date);
        }
        throw new DukeException("Invalid task input.");
    }
}
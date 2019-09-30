package duke;

import java.util.HashMap;
import java.util.Map;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

class Parser {
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Registers a command to be returned later.
     *
     * @param name Name that will trigger the command.
     * @param command Command to return later.
     */
    void register(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * Returns the appropriate command given the line that the user input.
     *
     * @param words Line of input.
     * @return Command to use on the input.
     * @throws DukeException If there is no such command.
     */
    Command parse(String[] words) throws DukeException {
        Command command = commands.get(words[0]);
        if (command == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }

    /**
     * Returns a parser with the default commands for Duke.
     *
     * @param tasks Task list to use.
     * @param storage Storage to store tasks.
     * @return Parser to parse commands.
     */
    static Parser getForDefaultCommands(TaskList tasks, Storage storage) {
        Parser parser = new Parser();
        parser.register("todo", ToDo.getCommand(tasks, storage));
        parser.register("deadline", Deadline.getCommand(tasks, storage));
        parser.register("event", Event.getCommand(tasks, storage));
        parser.register("list", new ListCommand(tasks));
        parser.register("find", new FindCommand(tasks));
        parser.register("view", new ViewCommand(tasks));
        parser.register("done", new DoneCommand(tasks, storage));
        parser.register("delete", new DeleteCommand(tasks, storage));
        parser.register("help", new HelpCommand());
        parser.register("bye", new ByeCommand());
        return parser;
    }
}

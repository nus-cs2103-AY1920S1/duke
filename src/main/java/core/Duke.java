package core;

import command.Command;
import command.CommandType;
import exception.DukeException;
import exception.DukeIllegalArgumentException;
import task.TaskList;
import ui.Cli;
import ui.Ui;

import java.io.File;

/**
 * Duke Chat Class.
 *
 * <p>A Personal Assistant Chatbot that helps to keep track of various things.
 *
 * @author Marcus Ong
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public final static String filePath = System.getProperty("user.dir") + "/data/duke.txt";

    /**
     * Duke Constructor, defaults to CLI mode.
     *
     * @param filePath String containing path of data file for Duke.
     */
    public Duke(String filePath) {
        ui = new Cli();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            loadAliases();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Duke Constructor.
     *
     * @param filePath String containing path of data file for Duke.
     */
    public Duke(String filePath, Ui ui) {
        this.ui = ui;
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            loadAliases();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void loadAliases() throws DukeIllegalArgumentException {
        Command.addAlias(CommandType.ADD_ALIAS, "add-alias", "alias");
        Command.addAlias(CommandType.ADD_DEADLINE, "deadline", "dead");
        Command.addAlias(CommandType.ADD_EVENT, "event", "ev");
        Command.addAlias(CommandType.ADD_TODO, "todo", "td");
        Command.addAlias(CommandType.DELETE_ALIAS, "delete-alias", "del-alias");
        Command.addAlias(CommandType.DELETE_TASK, "delete", "del");
        Command.addAlias(CommandType.DONE_TASK, "done");
        Command.addAlias(CommandType.EXIT, "bye", "exit", "logout");
        Command.addAlias(CommandType.FIND_TASK, "find", "search");
        Command.addAlias(CommandType.LIST_ALIASES, "list-alias", "list-aliases");
        Command.addAlias(CommandType.LIST_TASKS, "list", "tasks");
    }

    /**
     * Getter for Ui.
     *
     * @return Ui of Duke.
     */
    public Ui getUi() {
        return ui;
    }

    public static void main(String[] args) {
        new Duke(Duke.filePath, new Cli()).run();
    }

    /**
     * Run chat bot interaction.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ((Cli) ui).readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Consume user input from GUI.
     *
     * @param input String containing user input.
     * @return True if should exit program.
     */
    public boolean consumeUserInput(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }
}

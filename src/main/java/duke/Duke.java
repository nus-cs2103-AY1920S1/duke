package duke;

/**
 * The class contains all the custom classes (not related to UI) needed for application.
 */
public class Duke {
    public static Storage storage;
    public static TaskList taskList;
    public static Parser parser;

    /**
     * Public constructor for Duke class.
     * Initialises Storage, TaskList, and Parser classes.
     */
    public Duke() {
        storage = new Storage();
        taskList = new TaskList(Duke.storage);
        parser = new Parser(Duke.taskList);
    }
}

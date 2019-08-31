/**
 * The class contains all the custom classes (not related to UI) needed for application.
 */
public class Duke {
    protected Storage storage;
    protected TaskList taskList;
    protected Parser parser;

    /**
     * Public constructor for Duke class.
     * Initialises Storage, TaskList, and Parser classes.
     */
    public Duke() {
        storage = new Storage("C:\\Users\\User\\Documents\\duke\\data\\duke.txt");
        taskList = new TaskList(storage);
        parser = new Parser(taskList);
    }
}

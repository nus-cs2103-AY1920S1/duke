public class Duke{

    private Storage storage;
    private TaskList tasks;
    private UserInterface ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList(filePath);
        }
        ui = new UserInterface(tasks, storage);
    }

    void run() {
        dukePrint("Hello! I'm Duke", "What can I do for you?");
        this.ui.listen();
        dukePrint("Bye. Hope to see you again soon!");
    }
    /**
     * Main driver class for Duke.
     *
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Echos string.
     * @param echoedString targeted String to be echoed
     */
    private static void echo(String echoedString) {
        dukePrint(echoedString);
    }

    /**
     * Prints what Duke says in correct format.
     * @param texts any number of String arguments
     *              each prints on a new line.
     */

    private static void dukePrint(Object... texts) {
        System.out.println("    _____________________________");
        for (Object text : texts) {
            System.out.println("     " + text);
        }
        System.out.println("    _____________________________");

    }
}

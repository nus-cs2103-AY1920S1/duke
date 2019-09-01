package seedu.duke;

import seedu.duke.Ui;
import seedu.duke.Storage;
import seedu.duke.Parser;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeNoSuchCommandException;

/**
 * Main class of this project.
 * Personal assistant bot to help you keep track of tasks.
 * To run this program, create the Duke object, and use the .run() method.
 * @author Lim Daekoon
 */
public class Duke {

    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    /**
     * Creates a new Duke object to run.
     */
    public Duke() {

        ui = new Ui();
        storage = new Storage("save.txt");
        this.taskList = storage.load();

        String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Ui.printBlock(new String[] {"Hello! I'm Duke.", "What can I do for you?"});

        // Initializes the parser so that the loop runs at least once
        try {
            this.parser = new Parser("list");
        } catch (Exception e) {
            Ui.printBlock("Unexpected Error");
        }

    }

    /**
     * Runs the Duke program.
     */
    public void run() {

        while (!parser.getType().equals("bye")) {
            try {
                parser = ui.readCommand();

                switch (parser.getType()) {
                case "bye":
                    return;
                case "list":
                    taskList.list();
                    break;
                case "done":
                    taskList.markAsDone(parser);
                    break;
                case "todo":
                    taskList.addTodo(parser);
                    break;
                case "deadline":
                    taskList.addDeadline(parser);
                    break;
                case "event":
                    taskList.addEvent(parser);
                    break;
                case "delete":
                    taskList.deleteTask(parser);
                    break;
                default:
                    throw new DukeNoSuchCommandException();
                }

                storage.save(taskList);

            } catch (DukeException e) {
                e.printMessage();
            }
        }
        Ui.printBlock("Bye. Hope to see you again soon!");
    }

    /**
     * Default static main method of this project.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

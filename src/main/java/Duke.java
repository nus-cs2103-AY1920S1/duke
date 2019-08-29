import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke {

    // class variables
    UI ui;
    Parser parser;
    Storage storage;
    TaskList tl;

    /**
     * Class constructor for the Duke class.
     */
    public Duke() {
        this.ui = new UI();
        this.parser = new Parser();
        this.storage = new Storage("data/duke.txt");
        try {
            this.tl = new TaskList(storage.readTasksFromFile());
        } catch (DukeException e) {
            this.ui.printError(e);
            System.exit(0);
        }
    }

    /**
     * Method which runs and performs user input and task addition.
     */
    public void run() {
        this.ui.printWelcome();
        this.ui.showTaskList(this.tl);

        String input = this.ui.takeInput();
        try {
            while (!input.equalsIgnoreCase("bye")) {
                Task task;
                switch (this.parser.parseCommand(input)) {
                case "list":
                    this.ui.showTaskList(this.tl);
                    break;
                case "done":
                    task = this.tl.taskDone(this.parser.parseInteger(input.split(" ")[1]));
                    this.ui.showTaskMarkedDone(task);
                    break;
                case "delete":
                    task = this.tl.removeTask(this.parser.parseInteger(input.split(" ")[1]));
                    this.ui.showTaskDeletion(task, this.tl);
                    break;
                case "todo":
                    task = this.parser.parseTodo(input.split(" ", 2)[1]);
                    tl.addTask(task);
                    this.ui.showTaskAddition(task, this.tl);
                    break;
                case "event":
                    task = this.parser.parseEvent(input.split(" ", 2)[1]);
                    tl.addTask(task);
                    this.ui.showTaskAddition(task, tl);
                    break;
                case "deadline":
                    task = this.parser.parseDeadline(input.split(" ", 2)[1]);
                    tl.addTask(task);
                    this.ui.showTaskAddition(task, tl);
                    break;
                case "find":
                    TaskList newTl = tl.findTasks(input.split(" ", 2)[1]);
                    this.ui.showTaskList(newTl);
                    break;
                default:
                    this.ui.printError(new DukeException("Unknown command. :("));
                    break;
                }
                this.storage.writeTasksToFile(this.tl);
                input = this.ui.takeInput();
            }
        } catch (DukeException e) {
            this.ui.printError(e);
        }

        this.ui.printExit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

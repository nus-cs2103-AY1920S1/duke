/** Class to handle the different commands input. */
public class CommandHandler {

    // class variables
    private UI ui;
    private Parser parser;
    private Storage storage;
    private TaskList tl;

    /**
     * Class constructor to define a CommandHandler instance.
     * @param ui UI to generate output.
     * @param parser Parser to parse commands.
     * @param storage Storage to store stuff.
     * @param tl TaskList to maintain tasks.
     */
    public CommandHandler(UI ui, Parser parser, Storage storage, TaskList tl) {
        this.ui = ui;
        this.parser = parser;
        this.storage = storage;
        this.tl = tl;
    }

    /**
     * Execute a user input.
     * @param input Input to be executed.
     * @return String showing the output.
     * @throws DukeException Possible eception which can be thrown during runtime.
     */
    public String execute(String input) throws DukeException {
        Task task;
        String returnString = "";
        switch (this.parser.parseCommand(input)) {
        case "bye":
            returnString = this.ui.printExit();
            System.exit(0);
            break;
        case "list":
            returnString = this.ui.showTaskList(this.tl);
            break;
        case "done":
            task = this.tl.taskDone(this.parser.parseInteger(input));
            returnString = this.ui.showTaskMarkedDone(task);
            break;
        case "delete":
            task = this.tl.removeTask(this.parser.parseInteger(input));
            returnString = this.ui.showTaskDeletion(task, this.tl);
            break;
        case "todo":
            task = this.parser.parseTodo(input);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, this.tl);
            break;
        case "event":
            task = this.parser.parseEvent(input);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, tl);
            break;
        case "deadline":
            task = this.parser.parseDeadline(input);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, tl);
            break;
        case "find":
            TaskList newTl = tl.findTasks(input);
            returnString = this.ui.showTaskList(newTl);
            break;
        default:
            returnString = this.ui.printError(new DukeException("Unknown command. :("));
            break;
        }
        this.storage.writeTasksToFile(this.tl);
        return returnString;
    }
}
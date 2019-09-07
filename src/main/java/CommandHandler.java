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
        String commandType = this.parser.parseCommand(input);
        switch (commandType) {
        case "bye":
            assert input.split(" ")[0].equals("bye") : "First word in input should be `bye`.";
            returnString = this.ui.printExit();
            System.exit(0);
            break;
        case "list":
            assert input.split(" ")[0].equals("list") : "First word in input should be `list`.";
            returnString = this.ui.showTaskList(this.tl);
            break;
        case "done":
            assert input.split(" ")[0].equals("done") : "First word in input should be `done`.";
            task = this.tl.taskDone(this.parser.parseInteger(input));
            returnString = this.ui.showTaskMarkedDone(task);
            break;
        case "delete":
            assert input.split(" ")[0].equals("delete") : "First word in input should be `delete`.";
            task = this.tl.removeTask(this.parser.parseInteger(input));
            returnString = this.ui.showTaskDeletion(task, this.tl);
            break;
        case "todo":
            assert input.split(" ")[0].equals("todo") : "First word in input should be `todo`.";
            task = this.parser.parseTodo(input);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, this.tl);
            break;
        case "event":
            assert input.split(" ")[0].equals("event") : "First word in input should be `event`.";
            task = this.parser.parseEvent(input);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, tl);
            break;
        case "deadline":
            assert input.split(" ")[0].equals("deadline") : "First word in input should be `deadline`.";
            task = this.parser.parseDeadline(input);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, tl);
            break;
        case "find":
            assert input.split(" ")[0].equals("find") : "First word in input should be `find`.";
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
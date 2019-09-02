/** Class to handle the different commands input. */
public class CommandHandler {

    // class variables
    private UI ui;
    private Parser parser;
    private Storage storage;
    private TaskList tl;

    // class constructor
    public CommandHandler(UI ui, Parser parser, Storage storage, TaskList tl) {
        this.ui = ui;
        this.parser = parser;
        this.storage = storage;
        this.tl = tl;
    }

    // execute a command
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
            task = this.tl.taskDone(this.parser.parseInteger(input.split(" ")[1]));
            returnString = this.ui.showTaskMarkedDone(task);
            break;
        case "delete":
            task = this.tl.removeTask(this.parser.parseInteger(input.split(" ")[1]));
            returnString = this.ui.showTaskDeletion(task, this.tl);
            break;
        case "todo":
            task = this.parser.parseTodo(input.split(" ", 2)[1]);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, this.tl);
            break;
        case "event":
            task = this.parser.parseEvent(input.split(" ", 2)[1]);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, tl);
            break;
        case "deadline":
            task = this.parser.parseDeadline(input.split(" ", 2)[1]);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, tl);
            break;
        case "find":
            TaskList newTl = tl.findTasks(input.split(" ", 2)[1]);
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
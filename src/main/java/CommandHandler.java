import java.util.Date;

/** Class to handle the different commands input. */
public class CommandHandler {

    // class variables
    private UI ui;
    private Parser parser;
    private Storage storage;
    private TaskList tl;

    // run a conversation handler for updating task
    private boolean isUpdatingTask;
    private int taskBeingUpdated;

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
        this.isUpdatingTask = false;
        this.taskBeingUpdated = -1;
    }

    /**
     * Execute a user input.
     * @param input Input to be executed.
     * @return String showing the output.
     * @throws DukeException Possible eception which can be thrown during runtime.
     */
    public String execute(String input) throws DukeException {
        if (this.parser.parseCommand(input).equalsIgnoreCase("bye")) {
            System.exit(0);
        }
        if (this.isUpdatingTask) {
            return this.handleUpdate(input);
        }
        Task task;
        String returnString = "";
        switch (this.parser.parseCommand(input)) {
        case "list":
            assert input.split(" ")[0].equalsIgnoreCase("list") : "First word in input should be `list`.";
            returnString = this.ui.showTaskList(this.tl);
            break;
        case "done":
            assert input.split(" ")[0].equalsIgnoreCase("done") : "First word in input should be `done`.";
            task = this.tl.taskDone(this.parser.parseInteger(input));
            returnString = this.ui.showTaskMarkedDone(task);
            break;
        case "delete":
            assert input.split(" ")[0].equalsIgnoreCase("delete") : "First word in input should be `delete`.";
            task = this.tl.removeTask(this.parser.parseInteger(input));
            returnString = this.ui.showTaskDeletion(task, this.tl);
            break;
        case "todo":
            assert input.split(" ")[0].equalsIgnoreCase("todo") : "First word in input should be `todo`.";
            task = this.parser.parseTodo(input);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, this.tl);
            break;
        case "event":
            assert input.split(" ")[0].equalsIgnoreCase("event") : "First word in input should be `event`.";
            task = this.parser.parseEvent(input);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, tl);
            break;
        case "deadline":
            assert input.split(" ")[0].equalsIgnoreCase("deadline") : "First word in input should be `deadline`.";
            task = this.parser.parseDeadline(input);
            tl.addTask(task);
            returnString = this.ui.showTaskAddition(task, tl);
            break;
        case "find":
            assert input.split(" ")[0].equalsIgnoreCase("find") : "First word in input should be `find`.";
            TaskList newTl = tl.findTasks(input.split(" ", 2)[1]);
            returnString = this.ui.showTaskList(newTl);
            break;
        case "update":
            assert input.split(" ")[0].equalsIgnoreCase("update") : "First word in input should be `update`.";
            int taskIndex = this.parser.parseInteger(input);
            returnString = this.ui.showTaskBeingUpdated(this.tl.getTasks().get(taskIndex - 1));
            this.isUpdatingTask = true;
            this.taskBeingUpdated = taskIndex - 1;
            break;
        default:
            returnString = this.ui.printError(new DukeException("Unknown command. :("));
            break;
        }
        this.storage.writeTasksToFile(this.tl);
        return returnString;
    }

    /**
     * Function to handle the updating of a task.
     * @param input Input string storing update details.
     * @return Formatted string.
     */
    public String handleUpdate(String input) throws DukeException {
        Task task;
        try {
            String attribute = input.split(" ", 2)[0];
            String value = input.split(" ", 2)[1];
            if (attribute.equalsIgnoreCase("name")) {
                task = this.tl.updateName(this.taskBeingUpdated, value);
            } else if (attribute.equalsIgnoreCase("date")) {
                Date date = this.parser.parseDate(value);
                task = this.tl.updateDate(this.taskBeingUpdated, date);
            } else if (attribute.equalsIgnoreCase("both")) {
                String name = value.split("(/at|/by)")[0];
                Date date = this.parser.parseDate(value.split("(/at|/by)")[1]);
                task = this.tl.updateBoth(this.taskBeingUpdated, name, date);
            } else {
                throw new DukeException("You can only update date or name. :(");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter in the given format. :(");
        }
        this.isUpdatingTask = false;
        this.taskBeingUpdated = -1;
        return this.ui.showTaskIsUpdated(task);
    }

    /**
     * Function to check if command_handler is updating task.
     */
    public boolean isUpdatingTask() {
        return this.isUpdatingTask;
    }
}

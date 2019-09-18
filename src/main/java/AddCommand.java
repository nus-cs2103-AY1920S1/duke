/**
 * Represent user command to add tasks into their task list.
 */

public class AddCommand extends Command {

    protected String desc;
    protected String dateTime;
    private Task newTask;
    private TaskList tasks;

    /**
     * Represents an action to add tasks that does not require date and time.
     * @param command Type of task
     * @param desc Description of task
     */
    public AddCommand(String command, String desc) {
        super(command);
        this.desc = desc;
    }

    /**
     * Represents action to add tasks that require date and time.
     * @param command Type of task
     * @param desc Description of task
     * @param dateTime Date and Time of event
     */
    public AddCommand(String command, String desc, String dateTime) {
        super(command);
        this.desc = desc;
        this.dateTime = dateTime;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    /**
     * Creates and adds the specific type of task into the task list.
     *
     * @param tasks List of tasks.
     * @param ui User Interface.
     * @param storage Storage of tasks.txt files.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String cmd = this.getCommand();
        Task newTask;
        if (cmd.equals("deadline")) {
            newTask = new Deadline(this.getDesc(), this.getDateTime());
        } else if (cmd.equals("event")) {
            newTask = new Event(this.getDesc(), this.getDateTime());
        } else {
            newTask = new ToDo(this.getDesc());
        }
        tasks.addTask(newTask);
        this.newTask = newTask;
        this.tasks = tasks;

        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder printStr = new StringBuilder();
        printStr.append("Got it. I've added this task:\n");
        printStr.append("  " + newTask + "\n");
        printStr.append("Now you have " + tasks.getTaskCount() + " tasks in the list.");

        return printStr.toString();
    }
}

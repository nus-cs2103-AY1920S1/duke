/**
 * Represent user command to add tasks into their task list.
 */

public class AddCommand extends Command {

    protected String desc;
    protected String dateTime;

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

    public void execute(TaskList tasks, UI ui, Storage storage) {
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
        ui.showAddMessage(newTask, tasks);
    }

    public boolean isExit() {
        return false;
    }
}

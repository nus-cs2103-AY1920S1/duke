public class AddCommand extends Command {

    protected String desc;
    protected String dateTime;

    public AddCommand(String command, String desc) {
        super(command);
        this.desc = desc;
    }

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

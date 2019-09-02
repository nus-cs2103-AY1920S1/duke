import java.io.IOException;

public class SubCommand extends AddCommand {
    protected String subCommand, subDescription;
    public SubCommand(String commandWord, String description,
                      String subCommandWord, String subDescription) {
        super(commandWord, description, true);
        this.subCommand = subCommandWord;
        this.subDescription = subDescription;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task newTask = this.command.equals("deadline") ?
                new Deadline(this.description, this.subDescription) :
                new Event(this.description, this.subDescription);
        taskList.addTask(newTask);
        storage.save(taskList.getTaskArr());
        ui.showAddTaskMessage(newTask, taskList.getTaskArr());
    }

    public void print() {
        super.print();
        System.out.println("Subcommand: " + subCommand);
        System.out.println("Subcommand description: " + subDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        // Not even the same class
        if (!(o instanceof SubCommand)) { return false; }
        SubCommand c = (SubCommand) o;
        return c.subCommand == subCommand &&
                c.subDescription == subDescription;
    }

}
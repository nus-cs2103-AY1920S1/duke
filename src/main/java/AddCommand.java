public class AddCommand extends Command {
    String commandName;
    String argument;
    String optionName;
    String optionArgument;

    public AddCommand(String commandName, String argument) {
        this.commandName = commandName;
        this.argument = argument;
    }

    public AddCommand(String commandName, String argument, String optionName, String optionArgument) {
        this.commandName = commandName;
        this.argument = argument;
        this.optionName = optionName;
        this.optionArgument = optionArgument;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task myTask;
        switch (this.commandName) {
        case "todo":
            myTask = new Todo(this.argument);
            tasks.addTask(myTask);
            ui.printMessage("Got it. I've added this task: \n  " + myTask + "\nNow you have " + Ui.pluralize("task", tasks.getSize()) + " in the list.");
            break;
        case "deadline":
            if (!this.optionName.equals("by") || this.optionArgument.equals("")) {
                throw new DukeException("The date/time of a deadline cannot be empty.");
            }
            myTask = new Deadline(this.argument, Duke.parseDate(optionArgument));
            tasks.addTask(myTask);
            ui.printMessage("Got it. I've added this task: \n  " + myTask + "\nNow you have " + Ui.pluralize("task", tasks.getSize()) + " in the list.");
            break;
        case "event":
            if (!this.optionName.equals("at") || this.optionArgument.equals("")) {
                throw new DukeException("The date/time of an event cannot be empty.");
            }
            myTask = new Event(this.argument, Duke.parseDate(optionArgument));
            tasks.addTask(myTask);
            ui.printMessage("Got it. I've added this task: \n  " + myTask + "\nNow you have " + Ui.pluralize("task", tasks.getSize()) + " in the list.");
            break;
        }
        storage.save(tasks);
    }
}

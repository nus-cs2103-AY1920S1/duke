public class HelpCommand extends Command {

    private Ui ui;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.ui = ui;
        ui.show("Here is how you can use me:");
        print("bye", "to exit");
        print("list", "to show the tasks you have on your list");
        print("todo [description]", "to add a task of type todo");
        print("deadline [description] /by [date and time]", "to add a task of type deadline");
        print("event [description] /at [date and time]", "to add a task of type event");
        print("delete [task number]", "to delete the task of that specified index number from the task list");
        print("done [task number]", "to mark the task of that specified index number from the task list as "
                + "done");
    }

    public void print(String command, String description) {
        ui.show(Ui.TAB + command + " - " + description);
    }
}

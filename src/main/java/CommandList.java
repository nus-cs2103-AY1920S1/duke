//package mypackage;

public class CommandList extends Command {

    private String string;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Here are the tasks in your list:");
        int count = 1;
        for (Task t: tasks.getList()) {
            String tempStr = count + "." + t.toString();
            ui.printString(tempStr);
            count++;
        }

    }
}

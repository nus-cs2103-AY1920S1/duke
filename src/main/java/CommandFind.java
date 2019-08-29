public class CommandFind extends Command {

    private String string;

    public CommandFind(String str) {
        this.string = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Here are the matching tasks in your list:");
        int count = 1;
        for (Task t: tasks.getList()) {
            if (t.getDescription().contains(string)) {
                String tempStr = count + "." + t.toString();
                ui.printString(tempStr);
                count++;
            } else {

            }
        }
    }
}

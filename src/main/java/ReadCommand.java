public class ReadCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printLine("Here are the tasks in your list:");
        int i = 0;
        for (Task task : taskList.getTasksList()) {
            ui.printLine(String.format("%d. %s", (i + 1), task));
            i++;
        }
    }

}
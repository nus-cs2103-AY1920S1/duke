public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printListMessage();
        int counter = 0;
        for (Task t : tasks.taskList) {
            counter++;
            System.out.println(counter + ". " + t);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

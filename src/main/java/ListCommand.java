public class ListCommand extends Command {

    public ListCommand (String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int index = 0;
        System.out.println("Here are the tasks in your list: ");
        for (Task task : tasks.getList()) {
            index++;
            System.out.println(index + ". " + task.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

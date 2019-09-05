public class ListCommand extends Command {
    @Override
    String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }

    @Override
    boolean isExit() {
        return false;
    }
}

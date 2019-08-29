public class ListCommand extends Command {
    String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }

    boolean isExit() {
        return false;
    }
}

public class ByeCommand extends Command {
    @Override
    String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    boolean isExit() {
        return true;
    }
}

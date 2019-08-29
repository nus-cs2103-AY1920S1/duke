public class ByeCommand extends Command {
    String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    boolean isExit() {
        return true;
    }
}

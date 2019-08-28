public class ExitCommand extends Command {

    @Override
    public String run(TodoList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

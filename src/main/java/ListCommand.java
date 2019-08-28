public class ListCommand extends Command {

    @Override
    public String run(TodoList tasks, Storage storage) {
        return tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

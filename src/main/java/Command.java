public abstract class Command {

    public abstract void execute(TaskList toDoList, UI ui, Storage storage);

    public abstract boolean isExit();
}
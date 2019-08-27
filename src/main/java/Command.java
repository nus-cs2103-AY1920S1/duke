public abstract class Command {

    public abstract void execute(TaskList toDoList, UI ui);

    public abstract boolean isExit();
}
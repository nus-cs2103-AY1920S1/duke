package seedu.duke;

public abstract class Command {
    protected static boolean isExit;
    protected static TaskList task;
    protected static Ui ui;
    protected static Storage storage;
    protected static String type;

    public Command() {
        isExit = false;
    }

    public abstract void execute(TaskList t, Ui u, Storage s);

    public static boolean isExit() {
        return isExit;
    }
}

public class ExitCommand extends Command {
    public ExitCommand() {
        super("");
        isExit = true;
    }

    public void execute(TaskList taskList, UI ui, DukeDatabase database) throws DukeException {
        initialise(taskList, ui, database);
        exit();
    }

    /**
     * Clean up the essential components of Duke bot before exiting the program.
     */
    public void exit() {
        ui.echo("Bye. Hope to see you again!");
        database.update(taskList);
    }
}

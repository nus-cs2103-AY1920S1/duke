public class ExitCommand extends Command {
    public ExitCommand(){
        super();
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.messageUser("Bye. Hope to see you again soon!");
    }
}

public class Command {

    public boolean isExit;

    public Command(boolean isExit) {
        this.isExit =  isExit;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {

    }

}

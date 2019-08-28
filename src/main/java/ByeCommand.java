public class ByeCommand extends Command {

    ByeCommand(String commandDesc){
        super(commandDesc);
    }

    boolean isExit(){
        return true;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}

/**
 * This is a class for command "bye".
 * @author Choong Yong Xin
 */

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

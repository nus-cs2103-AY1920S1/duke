import java.io.IOException;

public class HelpCommand extends Command {

    public HelpCommand(){
        this.commandType = CommandType.HELP;
    }

    @Override
    public void execute(TaskList taskList, Ui ui){
        ui.showHelpMessage();
    }
}

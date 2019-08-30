public class ByeCommand extends Command{

    public void runCommand(TaskList taskList, Storage storage, Ui ui){

        ui.printText("Bye. Hope to see you again soon!");
        ui.closeUi();

    }
}
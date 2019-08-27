public class ListTaskCommand extends Command {
    ListTaskCommand(){
        super(2);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.printString(tasks.toString());
    }
}

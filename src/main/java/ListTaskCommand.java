public class ListTaskCommand extends Command {
    /**
     * constructor for the list task command
     */
    ListTaskCommand(){
        super(2);
    }

    /**
     * This method print relevant information for the list
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.printString(tasks.toString());
    }
}

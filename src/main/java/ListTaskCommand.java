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
    public String execute(TaskList tasks, Ui ui, Storage storage){
        return tasks.toString();
    }
}

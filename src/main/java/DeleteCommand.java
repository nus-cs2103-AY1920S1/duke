public class DeleteCommand extends Command {
    int toDelete;
    public DeleteCommand(int toDelete){
        this.toDelete = toDelete;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage){
        try{
            if(toDelete >= taskList.size() || toDelete < 0){
                throw new DukeException("☹ OOPS! Task " + (toDelete + 1) + " doesn't exist!");
            } else {
                Task curr = taskList.get(toDelete);
                ui.printDeleted(curr);
                taskList.deleteTask(toDelete);
                ui.printNumTasks();
                storage.setChangedTrue();
            }
        } catch (DukeException de){
            ui.printTaskError(de.getMessage());
        }
    }

    public boolean isExit(){
        return false;
    }
}

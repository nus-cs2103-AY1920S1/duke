public abstract class Command {
    int actionType; // -1=>null 0=>add 1=>delete 2=>list 3=>exit 4=>done 5=>find
    Task task;
    Command(int actionType){
        this.actionType = actionType;
    }
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    boolean isExit(){
        return actionType == 3;
    }
}

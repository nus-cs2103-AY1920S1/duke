import java.util.ArrayList;

public class FindTaskCommand extends Command {
    String keyWord;
    FindTaskCommand(String keyWord){
        super(5);
        this.keyWord = keyWord;
    }

    void execute(TaskList tasks, Ui ui, Storage storage){
        ArrayList<Task> list = tasks.find(keyWord);
        ui.printList(list);
    }
}

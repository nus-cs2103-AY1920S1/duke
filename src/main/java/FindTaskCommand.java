import java.util.ArrayList;

public class FindTaskCommand extends Command {
    String keyWord;
    FindTaskCommand(String keyWord){
        super(5);
        this.keyWord = keyWord;
    }

    String execute(TaskList tasks, Ui ui, Storage storage){
        assert !this.keyWord.equals(""): "Cannot search for empty string";
        TaskList taskList = tasks.find(keyWord);
        return taskList.toFoundListString();
    }
}

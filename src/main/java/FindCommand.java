import java.io.IOException;

public class FindCommand extends Command {

    String text;

    public FindCommand(String text) {
        this.text = text;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {

        Task[] matchedTasks = taskList.find(text);

        ui.find(matchedTasks);
    }
}

import java.io.FileNotFoundException;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException {
        ui.print("Here are the matching tasks in your list:");
        for (Task t : tasks.getList()) {
            if (t.toString().contains(keyword)) {
                ui.print(t.toString());
            }
        }
    }

}

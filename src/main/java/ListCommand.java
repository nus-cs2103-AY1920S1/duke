import java.io.IOException;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String command) {
        System.out.println("Here are the tasks in your list:");
        tasks.output();
    }
}

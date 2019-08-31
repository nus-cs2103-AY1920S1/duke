import java.io.IOException;

public class DoneCommand extends Command {
    String[] temp;

    public DoneCommand(String[] temp){
        this.temp = temp;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws IOException {
        try {
            int index = Integer.parseInt(temp[1]) - 1;
            ui.printDone(task.getList(), index);
            storage.arrayToFile(task.getList());
        } catch (NullPointerException e) {
            ui.printError("Please input a valid task number.");
        }

    }
}

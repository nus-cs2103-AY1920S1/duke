import java.io.IOException;

public class ByeCommand extends Command{

    public boolean isTerminated() {
        return true;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            storage.saveFile(tasklist);
        } catch (IOException e) {
            ui.sendLine();
            System.out.println("Unable to save data: " + e.getMessage());
            ui.sendLine();
        } finally {
            ui.sendBye();
        }
    }

}

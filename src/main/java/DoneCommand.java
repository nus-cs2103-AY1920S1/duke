import java.io.IOException;

public class DoneCommand extends Command {
    String rawString;
    int taskNum;

    public DoneCommand(String rawString) throws DukeException {
        this.rawString = rawString;
        String[] splited = rawString.split(" ");
        if (splited[1].matches("^[0-9]*[1-9][0-9]*$") && splited.length == 2) {
            taskNum = Integer.parseInt(splited[1]);
        } else {
            throw new DoneParameterException("Invalid parameter! Try the format: done (task number)");
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.done(taskNum);
        try {
            storage.updateState(tasks);
        } catch (IOException ex) {
            Ui.showError("IO exception caught while marking task as done!");
        } catch(UpdateStateException ex) {
            Ui.showError(ex.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
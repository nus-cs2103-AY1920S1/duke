import java.io.IOException;

public class DeleteCommand extends Command {
    String rawString;
    int taskNum;

    public DeleteCommand(String rawString) throws DukeException {
        String[] splited = rawString.split(" ");
        if (splited[1].matches("^[0-9]*[1-9][0-9]*$") && splited.length == 2) {
            taskNum = Integer.parseInt(splited[1]);
        } else {
            throw new DeleteParameterException("Invalid parameter! Try the format: delete (task number)");
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(taskNum);
        try {
            storage.updateState(tasks);
        } catch (IOException ex) {
            Ui.showError("IO exception caught while deleting task!");
        } catch(UpdateStateException ex) {
            Ui.showError(ex.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
import java.util.InputMismatchException;

public class DeleteCommand extends Command {
    private String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task deleted = taskList.deleteTask(Integer.parseInt(index) - 1);
            ui.showDeletedTask(deleted.toString(), taskList.getNumTasks());
        } catch (InputMismatchException e) {
            throw new DukeException("☹ OOPS!!! Please enter a single number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index to delete is not within the task list size.");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please enter a valid number.");
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}

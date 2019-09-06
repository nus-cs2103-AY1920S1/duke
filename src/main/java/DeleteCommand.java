import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class DeleteCommand implements Command {
    private int taskId;
    
    public DeleteCommand(int taskId) {
        super();
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(taskId);
            tasks.deleteTask(task); // mark task as done
        } catch (InputMismatchException e) {
            // user input after "done" is not an int
            System.out.println("Oops! You entered an invalid task ID!");
        } catch (NoSuchElementException e) {
            // user input after "done" is blank
            System.out.println("Oops! You did not enter a task ID!");
        } catch (IndexOutOfBoundsException e) {
            // user input after "done" is an invalid task ID
            System.out.println("Oops! You entered an invalid task ID!");
        }
    }
}

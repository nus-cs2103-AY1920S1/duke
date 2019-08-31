import java.io.IOException;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(String[] fullCommand) {
        if (fullCommand[0].equals("todo")) {
            task = new Todo(fullCommand[1]);
        } else if (fullCommand[0].equals("deadline")) {
            String[] detailsArray = fullCommand[1].split(" /by ", 2);
            task = new Deadline(detailsArray[0], detailsArray[1]);
        } else {
            String[] detailsArray = fullCommand[1].split(" /at ", 2);
            task = new Event(detailsArray[0], detailsArray[1]);
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, ui);
        try {
            storage.store(tasks);
        } catch (IOException e) {
            System.out.println("    ____________________________________________________________\n     OOPS!!! "
                    + e.getMessage()
                    + "\n    ____________________________________________________________\n\n");
        }
    }
}

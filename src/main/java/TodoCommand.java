import java.io.IOException;

public class TodoCommand extends Command {
    boolean isDone;
    String description;

    public TodoCommand(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;

    }

    public void runCommand(TaskList taskList, Storage storage, Ui ui) {
        try {
            storage.appendToFile("T", null, this.description);

            Todo newTodo = new Todo(this.description);

            taskList.addTask(newTodo);

            int numTask = taskList.size();

            ui.printText("Got it. I've added this task: \n" + "  "
                    + newTodo + "Now you have " +
                    numTask + " tasks in the list.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}
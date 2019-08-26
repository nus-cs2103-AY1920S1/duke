import java.util.*;

public class AddCommand extends Command {
    protected Type enumType;
    protected String taskDesc;
    protected String timeDesc;

    public AddCommand(Type enumType, String taskDesc, String timeDesc) {
        this.enumType = enumType;
        this.taskDesc = taskDesc;
        this.timeDesc = timeDesc;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (enumType) {
            case TODO: {
                Task newTask = new Todo(this.taskDesc);
                tasks.add(newTask);
                int numTasks = tasks.size();
                ui.showLine("Got it. I've added this task:" + "\n" + newTask.toString() + "\n" + "Now you have " + numTasks
                        + " tasks in the list.");
            }
            break;

            case DEADLINE: {
                Task newTask = new Deadline(this.taskDesc, this.timeDesc);
                tasks.add(newTask);
                int numTasks = tasks.size();
                ui.showLine("Got it. I've added this task:" + "\n" + newTask.toString() + "\n" + "Now you have "
                        + numTasks + " tasks in the list.");

            }
            break;

            case EVENT:
                Task newTask = new Events(this.taskDesc, this.timeDesc);
                tasks.add(newTask);
                int numTasks = tasks.size();
                System.out.println("Got it. I've added this task:" + "\n" + newTask.toString() + "\n" + "Now you have "
                        + numTasks + " tasks in the list.");

                break;
        }

        storage.save(tasks.getTaskList());
    }

    public boolean isExit() {
        return false;
    }
}

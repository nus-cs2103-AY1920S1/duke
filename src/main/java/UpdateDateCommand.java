import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateDateCommand extends UpdateCommand {

    private String taskDescription;
    private String newDate;

    public UpdateDateCommand(String taskDescription, String newDate) {
        this.taskDescription = taskDescription;
        this.newDate = newDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task;
        try {
            task = tasks.findTask(taskDescription);
            tasks.getList().remove(task);
            Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(newDate);
            task.setDate(date);
            System.out.println(task);
        } catch (TaskNotFoundException e) {
            return e.getMessage();
        } catch (ParseException e) {
            return "Date in wrong format.";
        }
        tasks.getList().add(task);
        storage.writeFile(tasks.getList());
        return toString() + ui.printTask(tasks.getList().size(), task);
    }

    @Override
    public String toString() {
        return "Got it. I've updated this task's date:\n";
    }
}

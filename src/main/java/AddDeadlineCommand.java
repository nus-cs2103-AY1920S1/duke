import java.io.IOException;

public class AddDeadlineCommand extends Command {

    String task;
    String date;
    Deadline deadlineTask;

    public AddDeadlineCommand(String task, String date){
        this.commandType = CommandType.ADDDEADLINE;
        this.task = task;
        this.date = date;
    }

    public AddDeadlineCommand(String task, Date date){
        this.commandType = CommandType.ADDDEADLINE;
        this.task = task;
        this.date = date.toString();
    }


    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        this.deadlineTask = new Deadline(this.task, taskList.getSize() + 1, this.date);
        taskList.addToList(this.deadlineTask);
        ui.printAddDeadlineCommand(this.deadlineTask, taskList);
    }
}

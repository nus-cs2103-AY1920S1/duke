public class AddDeadlineCommand extends Command {

    String task;
    String date;
    Deadline deadlineTask;

    public AddDeadlineCommand(String task, String date){
        this.commandType = CommandType.ADDDEADLINE;
        this.task = task;
        this.date = date;
    }


}

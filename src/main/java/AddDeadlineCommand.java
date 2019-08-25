public class AddDeadlineCommand extends Command {

    String task;
    String date;
    Deadline deadlineTask;

    public AddDeadlineCommand(String task, String date){
        this.commandType = CommandType.ADDDEADLINE;
        this.task = task;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        this.deadlineTask = new Deadline(this.task, taskList.getSize() + 1, this.date);
        taskList.addToList(this.deadlineTask);
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Got it. I've added this task:"));
        System.out.println(Formatter.indentLine("  " + this.deadlineTask));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }
}

public class AddEventCommand extends Command{

    String task;
    String date;
    Event eventTask;

    public AddEventCommand(String task, String date){
        this.commandType = CommandType.ADDEVENT;
        this.task = task;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        this.eventTask = new Event(this.task, taskList.getSize() + 1, this.date);
        taskList.addToList(this.eventTask);
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Got it. I've added this task:"));
        System.out.println(Formatter.indentLine("  " + this.eventTask));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }
}

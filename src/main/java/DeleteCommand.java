import java.text.Normalizer;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index){
        this.commandType = CommandType.DELETE;
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task toRemove = taskList.getTaskByIndex(this.index);
        taskList.removeFromList(this.index);
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Noted. I've removed this task:"));
        System.out.println(Formatter.indentLine("  " + toRemove));
        System.out.println(Formatter.indentLine("Now you have " + taskList.getSize() + " tasks in the list."));
        Formatter.printLine();
    }
}

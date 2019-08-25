import java.text.Format;

public class DoneCommand extends Command{

    int index;

    public DoneCommand(int index){
        this.commandType = CommandType.DONE;
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        if (taskList.getTaskByIndex(this.index).getIsDone()){
            throw new IllegalArgumentException("Task has already been done");
        }
        taskList.setDoneInList(index);
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Nice! I've marked this task as done:"));
        System.out.println(Formatter.indentLine("  " + taskList.getTaskByIndex(index)));
        Formatter.printLine();
    }
}

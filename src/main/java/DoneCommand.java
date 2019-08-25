import java.text.Format;

public class DoneCommand extends Command{

    int index;

    public DoneCommand(int index){
        this.commandType = CommandType.DONE;
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            taskList.getTaskByIndex(this.index);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException("Oh no! Task not found!", DukeExceptionType.TASKNOTFOUND);
        }
        if (taskList.getTaskByIndex(this.index).getIsDone()){
            throw new DukeException("Task has already been done!", DukeExceptionType.TASKALREADYDONE);
        }
        taskList.setDoneInList(this.index);
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Nice! I've marked this task as done:"));
        System.out.println(Formatter.indentLine("  " + taskList.getTaskByIndex(this.index)));
        Formatter.printLine();
    }
}

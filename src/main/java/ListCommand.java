import java.text.Normalizer;

public class ListCommand extends Command {

    public ListCommand(){
        this.commandType = CommandType.LIST;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Formatter.printLine();
        System.out.println(Formatter.indentLine("Here are the tasks in your list:"));
        for (Task task: taskList.getList()){
            System.out.println(Formatter.indentLine(task.id + "." + task.toString()));
        }
        Formatter.printLine();
    }
}

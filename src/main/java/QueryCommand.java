import java.util.ListIterator;

public class QueryCommand extends Command {
    private QueryType queryType;

    public enum QueryType {
        LIST_ALL;
    }

    public QueryCommand(QueryType queryType, String input) {
        super(input);
        this.queryType = queryType;
    }

    public void execute(TaskList tasks, UI ui, DukeDatabase database) throws DukeException{
        initialise(tasks, ui, database);

        if (QueryType.LIST_ALL.equals(queryType)) {
           list();
        } else {
            throw new DukeException("Internal logic bug occurs!");
        }
    }

    // List all the tasks in the taskList.
    private void list() {
        ui.echo(() -> {
            System.out.print(UI.INDENTATION_LVL1 + "Here are the tasks in your list:\n");
            ListIterator<Task> iterator = taskList.listIterator();

            for (int i = 0; i < taskList.size(); i++) {
                String taskDetails = iterator.next().toString();
                System.out.printf(ui.indentAndSplit(String.format("%d.%s", i + 1, taskDetails),
                        UI.INDENTATION_LVL1));
            }
        });
    }
}

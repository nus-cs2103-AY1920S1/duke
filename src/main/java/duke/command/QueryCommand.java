package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;
import duke.support.PrintFunction;
import duke.task.Task;

import java.util.ListIterator;

public class QueryCommand extends Command {
    private QueryType queryType;

    public enum QueryType {
        LIST_ALL, FIND_BY_KEYWORD;
    }

    public QueryCommand(QueryType queryType, String input) {
        super(input);
        this.queryType = queryType;
    }

    public void execute(TaskList tasksList, Ui ui, DukeDatabase database) throws DukeException {
        initialise(tasksList, ui, database);

        if (QueryType.LIST_ALL.equals(queryType)) {
            list();
        } else if (QueryType.FIND_BY_KEYWORD.equals(queryType)) {
            listTasksByKeyword();
        } else {
            throw new DukeException("Internal logic bug occurs!");
        }
    }

    // List all the tasks in the taskList.
    private void list() {
        ui.echo(getPrintListFunction(taskList, "Here are the tasks in your list:"));
    }

    private void listTasksByKeyword() {
        String keyword = input.substring(4).trim();

        TaskList resultList = new TaskList();
        ListIterator<Task> iterator = taskList.listIterator();
        int size = taskList.size();

        for (int i = 0; i < size; i++) {
            Task currTask = iterator.next();
            if (currTask.toString().contains(keyword)) {
                resultList.addTask(currTask);
            }
        }

        ui.echo(getPrintListFunction(resultList,"Here are the matching tasks in your list:"));
    }

    private PrintFunction getPrintListFunction(TaskList list, String message) {
        return () -> {
            System.out.print(Ui.INDENTATION_LVL1 + message + "\n");
            ListIterator<Task> iterator = list.listIterator();

            for (int i = 0; i < list.size(); i++) {
                String taskDetails = iterator.next().toString();
                System.out.printf(ui.indentAndSplit(String.format("%d.%s", i + 1, taskDetails),
                        Ui.INDENTATION_LVL1));
            }
        };
    }
}

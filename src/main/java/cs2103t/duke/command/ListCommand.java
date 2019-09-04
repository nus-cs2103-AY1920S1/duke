package cs2103t.duke.command;

import cs2103t.duke.TaskList;

import java.io.IOException;

/**
 * Encapsulates a list command to list out all the tasks.
 */
public class ListCommand extends Command {
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";

    /**
     * Constructs a cs2103t.duke.command.ListCommand object.
     *
     * @param list input task list.
     */
    public ListCommand(TaskList list) {
        super(list);
    }

    /*
    @Override
    public void execute() {
        printList(super.list);
    }

     */

    public String execute() throws IOException {
        return "ListCommand";
    }

    private static void printList(TaskList list) {
        System.out.println(HORIZONTAL_LINE);
        System.out.print(indentText(LIST_MESSAGE, TEXT_INDENT_LEVEL));
        System.out.println(list);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }
}

package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Handles output formatting and printing.
 */
public class PrettyPrinter {
//    private static final String SEPARATOR_TOP =
//            "    ____________________________________________________________\n";
//    private static final String SEPARATOR_BOTTOM =
//            "    ____________________________________________________________";
//    private static final String ADD_MESSAGE =
//            "    Got it. I've added this duke.task:\n";
//    private static final String DELETE_MESSAGE =
//            "    Noted. I've removed this duke.task:\n";
//    private static final String DONE_MESSAGE =
//            "    Nice! I've marked this duke.task as done:\n";
//
//    public String formatAddTask(Task task, TaskList taskList) {
//        String msg = ADD_MESSAGE
//                + "    " + task + "\n"
//                + (taskList.count() == 1
//                ? String.format("    Now you have %d duke.task in the list.\n", taskList.count())
//                : String.format("    Now you have %d tasks in the list.\n", taskList.count()));
//        return addSeparators(msg);
//    }
//
//    public String formatDeleteTask(Task task, TaskList taskList) {
//        String msg = DELETE_MESSAGE
//                + "    " + task + "\n"
//                + (taskList.count() == 1
//                ? String.format("    Now you have %d duke.task in the list.\n", taskList.count())
//                : String.format("    Now you have %d tasks in the list.\n", taskList.count()));
//        return addSeparators(msg);
//    }
//
//    public String formatTaskList(TaskList taskList) {
//        return addSeparators(formatTaskListHelper(taskList));
//    }
//
//    private String formatTaskListHelper(TaskList taskList) {
//        ArrayList<Task> tasks = taskList.getTaskList();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < tasks.size(); i++) {
//            Task task = tasks.get(i);
//            if (task != null) {
//                sb.append("\t").append(i).append(". ").append(task).append("\n");
//            }
//        }
//        return sb.toString();
//    }
//
//    public String formatDoneTask(Task task) {
//        String msg = DONE_MESSAGE
//                + "    " + task + '\n';
//        return addSeparators(msg);
//    }
//
//    public String addSeparators(String input) {
//        return SEPARATOR_TOP + input + SEPARATOR_BOTTOM;
//    }
//
//    public String addSeparatorsAddIndent(String input) {
//        return SEPARATOR_TOP + "    " + input + '\n' + SEPARATOR_BOTTOM;
//    }
}

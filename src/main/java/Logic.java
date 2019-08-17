import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Logic {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String INDENT = "     ";
    private static final String ADDED_MSG = "added: ";
    private static final String TASK_DONE_MSG = "Nice! I've marked this task as done:";
    private static final String LIST_MSG = "Here are the tasks in your list:";
    private static final String NEED_INDEX_ERROR = "Which task have you done?";
    private static final String ARG_MUST_BE_NUM_ERROR = "Argument must be a number.";
    private static final String TASK_DOESNT_EXIST_ERROR = "Task does not exist.";

    private List<Task> taskList;

    Logic() {
        taskList = new ArrayList<>();
    }

    int process(String str) {
        if (str.length() == 0) {
            return 0;
        }
        String[] strings = str.split(" ");
        String command = strings[0];
        switch (command) {
            case BYE_COMMAND:
                return -1;
            case LIST_COMMAND:
                list();
                break;
            case DONE_COMMAND:
                if (strings.length < 2) {
                    print(NEED_INDEX_ERROR);
                    return 0;
                }
                done(strings[1]);
                break;
            default:
                add(str);
        }
        return 0;
    }

    private void done(String s) {
        try {
            int i = Integer.parseInt(s) - 1;
            Task task = taskList.get(i);
            task.markAsDone();
            print("  " + TASK_DONE_MSG);
            print(task.toString());
        } catch (NumberFormatException e) {
            print(ARG_MUST_BE_NUM_ERROR);
        } catch (IndexOutOfBoundsException e) {
            print(TASK_DOESNT_EXIST_ERROR);
        }
    }

    private void list() {
        print(LIST_MSG);
        for (int i = 0; i < taskList.size(); ++i) {
            print((i + 1) + ". " + taskList.get(i));
        }
    }

    private void add(String s) {
        taskList.add(new Task(s));
        print(ADDED_MSG + s);
    }

    private void print(String s) {
        System.out.println(INDENT + s);
    }
}

import java.util.*;

public class Logic {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

    private static final String INDENT = "     ";
    private static final String ADDED_MSG = "Got it. I've added this task: ";
    private static final String TASK_REMOVED_MSG = "Noted. I've removed this task:";
    private static final String TASK_DONE_MSG = "Nice! I've marked this task as done:";
    private static final String LIST_MSG = "Here are the tasks in your list:";

    private Scanner sc;
    private List<Task> taskList;

    Logic(Scanner sc) {
        this.sc = sc;
        taskList = new ArrayList<>();
    }

    int process(String command) {
        if (command.length() == 0) {
            return 0;
        }

        try {
            switch (command) {
                case TODO_COMMAND:
                    addTodo(sc.nextLine());
                    break;
                case DEADLINE_COMMAND:
                    addDeadline(sc.nextLine());
                    break;
                case EVENT_COMMAND:
                    addEvent(sc.nextLine());
                    break;
                case BYE_COMMAND:
                    return -1;
                case LIST_COMMAND:
                    list();
                    break;
                case DONE_COMMAND:
                    done(sc.nextLine().trim());
                    break;
                case DELETE_COMMAND:
                    delete(sc.nextLine().trim());
                    break;
                default:
                    throw DukeException.UNKNOWN_COMMAND;
            }
        } catch (DukeException e) {
            print(e.getDesc());
        }
        return 0;
    }

    private void done(String s) throws DukeException {
        try {
            if (s.isEmpty()) {
                throw DukeException.TASK_NOT_SPECIFIED;
            }
            int i = Integer.parseInt(s) - 1;
            Task task = taskList.get(i);
            task.markAsDone();
            print(TASK_DONE_MSG);
            print("  " + task.toString());
        } catch (NumberFormatException e) {
            throw DukeException.ARG_MUST_BE_NUM;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.TASK_NOT_EXIST;
        }
    }

    private void delete(String s) throws DukeException {
        try {
            if (s.isEmpty()) {
                throw DukeException.TASK_NOT_SPECIFIED;
            }
            int i = Integer.parseInt(s) - 1;
            Task task = taskList.get(i);
            taskList.remove(i);
            print(TASK_REMOVED_MSG);
            print("  " + task.toString());
            printListSize();
        } catch (NumberFormatException e) {
            throw DukeException.ARG_MUST_BE_NUM;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.TASK_NOT_EXIST;
        }
    }

    private void list() {
        print(LIST_MSG);
        for (int i = 0; i < taskList.size(); ++i) {
            print((i + 1) + ". " + taskList.get(i));
        }
    }

    private void addTodo(String s) throws DukeException{
        if (s.isEmpty()) {
            throw DukeException.TODO_EMPTY_DESC;
        }
        Task task = new Todo(s);
        taskList.add(task);
        printAdded(task);

    }

    private void addDeadline(String s) throws DukeException {
        try {
            if (s.isEmpty()) {
                throw DukeException.DEADLINE_EMPTY_DESC;
            }
            String[] strs = s.split(Deadline.REGEX);
            Task task = new Deadline(strs[0], strs[1]);
            taskList.add(task);
            printAdded(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw DukeException.DEADLINE_EMPTY_ARG;
        }
    }

    private void addEvent(String s) throws DukeException {
        try {
            if (s.isEmpty()) {
                throw DukeException.EVENT_EMPTY_DESC;
            }
            String[] strs = s.split(Event.REGEX);
            Task task = new Event(strs[0], strs[1]);
            taskList.add(task);
            printAdded(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw DukeException.EVENT_EMPTY_ARG;
        }
    }

    private void printAdded(Task task) {
        print(ADDED_MSG);
        print("  " + task);
        printListSize();
    }

    private void print(String s) {
        System.out.println(INDENT + s);
    }

    private void printListSize() {
        print("Now you have " + taskList.size() + " tasks in the list.");
    }
}

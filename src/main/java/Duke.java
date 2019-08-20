import java.util.ArrayList;
import java.util.Scanner;

/**
 * Drives the Duke bot
 * This is the main driver class and entry point.
 */
public class Duke {

    static final String welcomeMsg = "Hello! I'm Duke\n" +
            "What can I do for you?";
    static final String exitMsg = "Bye. Hope to see you again soon!";
    static final ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        CmdInterface.printHBars(welcomeMsg);

        boolean isGoodbye = false;
        while (!isGoodbye) {
            String input;
            try {
                input = sc.nextLine();
                if (input.equals("list")) {
                    listTasks();
                } else if (input.matches("todo.*")) {
                    if (input.equals("todo")) {
                        throw new DukeTodoException();
                    } else if (input.charAt(4) == ' ') {
                        addTodo(input.substring(5));
                    } else {
                        throw new DukeUnknownCommandException();
                    }
                } else if (input.matches("deadline.*")) {
                    if (input.equals("deadline")) {
                        throw new DukeDeadlineException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (input.charAt(8) == ' ') {
                        addDeadline(input.substring(9));
                    } else {
                        throw new DukeUnknownCommandException();
                    }
                } else if (input.matches("event.*")) {
                    if (input.equals("event")) {
                        throw new DukeEventException("☹ OOPS!!! The description of a event cannot be empty.");
                    } else if (input.charAt(5) == ' ') {
                        addEvent(input.substring(6));
                    } else {
                        throw new DukeUnknownCommandException();
                    }
                } else if (input.matches("done\\s\\d+")) {
                    doDoneTask(input);
                } else if (input.equals("bye")) {
                    exitApp();
                    isGoodbye = true;
                } else {
                    throw new DukeUnknownCommandException();
                }
            } catch (DukeException dukeEx) {
                //do nothing
            }

        }
    }

    public static void exitApp() {
        CmdInterface.printHBars(exitMsg);
    }


    /* deprecated
    public static void addTask(String taskName) {
        taskList.add(new Task(taskName));
        CmdInterface.printHBars("added: " + taskName);
    }
     */

    public static void addTodo(String todoName) throws DukeTodoException {
        if (todoName.length() < 1) {
            throw new DukeTodoException();
        } else {
            //main body
            Todo newTodo = new Todo(todoName);
            taskList.add(newTodo);
            printAddSuccess(newTodo);
        }

    }

    public static void addDeadline(String deadlineDetail) throws DukeDeadlineException {
        if (deadlineDetail.length() < 1) {
            throw new DukeDeadlineException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!deadlineDetail.contains("/by")) {
            throw new DukeDeadlineException("☹ OOPS!!! The format of deadline is wrong!");
        } else {
            //main body
            String[] detailSplit = deadlineDetail.split(" /by ");
            Deadline newDeadline = new Deadline(detailSplit[0], detailSplit[1]);
            taskList.add(newDeadline);
            printAddSuccess(newDeadline);
        }
    }

    public static void addEvent(String eventDetail) throws DukeEventException {
        if (eventDetail.length() < 1) {
            throw new DukeEventException("☹ OOPS!!! The description of a event cannot be empty.");
        } else if (!eventDetail.contains("/at")) {
            throw new DukeEventException("☹ OOPS!!! The format of event is wrong!");
        } else {
            //main body
            String[] detailSplit = eventDetail.split(" /at ");
            Event newEvent = new Event(detailSplit[0], detailSplit[1]);
            taskList.add(newEvent);
            printAddSuccess(newEvent);
        }
    }

    public static void doDoneTask(String input) {
        int chosenTaskNo = Integer.parseInt(input.substring(5));
        Task chosenTask = taskList.get(chosenTaskNo - 1);
        chosenTask.setDone(true);
        CmdInterface.printHBars("Nice! I've marked this task as done: \n" +
                "  [✓] " + chosenTask.getTaskName());
    }

    public static void listTasks() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("Here are the tasks in your list:\n");
        for (Task task : taskList) {
            String checkbox = task.isDone() ? "[✓]" : "[✗]";
            sb.append(i++ + ". [" + task.getRepLetter() + "]" + checkbox + " " + task.getTaskName() + "\n");
        }
        CmdInterface.printHBars(sb.toString());
    }

    public static void printAddSuccess(Task task) {
        String checkbox = task.isDone() ? "[✓]" : "[✗]";
        CmdInterface.printHBars("Got it. I've added this task: \n" +
                "  [" + task.getRepLetter() + "]" + checkbox + task.getTaskName() +"\n" +
                "Now you have " + taskList.size() + " tasks in the list.");
    }

}

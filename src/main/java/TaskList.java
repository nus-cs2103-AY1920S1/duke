import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {

    public static ArrayList<Task> myTasks;

    /**
     * Empty task list is created if there is no previous session of Duke, or no tasks in the task list.
     */
    public TaskList() {
        this.myTasks = new ArrayList<>();
    }

    /**
     * New task list is filled with tasks from previous session of Duke.
     *
     * @param arrayList Task list from the previous session of Duke.
     */
    public TaskList(ArrayList<Task> arrayList) {
        myTasks = new ArrayList<>();
        for (Task task : arrayList) {
            myTasks.add(task);
        }
    }

    /**
     * Prints out the task list for the user.
     */
    public void printTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < myTasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + myTasks.get(i));
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints out a message informing user that the task has been marked as done.
     *
     * @param command Command to mark task as done.
     * @param storage Storage to store the updated task list.
     */
    public static void printDone(String command, Storage storage) {
        String[] currArray = command.split("\\s+", 2);
        try {
            checkDone(currArray);
            int currStep = Integer.parseInt(currArray[1]);
            Task currTask = myTasks.get(currStep - 1);
            currTask.markAsDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + currTask);
            System.out.println("    ____________________________________________________________\n");
            try {
                storage.store();
            } catch (IOException e) {
                System.out.println("    ____________________________________________________________\n     OOPS!!! "
                        + e.getMessage() + "\n    ____________________________________________________________\n\n");
            }
        } catch (DukeException error) {
            System.err.println(error.getMessage());
        }
    }

    /**
     * Checks if the task can be marked as done
     *
     * @param currArray Command to mark a project as done split into an array.
     * @throws DukeException If task number is not specified, or task number is out of range.
     */
    public static void checkDone(String[] currArray) throws DukeException {
        if (currArray.length == 1) {
            throw new DukeException("    ____________________________________________________________\n"
                    + "     OOPS!!! Please specify a task number! :-)\n"
                    + "    ____________________________________________________________" + "\n");
        }
        int currStep = Integer.parseInt(currArray[1]);
        if (currStep == 0 || currStep > myTasks.size()) {
            throw new DukeException("    ____________________________________________________________\n"
                    + "     OOPS!!! Your specified task number is out of range :-(\n"
                    + "    ____________________________________________________________" + "\n");
        }
    }

    /**
     * Prints out a message informing user that the task has been deleted.
     *
     * @param command Command to delete a task.
     * @param storage Storage to store the updated list.
     */
    public static void handleDelete(String command, Storage storage) {
        String[] currArray = command.split("\\s+", 2);
        int currStep = Integer.parseInt(currArray[1]);
        String string = (myTasks.get(currStep - 1)).toString();
        myTasks.remove(currStep - 1);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + string);
        printListSize();
        System.out.println("    ____________________________________________________________\n");
        try {
            storage.store();
        } catch (IOException e) {
            System.out.println("    ____________________________________________________________\n     OOPS!!! "
                    + e.getMessage() + "\n    ____________________________________________________________\n\n");
        }
    }

    /**
     * Prints out a message informing user of the current list size.
     */
    public static void printListSize() {
        if (myTasks.size() == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            System.out.println("     Now you have " + myTasks.size() + " tasks in the list.");
        }
    }

    /**
     * Creates tasks based on the given command.
     *
     * @param command Command to create a type of task.
     * @param storage Storage to store the updated list.
     */
    public static void handleTask(String command, Storage storage) {
        String[] extractCommand = command.split("\\s+", 2);
        if (extractCommand[0].equals("todo")) {
            Task currTask = new Todo(extractCommand[1]);
            myTasks.add(currTask);
            printTask(currTask);
            try {
                storage.store();
            } catch (IOException e) {
                System.out.println("    ____________________________________________________________\n     OOPS!!! "
                        + e.getMessage() + "\n    ____________________________________________________________\n\n");
            }
        } else if (extractCommand[0].equals("deadline")) {
            String[] currArray = extractCommand[1].split(" /by ", 2);
            try {
                checkTime(currArray, "deadline");
                Task currTask = new Deadline(currArray[0], currArray[1]);
                myTasks.add(currTask);
                printTask(currTask);
                try {
                    storage.store();
                } catch (IOException e) {
                    System.out.println("    ____________________________________________________________\n     OOPS!!! "
                            + e.getMessage() + "\n    ____________________________________________________________\n\n");
                }
            } catch (DukeException error) {
                System.err.println(error.getMessage());
            }
        } else {
            String[] currArray = extractCommand[1].split(" /at ", 2);
            try {
                checkTime(currArray, "event");
                Task currTask = new Event(currArray[0], currArray[1]);
                myTasks.add(currTask);
                printTask(currTask);
                try {
                    storage.store();
                } catch (IOException e) {
                    System.out.println("    ____________________________________________________________\n     OOPS!!! "
                            + e.getMessage() + "\n    ____________________________________________________________\n\n");
                }
            } catch (DukeException error) {
                System.err.println(error.getMessage());
            }
        }
    }

    /**
     * Prints a message informing user that the task has been added.
     *
     * @param task Task that has been added.
     */
    public static void printTask(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        printListSize();
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Checks if task (deadline or event) has a specified.
     *
     * @param currArray Details of command to create task split into array.
     * @param taskType Task type of deadline or event.
     * @throws DukeException
     */
    public static void checkTime(String[] currArray, String taskType) throws DukeException {
        if (currArray.length == 1) {
            throw new DukeException("    ____________________________________________________________\n"
                    + "     OOPS!!! Your " + taskType + " needs a specific date/time! Please re-enter your "
                    + taskType + " :-)\n" + "    ____________________________________________________________" + "\n");
        }
    }
}
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> myTasks;

    public TaskList() {
        this.myTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arrayList) {
        myTasks = new ArrayList<>();
        for (Task task : arrayList) {
            myTasks.add(task);
        }
    }

    public void printTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < myTasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + myTasks.get(i));
        }
        System.out.println("    ____________________________________________________________\n");
    }

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
                System.out.println("    ____________________________________________________________\n     OOPS!!! " + e.getMessage() + "\n    ____________________________________________________________\n\n");
            }
        } catch (DukeException error) {
            System.err.println(error.getMessage());
        }
    }

    public static void checkDone(String[] currArray) throws DukeException {
        if (currArray.length == 1) {
            throw new DukeException("    ____________________________________________________________\n" + "     OOPS!!! Please specify a task number! :-)\n" + "    ____________________________________________________________" + "\n");
        }
        int currStep = Integer.parseInt(currArray[1]);
        if (currStep == 0 || currStep > myTasks.size()) {
            throw new DukeException("    ____________________________________________________________\n"
                    + "     OOPS!!! Your specified task number is out of range :-(\n"
                    + "    ____________________________________________________________" + "\n");
        }
    }

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
            System.out.println("    ____________________________________________________________\n     OOPS!!! " + e.getMessage() + "\n    ____________________________________________________________\n\n");
        }
    }

    public static void printListSize() {
        if (myTasks.size() == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            System.out.println("     Now you have " + myTasks.size() + " tasks in the list.");
        }
    }


    public static void handleTask(String command, Storage storage) {
        String[] extractCommand = command.split("\\s+", 2);
        if (extractCommand[0].equals("todo")) {
            Task currTask = new Todo(extractCommand[1]);
            myTasks.add(currTask);
            printTask(currTask);
            try {
                storage.store();
            } catch (IOException e) {
                System.out.println("    ____________________________________________________________\n     OOPS!!! " + e.getMessage() + "\n    ____________________________________________________________\n\n");
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
                    System.out.println("    ____________________________________________________________\n     OOPS!!! " + e.getMessage() + "\n    ____________________________________________________________\n\n");
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
                    System.out.println("    ____________________________________________________________\n     OOPS!!! " + e.getMessage() + "\n    ____________________________________________________________\n\n");
                }
            } catch (DukeException error) {
                System.err.println(error.getMessage());
            }
        }
    }

    public static void printTask(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        printListSize();
        System.out.println("    ____________________________________________________________\n");
    }

    public static void checkTime(String[] currArray, String taskType) throws DukeException {
        if (currArray.length == 1) {
            throw new DukeException("    ____________________________________________________________\n" + "     OOPS!!! Your " + taskType + " needs a specific date/time! Please re-enter your " + taskType + " :-)\n" + "    ____________________________________________________________" + "\n");
        }
    }
}
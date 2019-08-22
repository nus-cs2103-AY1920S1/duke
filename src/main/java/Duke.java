import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    public static ArrayList<Task> myList = new ArrayList<>();
    public static int taskCount;

    public static void printWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printClose();
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        printClose();
    }

    public static void printClose() {
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void handleTask(String command) {
        taskCount++;
        String[] extractCommand = command.split("\\s+", 2);
        if (extractCommand[0].equals("todo")) {
            Task currTask = new Todo(extractCommand[1]);
            myList.add(currTask);
            printTask(currTask);
        } else if (extractCommand[0].equals("deadline")) {
            String[] currArray = extractCommand[1].split(" /by ", 2);
            try {
                checkTime(currArray, "deadline");
                Task currTask = new Deadline(currArray[0], currArray[1]);
                myList.add(currTask);
                printTask(currTask);
            } catch (DukeException error) {
                System.err.println(error.getMessage());
            }
        } else {
            String[] currArray = extractCommand[1].split(" /at ", 2);
            try {
                checkTime(currArray, "event");
                Task currTask = new Event(currArray[0], currArray[1]);
                myList.add(currTask);
                printTask(currTask);
            } catch (DukeException error) {
                System.err.println(error.getMessage());
            }
        }
    }

    public static void handleDelete(String command) {
        taskCount--;
        String[] currArray = command.split("\\s+", 2);
        int currStep = Integer.parseInt(currArray[1]);
        String string = (myList.get(currStep - 1)).toString();
        myList.remove(currStep - 1);
        printDelete(string);
    }

    public static void printDelete(String string) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + string);
        printListSize();
        printClose();
    }

    public static void printTask(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        printListSize();
        printClose();     
    }

    public static void printListSize() {
        if (myList.size() == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            System.out.println("     Now you have " + myList.size() + " tasks in the list.");
        }
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + myList.get(i));
        }
        printClose();
    }

    public static void printDone(String command) {
        String[] currArray = command.split("\\s+", 2);
        try {
            checkDone(currArray);
            int currStep = Integer.parseInt(currArray[1]);
            Task currTask = myList.get(currStep - 1);
            currTask.markAsDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + currTask);
            printClose();
        } catch (DukeException error) {
            System.err.println(error.getMessage());
        }
    }

    public static void checkValidCommand(String command) throws DukeException {
        ArrayList<String> validCommands = new ArrayList<>();
        validCommands.add("todo");
        validCommands.add("deadline");
        validCommands.add("event");
        validCommands.add("done");
        validCommands.add("list");
        validCommands.add("bye");
        validCommands.add("delete");
        if (!validCommands.contains(command)) {
            throw new DukeException("    ____________________________________________________________\n" + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + "    ____________________________________________________________\n" + "\n");
        }
    }

    public static void checkEmptyDesc(String[] currArray) throws DukeException {
        ArrayList<String> taskTypes = new ArrayList<>();
        taskTypes.add("todo");
        taskTypes.add("deadline");
        taskTypes.add("event");
        if (taskTypes.contains(currArray[0]) && currArray.length == 1) {
            throw new DukeException("    ____________________________________________________________\n" + "     ☹ OOPS!!! The description of a " + currArray[0] + " cannot be empty.\n" + "    ____________________________________________________________\n" +"\n");
        }
    }

    public static void checkDone(String[] currArray) throws DukeException {
        if (currArray.length == 1) {
            throw new DukeException("    ____________________________________________________________\n" + "     ☹ OOPS!!! Please specify a task number! :-)\n" + "    ____________________________________________________________" + "\n");
        } 
        int currStep = Integer.parseInt(currArray[1]);
        if (currStep == 0 || currStep > taskCount) {
            throw new DukeException("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! Your specified task number is out of range :-(\n"
                    + "    ____________________________________________________________" + "\n");
        }
    }

    public static void checkTime(String[] currArray, String taskType) throws DukeException {
        if (currArray.length == 1) {
            throw new DukeException("    ____________________________________________________________\n" + "     ☹ OOPS!!! Your " + taskType + " needs a specific date/time! Please re-enter your " + taskType + " :-)\n" + "    ____________________________________________________________" + "\n");        
        }
    }    

    public static void main(String[] args) {
        printWelcome();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine().trim();
            String[] currArray = currLine.split("\\s+");
            try {
                checkValidCommand(currArray[0]);
                checkEmptyDesc(currArray);
                if (currArray[0].equals("bye")) {
                    printBye();
                    sc.close();
                    System.exit(0);
                } else if (currArray[0].equals("list")) {
                    printList();
                } else if (currArray[0].equals("done")) {
                    printDone(currLine);
                } else if (currArray[0].equals("delete")) {
                    handleDelete(currLine);
                } else {
                    handleTask(currLine);
                }
            } catch (DukeException error) {
                System.err.println(error.getMessage());
            }
        }
    }
}

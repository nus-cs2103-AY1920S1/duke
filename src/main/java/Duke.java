import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    //Constants
    private static final String MESSAGE_PADDING  = "     ";
    private static final String MESSAGE_BOUNDARY = "    ____________________________________________________________";

    //Class Variables
    private final List<Task> taskList;

    /**
     * Constructor for the class Duke
     */
    //Constructor
    public Duke() {
        this.taskList = new ArrayList<>();
    }

    /**
     * @param responseHeader A message to the user.
     */
    //#region [Print Response Helper Functions]
    private void printResponse(String responseHeader) {
        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.print(MESSAGE_PADDING);

        System.out.println(responseHeader);

        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println();
    }

    /**
     * @param responseHeader A message to the user.
     * @param listOfTasks The list of tasks to be displayed to the user.
     */
    private void printResponse(String responseHeader, List<Task> listOfTasks) {
        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.print(MESSAGE_PADDING);
        System.out.println(responseHeader);

        if (listOfTasks != null) {
            int indexOfTask = 0;
            for (Task currentTask : listOfTasks) {

                indexOfTask += 1;
                System.out.print(MESSAGE_PADDING);
                System.out.print(String.format("%d.", indexOfTask));
                System.out.println(currentTask);
            }
        }

        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println();
    }

    /**
     * @param responseHeader A message to the user.
     * @param refTask The task to be displayed to the user.
     * @param displayNumOfTasks Display number of task(s) in task list
     */
    private void printResponseSingleTask(String responseHeader, Task refTask, boolean displayNumOfTasks) {
        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.print(MESSAGE_PADDING);
        System.out.println(responseHeader);

        System.out.print(MESSAGE_PADDING);
        System.out.print("  ");
        System.out.println(refTask);

        if (displayNumOfTasks) {
            System.out.print(MESSAGE_PADDING);
            System.out.println(String.format(
                    "Now you have %d task%s in the list.",
                    this.taskList.size(),
                    (this.taskList.size() > 1) ? "s" : ""));
        }

        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println();
    }
    //#endregion [Print Response Helper Functions]

    /**
     * @param in The query to process.
     * @return The task that was marked as done.
     * @throws DukeException representing any checked exceptions
     */
    //#region [Business Logic]
    private Task markTaskAsDone(Scanner in) throws DukeException{

        if (!in.hasNextInt()) {
            throw new DukeIllegalArgumentException("Task reference number needs to be an integer");
        }

        int taskIndexRef = in.nextInt();
        if (in.hasNext()) {
            throw new DukeIllegalArgumentException("Too many arguments for the 'mark as done' command");
        }

        if (0 < taskIndexRef && taskIndexRef <= this.taskList.size()) {
            Task taskRef = this.taskList.get(taskIndexRef - 1);
            taskRef.markAsDone();
            return taskRef;
        }

        throw new DukeInvalidCommandException("No such task was found");
    }

    /**
     * @param in The query to process.
     * @return The task that was deleted.
     * @throws DukeException representing any checked exceptions
     */
    //#region [Business Logic]
    private Task deleteTask(Scanner in) throws DukeException{

        if (!in.hasNextInt()) {
            throw new DukeIllegalArgumentException("Task reference number needs to be an integer");
        }

        int taskIndexRef = in.nextInt();
        if (in.hasNext()) {
            throw new DukeIllegalArgumentException("Too many arguments for the 'delete task' command");
        }

        if (0 < taskIndexRef && taskIndexRef <= this.taskList.size()) {
            Task taskRef = this.taskList.get(taskIndexRef - 1);
            this.taskList.remove(taskIndexRef - 1);
            return taskRef;
        }

        throw new DukeInvalidCommandException("No such task was found");
    }

    /**
     * @param query A query from the user.
     * @return A boolean representing whether Duke should continue the chat.
     */
    private boolean giveResponse(String query) {

        boolean shouldContinueChat = true;
        Scanner in = new Scanner(query);

        Task newTask = null;
        try {
            if (!in.hasNext()) {
                throw new DukeException("Query should not be empty");
            }

            //Try to parse User's query
            String command = in.next();
            switch(command) {
                case "todo":
                    newTask = ToDo.parse(in);
                    break;

                case "deadline":
                    newTask = Deadline.parse(in);
                    break;

                case "event":
                    newTask = Event.parse(in);
                    break;

                case "list":
                    printResponse("Here are the tasks in your list:", this.taskList);
                    break;

                case "done":
                    Task completedTask = markTaskAsDone(in);
                    printResponseSingleTask("Nice! I've marked this task as done:",
                            completedTask, false);
                    break;

                case "delete":
                    Task deletedTask = deleteTask(in);
                    printResponseSingleTask("Noted. I've removed this task:",
                            deletedTask, true);
                    break;

                case "bye":
                    shouldContinueChat = false;
                    printResponse("Bye. Hope to see you again soon!");
                    break;

                default:
                    throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            //Info user that an error occurred
            printResponse("\u2639 OOPS!!! " + ex.getMessage());
        } finally {
            //Clean up
            in.close();
        }

        //If a new Task is created, handle it here
        if (newTask != null) {
            taskList.add(newTask);
            printResponseSingleTask("Got it. I've added this task:", newTask, true);
        }

        return shouldContinueChat;
    }
    //#endregion [Business Logic]


    /**
     * To run Duke's program
     */
    public void spin() {
        boolean continueChat;
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object

        printResponse("Hello! I'm Duke\n" + MESSAGE_PADDING + "What can I do for you?");

        do {
            //Get query from user
            String userQuery = myScanner.nextLine();

            //Find and give Response
            continueChat = giveResponse(userQuery);

        } while (continueChat);
        myScanner.close();
    }


    public static void main(String[] args) {
        Duke myObj = new Duke();
        myObj.spin();
    }
}

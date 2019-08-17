import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    //Constants
    private static final String MESSAGE_PADDING  = "     ";
    private static final String MESSAGE_BOUNDARY = "    ____________________________________________________________";

    //Class Variables
    private List<Task> taskList;

    //Constructor
    public Duke() {
        this.taskList = new ArrayList<Task>();
    }

    //#region [Print Response Helper Functions]
    private void printResponse(String responseHeader) {
        System.out.println(MESSAGE_BOUNDARY);
        System.out.print(MESSAGE_PADDING);
        System.out.println(responseHeader);
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println("");
    }

    private void printResponse(String responseHeader, List<Task> listofTasks) {
        System.out.println(MESSAGE_BOUNDARY);
        System.out.print(MESSAGE_PADDING);
        System.out.println(responseHeader);

        if (listofTasks != null) {
            int indexofTask = 0;
            for (Task mytask : listofTasks) {

                indexofTask += 1;
                System.out.print(MESSAGE_PADDING);
                System.out.print(String.format("%d.", indexofTask));
                System.out.println(mytask);
            }
        }

        System.out.println(MESSAGE_BOUNDARY);
        System.out.println("");
    }

    private void printResponseSingleTask(String responseHeader, Task refTask) {
        System.out.println(MESSAGE_BOUNDARY);
        System.out.print(MESSAGE_PADDING);
        System.out.println(responseHeader);

        System.out.print(MESSAGE_PADDING);
        System.out.print("  ");
        System.out.println(refTask);

        System.out.println(MESSAGE_BOUNDARY);
        System.out.println("");
    }
    //#endregion [Print Response Helper Functions]

    //#region [Business Logic]
    private Task tryMarkTaskAsDone(String query) {
        Scanner in = new Scanner(query);
        String command = in.next();
        int taskIndexRef = in.nextInt();
        in.close();

        if (command.equals("done") && 0 < taskIndexRef && taskIndexRef <= this.taskList.size()) {
            this.taskList.get(taskIndexRef - 1).markAsDone();
            return this.taskList.get(taskIndexRef - 1);
        }

        return null;
    }

    private boolean shouldContinueChat(String query) {
        return !query.equals("bye");
    }

    private boolean giveResponse(String query) {
        if (!shouldContinueChat(query)) {
            printResponse("Bye. Hope to see you again soon!");
            return false;

        } else if (query.equals("list")) {
            printResponse("Here are the tasks in your list:", this.taskList);

        } else if (query.startsWith("done ")) {
            Task taskRef = tryMarkTaskAsDone(query);
            if (taskRef == null) {
                printResponse("Invalid Query");
            } else {
                printResponseSingleTask("Nice! I've marked this task as done:", taskRef);
            }

        } else {
            Task newTask = new Task(query);
            this.taskList.add(newTask);
            printResponseSingleTask("added: ", newTask);
        }
        return true;
    }
    //#endregion [Business Logic]


    public void spin() {
        boolean continueChat = true;
        Scanner myscanner = new Scanner(System.in);  // Create a Scanner object

        printResponse("Hello! I'm Duke\n" + MESSAGE_PADDING + "What can I do for you?");

        do {
            //Get query from user
            String userQuery = myscanner.nextLine();

            //Find and give Response
            continueChat = giveResponse(userQuery);

        } while (continueChat);
        myscanner.close();
    }


    public static void main(String[] args) {
        Duke myObj = new Duke();
        myObj.spin();
    }
}

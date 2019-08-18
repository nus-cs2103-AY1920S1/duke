import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private ArrayList<Task> taskArr = new ArrayList<Task>();

    public Duke() {
        /*
        description: Constructor for duke, greets user.
        expects: No input
        outputs: returns nothing
         */
        printLine();
        System.out.println("Hello, I'm Duke\nWhat can I do for you?");
        printLine();
    }
    private void printLine() {
        /*
        description: Prints line of dashes with \n at end.
        looks nice tee hee
        expects: No input
        outputs: returns nothing
         */
        System.out.println("____________________________________________________________");
    }
    private String list() {
        /*
        description: stores all tasks in printable format
        then returns it.
        expects: nothing, but taskArr must exist
        outputs: printable string that is all tasks.
         */
        String s = "Here are the tasks in your list:\n";

        for (int i = 0; i < taskArr.size(); i++) {
            // printInt to put number for printing
            int printInt = i + 1;
            Task currTask = taskArr.get(i);
            s += currTask.printTask() + "\n";
        }
        return s;
    }
    private void todo(String taskInfo) {
        /*

         */
        ToDo newToDo = new ToDo(taskInfo,"T");
        taskArr.add(newToDo);
        System.out.println("Got it. I've added this task:");
        System.out.print(" " + newToDo.printTask() + "\n");
        System.out.println("Now you have " + taskArr.size() + " tasks in the list.");
    }
    private void run() {
        /*
        Main method for duke, will run until
        bye is read, then will exit
        expects: No input
        outputs: returns nothing
         */
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                // list all tasks
                printLine();
                System.out.print(list());
                printLine();
            } else if (input.equals("todo")) {
                // basic
                String info = sc.nextLine();
                printLine();
                todo(info);
                printLine();
            } else if (input.equals("deadline")) {
                continue;
            } else if (input.equals("event")) {   // list command

                continue;
            } else if (input.equals("done")) {   // list command
                int taskNum = sc.nextInt();
                printLine();
                done(taskNum);
                printLine();
            } else if (input.equals("delete")) {
                // add skeleton, for now just skips
                continue;
            } else {
                // handle all other cases
                printLine();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printLine();
            }
            input = sc.next();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    private void done(int t) {
        Task doneTask = taskArr.get(t-1);
        doneTask.markDone();
        System.out.print("Nice! I've marked this task as done:\n");
        System.out.println(doneTask.printTask());

    }
    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}

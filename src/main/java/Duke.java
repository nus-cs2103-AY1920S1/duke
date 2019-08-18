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
        String s = new String();

        for (int i = 0; i < taskArr.size(); i++) {
            // printInt to put number for printing
            int printInt = i + 1;
            Task currTask = taskArr.get(i);
            String taskStatus = "[" + currTask.getStatus() + "] ";
            s += printInt + "." + taskStatus + currTask.getTaskInfo() + "\n";
        }
        return s;
    }
    private void add(String taskInfo) {
        /*
        description: creates task, then
        will store task to taskArr
        then increment c
        then print added:....
        expects: String that is task
        outputs: returns nothing
         */
        Task newTask = new Task(taskInfo);
        taskArr.add(newTask);
        System.out.println("added: "+taskInfo);
    }
    private void run() {
        /*
        description: Main method for duke, will run until
        bye is read, then will exit
        expects: No input
        outputs: returns nothing
         */
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {   // list command
                printLine();
                System.out.print(list());
                printLine();
                input = sc.nextLine();
                continue;
            }
            if (input.substring(0,4).equals("done")) {   // list command
                int numIndex = input.length() - 1;
                int taskNum = Character.getNumericValue(input.charAt(numIndex));
                printLine();
                done(taskNum);
                printLine();
                input = sc.nextLine();
                continue;
            }
            printLine();   // if no default is add command
            add(input);
            printLine();
            input = sc.nextLine();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    private void done(int t) {
        Task doneTask = taskArr.get(t-1);
        doneTask.markDone();
        System.out.print("Nice! I've marked this task as done:\n");
        System.out.println("[" + doneTask.getStatus() + "] " + doneTask.getTaskInfo());

    }
    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}

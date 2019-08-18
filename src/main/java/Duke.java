import java.util.Scanner;

public class Duke {
    String[] taskArr = new String[102]; // c keeps track of current empty space
    int c = 1; // reason: print from 1

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

        for (int i = 1; i < c; i++) {
            s += i+". "+taskArr[i]+"\n";
        }
        return s;
    }
    private void add(String task) {
        /*
        description: will store task to taskArr,
        then increment c
        then print added:....
        expects: String that is task
        outputs: returns nothing
         */
        taskArr[c] = task;
        c++;
        System.out.println("added: "+task);
    }
    private void  run() {
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

            printLine();   // if no default is add command
            add(input);
            printLine();
            input = sc.nextLine();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.lang.StringBuilder;

public class Duke {
    private List<Task> list; // List of all tasks

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.start();
    }

    private Duke() {
        this.list = new ArrayList<Task>();
    }

    /*
     Main driver function for the program -
     Greets user, then repeatedly takes in
     user input and processes them.
    */
    private void start() {
        boolean notShutdown = true;
        Scanner sc = new Scanner(System.in);

        this.greetHello();
        do {
            String input = sc.nextLine();
            String firstWord = input.split(" ", 2)[0];
            switch (firstWord) {
            case "bye":
                notShutdown = false;
                break;
            case "list":
                this.printList();
                break;
            case "done":
                String secondWord = input.split(" ")[1];
                int taskIndex = Integer.parseInt(secondWord);
                this.markTaskAsDone(taskIndex);
                break;
            default:
                this.addToList(input);
            }
        } while (notShutdown);
        this.greetGoodbye();
    }

    private void markTaskAsDone(int taskIndex) {
        if (taskIndex < 0 || taskIndex > this.list.size()) {
            this.formattedPrintln("Hey! There's no such task!\n");
            return;
        }
        taskIndex--; // convert to zero-indexing
        this.list.get(taskIndex).markAsDone();

        this.formattedPrintln("Nice! I've marked this task as done:\n  "
                + this.list.get(taskIndex));
    }

    // Add a description to the current list
    private void addToList(String description) {
        Task t = new Task(description);
        this.list.add(t);
        this.formattedPrintln("added: " + description);
    }

    // Print out all tasks in the current list
    private void printList() {
        if (list.size() == 0) {
            this.formattedPrintln("Hey! There's nothing in your list!");
        } else {
            int index = 1;
            Iterator iter = this.list.iterator();
            StringBuilder output = new StringBuilder();

            output.append("Here are the tasks in your list:\n");
            while (iter.hasNext()) {
                output.append("  ");
                output.append(index);
                output.append(".");
                output.append(iter.next());
                output.append("\n");
                index++;
            }
            this.formattedPrint(output.toString());
        }
    }

    /*
     Prints the target string between two horizontal
     bars. Adds a newline to the input string
     before printing.

     @param output  The string to be printed
    */
    private void formattedPrintln(String output) {
        System.out.println("____________________________________________________________\n"
                + output
                + "\n"
                + "____________________________________________________________\n");
    }

    /*
     Prints the target string between two horizontal
     bars. Newline is not added to input string.

     @param output  The string to be printed
    */
    private void formattedPrint(String output) {
        System.out.println("____________________________________________________________\n"
                + output
                + "____________________________________________________________\n");
    }

    /*
     Prints out a formatted hello greeting on the
     screen. It is a implemented as a thin wrapper
     around duke.formattedPrintln()
    */
    private void greetHello() {
        String hello = "Hello! I'm Duke\n"
                + "What can I do for you?";
        this.formattedPrintln(hello);
    }

    /*
     Prints out a formatted goodbye greeting on the
     screen. It is a implemented as a thin wrapper
     around duke.formattedPrintln()
    */
    private void greetGoodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        this.formattedPrintln(goodbye);
    }
}
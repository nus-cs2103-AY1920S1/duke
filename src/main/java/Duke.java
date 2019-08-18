import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greetHello();

        String input;
        // Run input loop
        while (!(input = sc.next()).equals("bye")) {
            echo(input);
        }

        greetBye();
    }

    public static void printOutput(String output) {
        String line = "    ____________________________________________________________\n";

        // Indent and process output line
        output = "      " + output.replaceAll("\n","\n      ") + '\n';

        System.out.println(line + output + line);
    }


    public static void greetHello() {
        printOutput("Hello I'm Duke\nWhat can I do for you?");
    }

    public static void greetBye() {
        printOutput("Bye. Hope to see you again soon!");
    }

    public static void echo(String echoInput) {
        printOutput(echoInput);
    }
}

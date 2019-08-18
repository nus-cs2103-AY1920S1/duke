import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        greetUser();
        drawLine();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String userInput = input.next();
            if (userInput.equals("bye")) {
                sayBye();
                input.close();
                break;
            } else {
                echo(userInput);
            }
        }
    }

    public static void sayBye() {
        drawLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        drawLine();
    }
    public static void echo(String input) {
        drawLine();
        System.out.println("\t " + input);
        drawLine();
    }
    public static void printHeading(String heading) {
        System.out.println("\n" + heading);
    }

    public static void drawLine() {
        System.out.println("\t---------------------------------------");
    }

    public static void greetUser() {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
    }
}

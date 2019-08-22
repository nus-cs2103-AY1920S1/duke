import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printGreetingMessage();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();

            switch(input) {
                case "bye":
                    printExitMessage();
                    return;

                default:
                    printDefaultMessage(input.toLowerCase());
                    break;
            }
        }
    }


    public static void addBorder(String input) {

        String border = "__________________________________________________________";

        System.out.print(border + "\n\n" + input + "\n" + border + "\n\n");
    }


    public static void printGreetingMessage() {
        addBorder("Hello! I'm Duke\n" + "What can I do for you?");
    }


    public static void printDefaultMessage(String input) {
        addBorder(input);
    }

    public static void printExitMessage() {
        addBorder("Bye. Hope to see you again soon!");
    }

}
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in); //gets commands from user
        String userCommand = sc.nextLine();

        //if command is not bye, echo userCommand
        while (!userCommand.equals("bye")) {
            System.out.println(userCommand);
            userCommand = sc.nextLine();
        }

        //program runs this command when userCommand is bye
        System.out.println("Bye. Hope to see you again soon!");
    }
}

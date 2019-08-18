import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        readInput();
        goodbye();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void readInput() {
        Scanner input = new Scanner(System.in);

        String userInput = input.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = input.nextLine();
        }
    }
}

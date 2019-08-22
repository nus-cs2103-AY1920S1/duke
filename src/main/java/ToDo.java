import java.util.Scanner;

public class ToDo {

    public ToDo() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String nextCommand = sc.nextLine();
        while (!nextCommand.equals("bye")) {
            switch (nextCommand) {
                case "list":
                    System.out.println("list");
                    nextCommand = sc.nextLine();
                    break;
                default:
                    System.out.println(nextCommand);
                    nextCommand = sc.nextLine();
            }
        }

        System.out.println("Bye! Hope to see you again soon!");
    }
}

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Task[] data = new Task[100];
        int x = 0;

        while (!input.equals("bye")) {

            if (input.startsWith("done")) {
                int index = Integer.parseInt(input.substring(5));
                data[index-1].markAsDone();
                printDone(data[index-1]);
                input = sc.nextLine();
            } else {
                switch (input) {
                    case "list":
                        printList(data);
                        input = sc.nextLine();
                        break;
                    default:
                        data[x] = new Task(input);
                        x++;
                        echo("Added: " + input);
                        input = sc.nextLine();
                        break;
                }
            }
        }

        exit();
    }

    public static void greet() {
        echo("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(String s) {
        String[] arr = s.split("\n");
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        for (String str : arr) {
            System.out.println("     " + str);
        }
        System.out.println(indentedline);
    }

    public static void printList(Object[] array) {
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        System.out.println("     Here are the tasks in your list:");
        int y = 0;
        while (array[y] != null) {
            System.out.println("     " + (y+1) + ". " + array[y]);
            y++;
        }
        System.out.println(indentedline);
    }

    public static void printDone(Task t) {
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + t);
        System.out.println(indentedline);

    }
}

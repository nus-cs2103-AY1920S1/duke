import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        Scanner sc = new Scanner(System.in);
        Tasks tasks = new Tasks();
        String input = sc.next();
        while(!input.equals("bye")) {
            switch (input) {
                case "list":
                    tasks.list();
                    break;
                case "done":
                    tasks.done(sc.nextInt());
                    break;
                default :
                    tasks.add(input + sc.nextLine());
                    break;
            }
            input = sc.next();
        }
        bye();
    }
    private static void greet() {
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ------------------------------------------------------------");
    }

    private static void bye() {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ------------------------------------------------------------");
    }

}

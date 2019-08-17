import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String query = sc.next();
        while(!query.equals("bye")) {
            echo(query);
            query = sc.next();
        }
        exit();
    }

    public static void echo(String str) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t"+str);
        System.out.println("\t____________________________________________________________");

    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        echo("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    public static void exit() {
        echo("Bye. Hope to see you again soon!");
    }
}

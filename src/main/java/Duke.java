import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        String line = "    ________________________" 
            + "____________________________________";
        System.out.println(line);
        System.out.println("    Hello from\n" + logo);
        System.out.println("    What can I do for you?");
        System.out.println(line + "\n");
        Scanner sc = new Scanner(System.in);
        while (true) {
          String input = sc.next();
          if (input.equals("bye")) {
            echo("Bye. Hope to see you again soon!");
            break;
          }
          echo(input);
        }
    }
    public static void echo(String s) {
        String line = "    ________________________" 
            + "____________________________________";
        System.out.println(line);
        System.out.println("    " + s);
        System.out.println(line + "\n");
        return;
    }
}

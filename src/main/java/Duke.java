import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        //System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> todolist = new ArrayList<String>();

        while (scanner.hasNext()) {
            String request = scanner.nextLine();

            System.out.println("____________________________________________________________");
            if (request.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                return ;
            } else if (request.equals("list")) {
                for (int i = 0; i < todolist.size(); i++) {
                    String todo = String.format("%d. %s", i+1, todolist.get(i));
                    System.out.println(todo);
                }
            } else {
                todolist.add(request);
                String add = String.format("added: %s", request);
                System.out.println(add);
            }

            System.out.println("____________________________________________________________");
        }
    }
}

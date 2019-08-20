import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Greet();
        Detecting();
    }
    public static void Greet(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }
    public static void Detecting() {
        Scanner sc = new Scanner(System.in);
        List<String> toDo = new LinkedList<>();
        List<String> done = new LinkedList<>();
        while(true) {
            String cmd = sc.nextLine();
            Scanner cmdSc = new Scanner(cmd);
            if (cmd.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmdSc.next().toLowerCase().equals("done")) {
                int numDone = Integer.parseInt(cmdSc.next()) - 1;
                done.set(numDone, "V");
                System.out.println("Nice! I've marked this task as done:\n[V] " + toDo.get(numDone));
            } else {
                switch (cmd.toLowerCase()) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 1; i <= toDo.size(); i++) {
                            System.out.println(i + ". " + "[" + done.get(i-1) + "] " + toDo.get(i-1));
                        }
                        break;

                    default:
                        toDo.add(cmd);
                        done.add("X");
                        System.out.println("added: " + cmd);
                }
            }
        }
    }
}


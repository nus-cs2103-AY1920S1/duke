import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        Duke d = new Duke();
        d.greeting();
        d.listen();
    }

    private Duke() {}

    private void greeting() {
        System.out.println("Hello! I'm Duke");
    }

    private ArrayList<Task> cache = new ArrayList<>();
    private void listen() {
        Scanner listener = new Scanner(System.in);
        while(listener.hasNext()) {
            String line = listener.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (line.equals("list")) {
                showList();
            } else if (line.length() >= 6 && line.substring(0,5).equals("done ")) {
                try {
                    Task t = cache.get(Integer.parseInt(line.substring(5))-1);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                } catch (NullPointerException e) {
                    System.out.println("Hey bro why no number?");
                } catch (Exception e) {
                    System.out.println("The format is: 'done <number>'. Please try again.");
                }
            } else {
                cache.add(new Task(line)); 
                System.out.printf("added: %s\n", line);
            }
        }
    }

    private void showList() {
        for (int i=0; i<cache.size(); i++) {
            Task t = cache.get(i);
            System.out.printf("%d.[%s] %s\n", i+1, t.getStatusIcon(), t.getDesc());
        }
    }
}

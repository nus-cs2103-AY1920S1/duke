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

    private ArrayList<String> cache = new ArrayList<>();
    private void listen() {
        Scanner listener = new Scanner(System.in);
        while(listener.hasNext()) {
            String line = listener.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else {

                switch (line) {
                    case "list":
                        for (int i=0; i<cache.size(); i++) {
                            System.out.printf("%d. %s\n", i+1, cache.get(i));
                        }
                        break;
                    default: 
                        cache.add(line); 
                        System.out.printf("added: %s\n", line);
                        break;
                }
            }
        }
    }
}

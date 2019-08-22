import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storage = new ArrayList<>();
        String in;
inputloop:
        while(true){
            in = sc.next();
            switch(in){
                case "bye":
                    System.out.println("Bye!");
                    break inputloop;

                case "list":
                    for(int i = 1; i <= storage.size(); i++) {
                        Task t = storage.get(i-1);
                        System.out.println(String.format(
                            "%d. %s", i, t.toString()));
                    }
                    break;

                case "done":
                    Task t = storage.get(sc.nextInt()-1);
                    t.markDone();
                    System.out.println(String.format(
                        "Nice! I've marked this task as done:\n  %s",
                            t.toString()));
                    break;

                default:
                    System.out.println("added: " + in);
                    storage.add(new Task(in));
            }
        }
    }
}

import java.util.*;

public class Duke {
    private static ArrayList<Task> storage = new ArrayList<>();
    private static void addTask(Task t){
        System.out.println("Got it. I've added this task:\n" + 
            t.toString());
        storage.add(t);
        System.out.println("Now you have " + storage.size() +
            " tasks in your list.");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String in;
inputloop:
        while(true){
            in = sc.next();
            switch(in){
                case "bye":
                    System.out.println("Bye!");
                    break inputloop;

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 1; i <= storage.size(); i++) {
                        Task t = storage.get(i-1);
                        System.out.println(String.format(
                            "%d. %s", i, t.toString()));
                    }
                    break;

                case "done": {
                    Task t = storage.get(sc.nextInt()-1);
                    t.markDone();
                    System.out.println(String.format(
                        "Nice! I've marked this task as done:\n  %s",
                            t.toString()));
                    break;
                }

                case "todo":
                    addTask(new ToDo(sc.nextLine().trim()));
                    break;

                case "deadline": {
                    String[] details = sc.nextLine().trim().split("/by");
                    addTask(new Deadline(details[0].trim(), details[1].trim()));
                    break;
                }

                case "event": {
                    String[] details = sc.nextLine().trim().split("/at");
                    addTask(new Event(details[0].trim(), details[1].trim()));
                    break;
                }

                default:
                    System.out.println("added: " + in);
                    storage.add(new Task(in));
            }
        }
    }
}

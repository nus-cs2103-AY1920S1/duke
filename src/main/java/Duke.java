import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String greeting = "Hello! I'm Duke\n" +
                          "What can I do for you?";
        System.out.println(greeting);

        ArrayList<Task> tasks = new ArrayList<Task>();
        boolean ok = true;
        while(ok){
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            String arr[] = command.split(" ", 2);
            switch(arr[0]) {
                case "list":
                    if(arr.length > 1) {continue;};
                    System.out.println("Here are the tasks in your list:");
                    int counter = 0;
                    for(Task t : tasks) {
                        counter ++;
                        System.out.println(counter + ". " + t.toString());
                    }
                    break;
                case "blah":
                    if(arr.length > 1) {continue;};
                    System.out.println("blah");
                    break;
                case "bye":
                    if(arr.length > 1) {continue;};
                    System.out.println("Bye. Hope to see you again soon!");
                    ok = false;
                    break;
                case "done":
                    if(arr.length > 2) {continue;};
                    int index = Integer.parseInt(arr[1]) - 1;
                    tasks.get(index).markDone();
                    String prompt = "Nice! I've marked this task as done:\n" +
                            "    " + tasks.get(index).toString();
                    System.out.println(prompt);
                    break;
                default:
                    Task t = new Task("");
                    switch(arr[0]) {
                        case "todo":
                            t = new Todo(arr[1]);
                            break;
                        case "deadline":
                            String ddl[] = arr[1].split("/by ", 2);
                            t = new Deadline(ddl[0], ddl[1]);
                            break;
                        case "event":
                            String evt[] = arr[1].split("/at ", 2);
                            t = new Event(evt[0], evt[1]);
                            break;
                        default:
                    }
                    tasks.add(t);
                    String output = "Got it. I've added this task: \n" +
                                    "    " + t.toString() + "\n" +
                                    "Now you have " + tasks.size() + " tasks in the list.";
                    System.out.println(output);
            }
        }
    }
}

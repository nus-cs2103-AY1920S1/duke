import java.util.*;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

public class Duke {

    private static ArrayList<Task> list =  new ArrayList<Task>();
    public static void showList() {
        int counter = 0;
        for (Task item : list) {
            System.out.println(++counter + "." + item.getStatus());
        }
    }

    public static void main(String[] args) {
        boolean active = true;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String intro = "Hello! I'm Duke What can I do for you?";
        System.out.println(intro);

        try(Scanner scanner = new Scanner(System.in)) {
            
            while (active && scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] input_split = input.split(" ");
                String command = input_split[0];

                switch (command) {
                    case "bye":
                    active = false;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                    case "list":
                    showList();
                    break;
                    case "done":
                    if (input_split.length < 2) {
                        System.out.println("What have you done?");
                        break;
                    }
                    Task removedTask = list.remove(Integer.valueOf(input_split[1]) - 1);
                    System.out.println("Nice! I've marked this task as done: \n" + 
                        "  " + removedTask.getStatus());
                    break;
                    default:
                    Task task = new Task(input);
                    list.add(task);
                    System.out.println("added: " + input);
                }
            }
        };
        
    }
}

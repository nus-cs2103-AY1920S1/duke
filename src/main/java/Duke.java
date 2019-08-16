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

class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String getStatus() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}

class Deadline extends Task {
    protected String timeDue;
    protected String preposition;
    public Deadline(String description, String preposition, String timeDue) {
        super(description);
        this.timeDue = timeDue;
        this.preposition = preposition;
    }
    @Override
    public String getStatus() {
        return "[D][" + getStatusIcon() + "] " + description + " (" + preposition + ": " + timeDue + ")";
    }
}

class Event extends Task {
    protected String startEndTime;
    protected String preposition;
    public Event(String description, String preposition, String startEndTime) {
        super(description);
        this.startEndTime = startEndTime;
        this.preposition = preposition;
    }
    @Override
    public String getStatus() {
        return "[E][" + getStatusIcon() + "] " + description + " (" + preposition + ": " + startEndTime + ")";
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
    public static void countList() {
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
    public static void construct(String type, String[] input_split) {
        if (input_split.length < 2) {
            System.out.println("what's the " + type);
            return;
        }
        Task task;
        String description = "";
        String preposition = "";
        int prepAt = 0;
        String memo = "";
        for (int i = 0; i < input_split.length; i++) {
            if (input_split[i].charAt(0) == '/') {
                preposition = input_split[i].substring(1);
                prepAt = i;
                break;
            };
        }
        if (prepAt == 0) {
            System.out.println("what's the date due?");
            return;
        } 
        for (int k = 1; k < prepAt; k++) {
            description += " " + input_split[k];
        }
        for (int i = prepAt + 1; i < input_split.length; i++) {
            memo += " " + input_split[i];
        }
        switch (type) {
            case "deadline":
            task = new Deadline(description.trim(), preposition, memo.trim());
            break;
            case "event":
            task = new Event(description.trim(), preposition, memo.trim());
            break;
            default:
            task = null;
        }
        if (task != null) {
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task.getStatus());
            countList();
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

                    case "todo":
                    if (input_split.length < 2) {
                        System.out.println("What to do?");
                        break;
                    }
                    String description = input.substring(5);
                    ToDo todo = new ToDo(description);
                    list.add(todo);
                    countList();
                    break;

                    case "deadline":
                    case "event":
                    construct(command, input_split);
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;

class DukeException extends Exception {}
class InputUnknownException extends DukeException {}
class EmptyDescriptionException extends DukeException {}
class EmptyListIndexException extends DukeException {}
// class EmptyPrepositionException extends DukeException {}
class EmptyTimeDueException extends DukeException {}

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
    public static void construct(String type, String[] input_split) 
        throws EmptyDescriptionException, EmptyTimeDueException {
        if (input_split.length < 2) {
            // System.out.println("what's the " + type);
            throw new EmptyDescriptionException();
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
            // System.out.println("what's the date due?");
            throw new EmptyTimeDueException();
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
        
        try(Scanner scanner = new Scanner(System.in);
            PrintStream out = new PrintStream(System.out, true, "UTF-8");
            ) {
            System.setOut(out);
            boolean active = true;

            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            String intro = "Hello! I'm Duke What can I do for you?";
            System.out.println(intro);
            
            while (active && scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] input_split = input.split(" ");
                String command = input_split[0];

                try {
                    switch (command) {
                        case "bye":
                        active = false;
                        System.out.println("Bye. Hope to see you again soon!");
                        break;

                        case "list":
                        System.out.println("Here are the tasks in your list:");
                        showList();
                        break;
                        
                        case "done":
                        if (input_split.length < 2) {
                            throw new EmptyListIndexException();
                        }
                        Task theTask = list.get(Integer.valueOf(input_split[1]) - 1);
                        theTask.isDone = true;
                        System.out.println("Nice! I've marked this task as done: \n" + 
                            "  " + theTask.getStatus());
                        break;

                        case "todo":
                        if (input_split.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        String description = input.substring(5);
                        ToDo todo = new ToDo(description);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + todo.getStatus());
                        list.add(todo);
                        countList();
                        break;

                        case "deadline":
                        case "event":
                        construct(command, input_split);
                        break;

                        case "delete": 
                        if (input_split.length < 2) {
                            throw new EmptyListIndexException();
                        }
                        System.out.println("Noted. I've removed this task: ");
                        Task removedTask = list.remove(Integer.valueOf(input_split[1]) - 1);
                        System.out.println("  " + removedTask.getStatus());
                        countList();
                        break;

                        default:
                        throw new InputUnknownException();
                    }
                } catch (InputUnknownException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-("); 
                } catch (EmptyDescriptionException e) {
                    System.out.println("What's the description of the item?");
                } catch (EmptyTimeDueException e) {
                    System.out.println("When is it due? eg. /by 2359 sunday");
                } catch (EmptyListIndexException e) {
                    System.out.println(command + " which one? (input an integer)");
                }

            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("whoops unsupported encoding: " + e.getMessage());
        };
        
    }
}

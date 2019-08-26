import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class DukeException extends Exception {}
class InputUnknownException extends DukeException {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
class EmptyDescriptionException extends DukeException {
    @Override
    public String getMessage() {
        return "What's the description of the item?";
    }
}
class EmptyListIndexException extends DukeException {
    @Override
    public String getMessage() {
        return " which one? (input an integer)";
    }
}
class ListItemEmptyException extends DukeException {
    @Override
    public String getMessage() {
        return "No such item in list!";
    }
}
class EmptyTimeDueException extends DukeException {
    @Override
    public String getMessage() {
        return "When is it due? eg. /by 2359 sunday";
    }
}

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

    public String saveFormat() {
        return (isDone ? "1" : "0") + " " + description;
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

    @Override
    public String saveFormat() {
        return "T" + (isDone ? "1" : "0") + " " + description;
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
    @Override
    public String saveFormat() {
        return "D" + (isDone ? "1" : "0") + " " + description + " /" + preposition + " " + timeDue;
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
    @Override
    public String saveFormat() {
        return "E" + (isDone ? "1" : "0") + " " + description + " /" + preposition + " " + startEndTime;
    }
}

public class Duke {
    private static final String saveLoadFilePath = "listSaveData.txt";

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

    public static int getPrepositionPos(String[] input_split) {
        for (int i = 0; i < input_split.length; i++) {
            if (input_split[i].charAt(0) == '/') {
                return i;
            };
        }
        return 0;
    }

    public static Task construct(String[] input_split)
        throws EmptyDescriptionException, EmptyTimeDueException {
        if (input_split.length < 2) {
            // System.out.println("what's the " + type);
            throw new EmptyDescriptionException();
        }
        Task task;
        int prepAt = getPrepositionPos(input_split);
        StringBuilder description = new StringBuilder();
        String preposition = input_split[prepAt].substring(1);
        StringBuilder memo = new StringBuilder();
        boolean prepRequired = input_split[0].equals("deadline") || input_split[0].equals("event");

        if (prepRequired && prepAt == 0) {
            // System.out.println("what's the date due?");
            throw new EmptyTimeDueException();
        }
        if (prepRequired) {
            for (int k = 1; k < prepAt; k++) {
                description.append(" " + input_split[k]);
            }
        } else {
            for (int k = 1; k < input_split.length; k++) {
                description.append(" " + input_split[k]);
            }
        }
        for (int i = prepAt + 1; i < input_split.length; i++) {
            memo.append(" " + input_split[i]);
        }

        switch (input_split[0]) {
        case "todo":
            task = new ToDo(description.deleteCharAt(0).toString());
            break;
        case "deadline":
            task = new Deadline(description.deleteCharAt(0).toString(), preposition, memo.deleteCharAt(0).toString());
            break;
        case "event":
            task = new Event(description.deleteCharAt(0).toString(), preposition, memo.deleteCharAt(0).toString());
            break;
        default:
            task = null;
        }

        return task;
    }
    public static Task construct(String[] input_string, boolean completed)
            throws EmptyDescriptionException, EmptyTimeDueException{
        Task task = construct(input_string);
        task.isDone = completed;
        return task;
    }
    public static void saveList() throws IOException {
        File f = new File(saveLoadFilePath);

        // how do you work around this? it always still exists
        if (f.exists()) {
            System.out.println(f.delete() ? "Previous File deleted" : "Previous File still exists");
        }

        System.out.println(f.createNewFile() ? "New file created" : "New file not created");
        FileWriter fw = new FileWriter(f,   false);
        for (Task item : list) {
            fw.write(item.saveFormat() + "\n");
        }
        fw.close();
        System.out.println("Finished Saving");
    }
    public static void loadList() throws FileNotFoundException, EmptyDescriptionException, EmptyTimeDueException {
        File f = new File(saveLoadFilePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String[] task = s.nextLine().split(" ");
            boolean completed = task[0].charAt(1) == '1';
            switch(task[0].charAt(0)) {
            case 'T':
                // construct todo
                task[0] = "todo";
                break;
            case 'D':
                // construct deadline
                task[0] = "deadline";
                break;
            case 'E':
                // construct event
                task[0] = "event";
                break;
            }
            list.add(construct(task, completed));
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

            try {
                loadList();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (EmptyTimeDueException | EmptyDescriptionException e) {
                System.out.println("Error with loading: " + e.getMessage());
            }

            String intro = "Hello! I'm Duke What can I do for you?";
            System.out.println(intro);
            
            while (active && scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] input_split = input.split(" ");
                String command = input_split[0];

                try {
                    switch (command) {
                    case "exit":
                        active = false;
                        break;

                    case "bye":
                        active = false;
                        System.out.println("Bye. Hope to see you again soon!");
                        saveList();
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
                    case "deadline":
                    case "event":
                        Task task = construct(input_split);
                        if (task != null) {
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + task.getStatus());
                            list.add(task);
                            countList();
                        }
                        break;

                    case "delete":
                        if (input_split.length < 2) {
                            throw new EmptyListIndexException();
                        }
                        int i = Integer.parseInt(input_split[1]);
                        if (i > list.size()) throw new ListItemEmptyException();
                        Task removedTask = list.remove( i - 1);
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println("  " + removedTask.getStatus());
                        countList();
                        break;

                    default:
                        throw new InputUnknownException();
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("unable to save " + e.getMessage());
                }

            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("whoops unsupported encoding: " + e.getMessage());
        };
        
    }
}

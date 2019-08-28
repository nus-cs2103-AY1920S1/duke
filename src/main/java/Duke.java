import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        try {
            loadFile();
        } catch (IOException ex) {
            System.out.println("File not found.");
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean continues = true;
        String description = sc.nextLine();

        while (!description.equals("bye")) {
            try {
                boolean valid = checkValidity(description);
                if(valid) {
                    String[] part = description.split(" ");
                    String command = part[0]; // to extract the first word of input description.
                    switch (command) {
                        case "list":
                            showList();
                            break;
                        case "done":
                            int indexToMark = Integer.parseInt(part[1]);
                            list.get(indexToMark - 1).markAsDone();
                            System.out.println("Nice! I've marked this task as done: \n  " +
                                    list.get(indexToMark - 1));
                            updateFile();
                            break;
                        case "delete":
                            int indexToDelete = Integer.parseInt(part[1]);
                            Task removed = list.remove(indexToDelete - 1);
                            System.out.println("Noted. I've removed this task: \n  " +
                                    removed + "\nNow you have " + list.size() + " tasks in the list.");
                            updateFile();
                            break;
                        case "todo":
                        case "deadline":
                        case "event":
                            boolean isDone = false;
                            addTask(description, command, isDone);
                            updateFile();
                            break;
                    }
                }
            } catch (DukeException | IOException ex) {
                System.out.println(ex.getMessage());
            }
            description = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close(); //exit the program
    }

    // To load the data from hard disk each time when Duke starts up.
    public static void loadFile() throws IOException{
        FileReader fr = new FileReader("duke.txt");

        Scanner s = new Scanner(fr);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] part = line.split("/");
            String status = part[1];
            boolean isDone;
            if (status.equals("✓")) {
                isDone = true;
            } else {
                isDone = false;
            }
            switch (part[0]) {
                case "T":
                    list.add(new Todo(part[2], isDone));
                    break;
                case "D":
                    list.add(new Deadline(part[2], isDone, part[3]));
                    break;
                case "E":
                    list.add(new Event(part[2], isDone, part[3]));
                    break;
            }
        }
    }

    // To save the tasks in the list whenever there is any change.
    public static void updateFile() throws IOException {
        FileWriter fw = new FileWriter("duke.txt");
        for (Task task : list) {
            fw.write(task.storageFormat() + "\n");
            fw.flush();
        }
    }

    // To check validity of the input description.
    public static boolean checkValidity(String description) throws DukeException {
            String[] part = description.split(" ");
            String firstWord = part[0];
            switch (firstWord) {
                case "list":
                    break;
                case "done": case "delete":
                    if (part.length < 2) {
                        throw new DukeException("☹ OOPS!!! You need to enter an index.");
                    }
                case "todo":
                    // check task description.
                    if (part.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case "deadline":
                    if (part.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    break;
                case "event":
                    if (part.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return true;
    }


    // To print out the items stored in the list.
    public static void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
    }

    // To add a new task into the list array.
    public static void addTask(String description, String command, boolean isDone) {
        Task newTask;
        switch(command)
        {
            case "todo":
                String[] activity = description.split("todo");
                newTask = new Todo(activity[1], isDone);
                break;
            case "deadline":
                String[] details = description.split("deadline");
                String[] activity1 = details[1].split("/");
                String[] time = activity1[1].split("by");
                newTask = new Deadline(activity1[0], isDone, time[1]);
                break;
            case "event":
                String[] details2 = description.split("event");
                String[] activity2 = details2[1].split("/");
                String[] time2 = activity2[1].split("at");
                newTask = new Event(activity2[0], isDone, time2[1]);
                break;
            default:
                newTask = new Task(command, isDone);
        }

        list.add(newTask);
        System.out.println("Got it. I've added this task: \n  " +
                newTask + "\nNow you have " + list.size() + " tasks in the list.");
    }
}
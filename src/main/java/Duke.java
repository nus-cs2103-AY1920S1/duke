import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc;
        Boolean exit = false;

        // Retrieve existing tasks
        File file = new File("tasks.txt");
        Scanner fileSc;
        try {
            fileSc = new Scanner(file);
            while (fileSc.hasNextLine()) {
                String[] nextLine = fileSc.nextLine().strip().split("`");
                Task newTask;
                boolean isDone = nextLine[1].equals("1");
                switch (nextLine[0]) {
                case ("T"):
                    newTask = new ToDo(nextLine[2], isDone);
                    break;
                case ("D"):
                    newTask = new Deadline(nextLine[2], isDone, nextLine[3]);
                    break;
                case ("E"):
                    newTask = new Event(nextLine[2], isDone, nextLine[3]);
                    break;
                default:
                    newTask = new ToDo("Unknown task");
                    break;
                }
                tasks.add(newTask);
            }
            fileSc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        sc = new Scanner(System.in);
        String response = sc.next();
        while (!exit) {
            try {
                switch (response) {
                case ("bye"):
                    exit = true;
                    break;
                case ("list"):
                    Iterator<Task> iter = tasks.iterator();
                    int i = 1;
                    while (iter.hasNext()) {
                        Task task = iter.next();
                        System.out.println(task.toString());
                        i++;
                    }
                    response = sc.next();
                    break;
                case ("done"):
                    int taskNo = Integer.parseInt(sc.next());
                    Task task = tasks.get(taskNo - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + task.getStatusIcon() + "] " + task.description);
                    response = sc.next();
                    break;
                case ("todo"):
                    try {
                        String description = sc.nextLine().trim();
                        if (description.equals("")) throw new EmptyDescriptionException(
                                "☹ OOPS!!! The description of a todo cannot be empty.");
                        ToDo newToDo = new ToDo(description);
                        tasks.add(newToDo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newToDo.toString());
                        System.out.println("Now you have " + tasks.size() + " task in the list.");
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.toString());
                    }
                    response = sc.next();
                    break;
                case ("deadline"):
                    response = sc.nextLine().trim();
                    Deadline newDeadline = new Deadline(response.split(" /by ")[0],
                            response.split(" /by ")[1]);
                    tasks.add(newDeadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    response = sc.next();
                    break;
                case ("event"):
                    response = sc.nextLine().trim();
                    Event newEvent = new Event(response.split(" /at ")[0],
                            response.split(" /at ")[1]);
                    tasks.add(newEvent);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    response = sc.next();
                    break;
                case ("delete"):
                    int index = Integer.parseInt(sc.nextLine().trim()) - 1;
                    Task removedTask = tasks.remove(index);
                    System.out.println("Got it. I've removed this task:");
                    System.out.println(removedTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    response = sc.next();
                    break;
                default:
                    throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (InvalidCommandException e) {
                System.out.println(e.toString());
                response = sc.next();
            }

            // write all tasks to file
            FileWriter fw;
            try {
                fw = new FileWriter("tasks.txt");
                String dataStr = "";
                for (Task task : tasks) {
                    String isDone = task.isDone
                            ? "1"
                            : "0";
                    dataStr += task.toStorageString() + System.lineSeparator();
                }
                fw.write(dataStr);
                fw.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}

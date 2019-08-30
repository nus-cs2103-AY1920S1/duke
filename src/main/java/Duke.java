import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> todolist = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        //System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");




        try {
            File f = new File("src/main/java/data/duke.txt");
            if (!f.exists()) {
                throw new DukeException("File not found.");
            }

            Scanner readFileScanner = new Scanner(f);
            while(readFileScanner.hasNext()) {
                String[] todoTask = readFileScanner.nextLine().split(" \\| ");
                switch (todoTask[0]) {
                    case "T":
                        Task task1 = new Todo(todoTask[2]);
                        if (todoTask[1].equals("1")) {
                            task1.markAsDone();
                        }
                        todolist.add(task1);
                        break;
                    case "D":
                        Task task2 = new Deadline(todoTask[2], todoTask[3]);
                        if (todoTask[1].equals("1")) {
                            task2.markAsDone();
                        }
                        todolist.add(task2);
                        break;
                    case "E":
                        Task task3 = new Event(todoTask[2], todoTask[3]);
                        if (todoTask[1].equals("1")) {
                            task3.markAsDone();
                        }
                        todolist.add(task3);
                        break;
                    default:
                        throw new DukeException("Something in file go wrong.");
                }
            }
            readFileScanner.close();

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()) {
                String request = scanner.nextLine();

                System.out.println("____________________________________________________________");
                if (request.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    saveFile();
                    System.out.println("____________________________________________________________");
                    return;
                } else if (request.equals("list")) {
                    for (int i = 0; i < todolist.size(); i++) {
                        String todo = String.format("%d. %s", i + 1, todolist.get(i).toString());
                        System.out.println(todo);
                    }
                } else if (request.startsWith("done")) {
                    char num = request.charAt(request.length() - 1);
                    int index = Character.getNumericValue(num);
                    if (index >= todolist.size()) {
                        throw new DukeException(" ☹ OOPS!!! Do not exist that task.");
                    }
                    Task task = todolist.get(index - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.print("  ");
                    System.out.println(task.toString());
                } else if (request.startsWith("delete")) {
                    char num = request.charAt(request.length() - 1);
                    int index = Character.getNumericValue(num);
                    if (index >= todolist.size()) {
                        throw new DukeException(" ☹ OOPS!!! Do not exist that task.");
                    }
                    Task task = todolist.get(index - 1);
                    todolist.remove(index - 1);
                    deleteTask(task);
                } else if (request.startsWith("todo")) {
                    if (request.trim().length() == 4) {
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String descrip = request.substring(5);
                    Task task = new Todo(descrip);
                    todolist.add(task);
                    String add = String.format("added: %s", request);
                    addTask(task);
                } else if (request.startsWith("event")) {
                    if (request.trim().length() == 5) {
                        throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String[] event = request.substring(6).split(" /at ");
                    if (event.length != 2) {
                        throw new DukeException(" ☹ OOPS!!! The description of a event is wrong.");
                    }
                    Task task = new Event(event[0], event[1]);
                    todolist.add(task);
                    addTask(task);
                } else if (request.startsWith("deadline")) {
                    if (request.trim().length() == 8) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] event = request.substring(9).split(" /by ");
                    if (event.length != 2) {
                        throw new DukeException(" ☹ OOPS!!! The description of a event is wrong.");
                    }
                    Task task = new Deadline(event[0], event[1]);
                    todolist.add(task);
                    addTask(task);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                System.out.println("____________________________________________________________");
            }

        } catch (DukeException | FileNotFoundException exception) {
            System.out.println(exception.toString());
        }
    }

    private static void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", todolist.size()));
    }

    private static void deleteTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", todolist.size()));
    }

    private static void saveFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter("src/main/java/data/duke.txt");
            String data = todolist.get(0).toFile();
            if (todolist.size() > 1) {
                for (int i = 1; i < todolist.size(); i++) {
                    data = data + System.lineSeparator() + todolist.get(i).toFile();
                }
            }
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("No such file.");
        }

    }
}
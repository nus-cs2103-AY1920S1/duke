import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;

public class Duke {
    private ArrayList<Task> taskList = new ArrayList<>();
    private static String filePath = "data/duke.txt";

    private void run(Scanner sc, File file) throws IOException {
        boolean bye = false;

        printWelcome();

        writeFromFile(file);

        while(!bye) {
            String input = sc.nextLine();
            String[] command = input.split(" ");
            try {
                switch (command[0]) {
                case "bye":
                printBye();
                bye = true;
                break;
                case "list":
                printList();
                break;
                case "done":
                completeTask(input);
                break;
                case "todo":
                addTodo(input);
                break;
                case "deadline":
                addDeadline(input);
                break;
                case "event":
                addEvent(input);
                break;
                case "delete":
                deleteTask(input);
                break;
                default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        writeToFile();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File file = new File(filePath);
        Duke duke = new Duke();
        try {
            duke.run(sc, file);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            sc.close();
        }
    }

    private void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    private void printBye() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    private void printList() {
        int count = 1;
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the task(s) in your list:");
        for (Task item: taskList) {
            System.out.printf("\t %d. %s\n", count, item);
            count++;
        }
        System.out.println("\t____________________________________________________________");
    }

    private void completeTask(String input) throws DukeException {
        if (input.split(" ").length > 1) {
            try {
                int itemIndex = Integer.parseInt(input.split(" ")[1]);
                if(itemIndex > taskList.size() || itemIndex < 1) {
                    throw new DukeException("The task number specified is not within the list.");
                } else {
                    Task currTask = taskList.get(itemIndex - 1);
                    currTask.doTask();
                }
            } catch (NumberFormatException e) {
                throw new DukeException("The task specified is not a number.");
            }
        } else {
            throw new DukeException("The task to be done is not specified.");
        }
    }

    private void addTodo(String input) throws DukeException {
        if (input.split(" ", 2).length > 1) {
            String todoInput = input.split(" ", 2)[1];
            Todo todo = new Todo(todoInput);
            taskList.add(todo);
            printAddedTask();
        } else {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    private void addDeadline(String input) throws DukeException {
        if (input.split(" ", 2).length > 1) {
            String[] desc = input.split(" ", 2)[1].split(" /by ");
            if (desc.length > 2) {
                throw new DukeException("There are too many /by in the description.");
            } else if (desc.length < 2) {
                throw new DukeException("The description of the deadline is insufficient.");
            }
            Deadline deadline = new Deadline(desc[0], desc[1]);
            taskList.add(deadline);
            printAddedTask();
        } else {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
    }

    private void addEvent(String input) throws DukeException {
        if (input.split(" ", 2).length > 1) {
            String[] desc = input.split(" ", 2)[1].split(" /at ");
            if (desc.length > 2) {
                throw new DukeException("There are too many /at in the description.");
            } else if (desc.length < 2) {
                throw new DukeException("The description of the deadline is insufficient.");
            }
            Event event = new Event(desc[0], desc[1]);
            taskList.add(event);
            printAddedTask();
        } else {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    private void deleteTask(String input) throws DukeException {
        if (input.split(" ").length > 1) {
            try {
                int deleteIndex = Integer.parseInt(input.split(" ")[1]);
                if (deleteIndex > taskList.size() || deleteIndex < 1) {
                    throw new DukeException("The task number specified is not within the list.");
                }
                Task deleted = taskList.remove(deleteIndex - 1);
                printDeletedTask(deleted);
            } catch (NumberFormatException e) {
                throw new DukeException("The task specified is not a number.");
            }
        } else {
            throw new DukeException("The task to be deleted is not specified.");
        }
    }

    private void printAddedTask() {
        Task lastTask = taskList.get(taskList.size() - 1);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:");
        System.out.printf("\t   %s\n", lastTask);
        System.out.printf("\t Now you have %d task(s) in the list.\n", taskList.size());
        System.out.println("\t____________________________________________________________");
    }

    private void printDeletedTask(Task task) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Noted. I've removed this task:");
        System.out.printf("\t   %s\n", task);
        System.out.printf("\t Now you have %d task(s) in the list.\n", taskList.size());
        System.out.println("\t____________________________________________________________");
    }

    private void writeFromFile(File file) throws IOException {
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" \\| ");
            switch (line[0]) {
            case "T":
            Todo todo = new Todo(line[2]);
            if (line[1].equals("1")) {
                todo.markDone();
                taskList.add(todo);
            } else {
                taskList.add(todo);
            }
            break;
            case "D":
            Deadline deadline = new Deadline(line[2], line[3]);
            if (line[1].equals("1")) {
                deadline.markDone();
                taskList.add(deadline);
            } else {
                taskList.add(deadline);
            }
            break;
            case "E":
            Event event = new Event(line[2], line[3]);
            if (line[1].equals("1")) {
                event.markDone();
                taskList.add(event);
            } else {
                taskList.add(event);
            }
            break;
            default:
            break;
            }
        }
    }

    private void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        boolean first = true;
        String textToAdd = "";

        for (Task task: taskList) {
            if (first) {
                textToAdd = task.formatToWrite();
                first = false;
            } else {
                textToAdd = String.format("%s\n%s", textToAdd, task.formatToWrite());
            }
        }
        System.out.print(textToAdd);
        fw.write(textToAdd);
        fw.close();
    }
}

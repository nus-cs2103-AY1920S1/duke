import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Duke {
    private static boolean isBye = false;
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static File taskFile = new File("task.txt"); //load task file

    public static void saveTask() throws IOException {
        taskFile.createNewFile();
        FileWriter fileWriter = new FileWriter("task.txt");

        for (int i = 0; i < tasks.size(); i++){
            String message = tasks.get(i).toString() + "\r\n";
            fileWriter.write(message);
        }
        fileWriter.close();

    }

    public static void saveTaskUtil(){
        try{
            saveTask();
        } catch (IOException e) {
            System.out.println("task file is not found");
        }

    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++){
            Task task = tasks.get(i);
            int count = i + 1;
            System.out.println(count + "." + task.toString());
        }
    }

    public static void done(int taskIndex) {
        try{
            Task task = tasks.get(taskIndex);
            task.completed();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("   [✓] " + task.getTask());
        } catch (IndexOutOfBoundsException ex) {
            if (tasks.size() == 0){
                System.out.println("No tasks to be done.");
            } else {
                taskIndex += 1;
                System.out.println("Task " + taskIndex + " does not exist.");
            }
        }
    }

    public static void delete(int taskIndex) {
        try{
            Task deletedTask = tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + deletedTask.toString());
            if (tasks.size() <= 1){
                System.out.println("Now you have " + tasks.size() + " task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }

        } catch (IndexOutOfBoundsException ex) {
            if (tasks.size() == 0) {
                System.out.println("Nothing to delete.");
            } else {
                taskIndex += 1;
                System.out.println("Task " + taskIndex + " does not exist.");
            }

        }
    }

    public static void createDeadLine(String input) {
        String[] words = input.split(" /by ");
        Deadline deadLine = new Deadline(words[0], words[1]);
        tasks.add(deadLine);
        deadLine.printAddedDeadline(tasks.size());
    }

    public static void createEvent(String input) {
        String[] words = input.split(" /at ");
        Events event = new Events(words[0], words[1]);
        tasks.add(event);
        event.printAddedEvent(tasks.size());
    }

    public static void createToDo(String input){
        Todo toDo = new Todo(input);
        tasks.add(toDo);
        toDo.printAddedTodo(tasks.size());
    }

    public static void checkCommand(String input) throws NoValidCommandException {
        String[] words = input.split(" ", 2);
        String command = words[0];

            switch (command) {
            case "bye":
                isBye = true;
                System.out.println("Bye. Hope to " +
                    "see you again soon!");
                break;

            case "list":
                printList();
                break;

            case "done":
                int doneIndex = Integer.parseInt(words[1]) - 1;
                done(doneIndex);
                saveTaskUtil();
                break;

            case "delete":
                int delIndex = Integer.parseInt(words[1]) - 1;
                delete(delIndex);
                saveTaskUtil();
                break;

            case "deadline":
                createDeadLine(words[1]);
                saveTaskUtil();
                break;

            case "event":
                try{
                createEvent(words[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Either event description or event specific timing is missing.");
                }
                saveTaskUtil();
                break;

            case "todo":
                try {
                    createToDo(words[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                saveTaskUtil();
                break;

            default:
                throw new NoValidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    public static void runInterface() {
        Scanner scan = new Scanner(System.in);

        while (!isBye) {
            System.out.print("input command here: ");
            String input = scan.nextLine();
            try {
                checkCommand(input);
            } catch (NoValidCommandException ex){
                ex.printErrorMessage();
            }
        }
    }

    public static void main (String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        runInterface();


    }

}

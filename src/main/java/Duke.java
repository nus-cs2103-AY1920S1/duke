import jdk.jfr.StackTrace;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private static  LinkedList<Task> tasks = new LinkedList<Task>();
    private static String pathName = "C:/Users/User/Documents/GitHub/duke/src/main/";
    public static void main(String[] args) {
        setUp();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //reset();
        System.out.println(logo);
        System.out.println("Hello I'm Duke\n" + "What can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String curr = sc.nextLine();
            if (curr.equals("bye")) {
               System.out.println("Bye. Hope to see you again soon!");
               break;
            } else if (curr.equals("list")) {
                int i = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task t : tasks) {
                    System.out.println(i + ". " + t);
                    i++;
                }
            } else {
                execute(curr);
            }
        }

    }

    private static void execute(String command) {
        if (command.startsWith("done ")) {
            try {
                int id = Integer.parseInt(command.substring(5));
                Task finished_task = tasks.get(id - 1);
                finished_task.markAsDone();
                updateTaskFile();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(finished_task);
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Invalid index provided!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! No such task exist!");
            }
        } else if (command.startsWith("delete ")){
            try {
                int id = Integer.parseInt(command.substring(7));
                Task deleted_task = tasks.get(id - 1);
                tasks.remove(id - 1);
                updateTaskFile();
                System.out.println("Noted. I've removed this task: ");
                System.out.println(deleted_task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Invalid index provided!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! No such task exist!");
            }
        } else {
            addTask(command);
        }
    }

    public static void addTask(String command) {
        boolean valid = true;
        Task curr = null;
        if (command.startsWith("todo ")) {
            curr = new Todo(command);
            tasks.add(curr);
            addTasksToFile(curr);

        } else if (command.startsWith("deadline ")) {
            if (!command.contains("/by ")) {
                System.out.println("☹ OOPS!!! Please follow format e.g deadline return book /by Sunday");
                valid = false;
            } else {
                curr = new Deadline(command);
                tasks.add(curr);
                addTasksToFile(curr);
            }
        } else if (command.startsWith("event ")) {
            if (!command.contains("/at ")) {
                System.out.println("☹ OOPS!!! Please follow format e.g event project meeting /at Mon 2-4pm");
                valid = false;
            } else {
                curr = new Event(command);
                tasks.add(curr);
                addTasksToFile(curr);
            }

        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            valid = false;
        }
        
        if (valid) {
            System.out.println("Got it. I've added this task: ");
            System.out.println(curr);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }

    }
    /*
    @Test
    public void bleh() {
        assert(3 == 3 );
    }*/
    private static void addTasksToFile(Task task) {
        File saveFile = new File(pathName + "duke.txt");
        try {
            FileWriter fw = new FileWriter(saveFile, true);
            fw.write(task.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void updateTaskFile() {

        File saveFile = new File(pathName + "duke.txt");
        try {

            FileWriter fw = new FileWriter(saveFile, false);
            for (Task t : tasks) {
                fw.write(t.toString() + "\n");
            }

            fw.close();
            /*
            File temp = new File(pathName + "temp.txt");
            PrintWriter out = new PrintWriter(new FileWriter(temp));
            Files.lines(input.toPath())
                    .filter(line -> !line.contains(task.toString()))
                    .forEach(out::println);
            out.flush();
            out.close();
            input.delete();
            while (input.exists()) {
                System.out.println("UPDATING...");
            }
            temp.renameTo(input);*/

            //temp.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void setUp() {
        File file = new File(pathName + "duke.txt");
        if (file.exists()) {
            try {

                List<String> temp = Files.lines(file.toPath())
                        .collect(Collectors.toList());
                for (String s : temp) {
                    Task curr = null;
                    if (s.contains("[T]")) {
                        curr = new Todo(s.substring(7));
                    } else if (s.contains("[D]")) {
                        curr = new Deadline(s.substring(7));
                    } else if (s.contains("[E]")) {
                        curr = new Event(s.substring(7));
                    }

                    if (s.contains("[✓]")) {
                        curr.markAsDone();
                    }

                    tasks.add(curr);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

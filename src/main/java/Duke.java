import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * This class tests for chatbot Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.run(tasks, storage);
    }
    /*public void runn() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<Task> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int i = 0;
        //PrintWriter writer = new PrintWriter("duke.txt", "UTF-8");

        while(sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) { //go through the arrayList
                PrintWriter writer = new PrintWriter("duke.txt", "UTF-8");
                System.out.println("Here are the tasks in your list: ");
                String output = "";
                for (int j = 0; j < arr.size(); j++) {
                    System.out.println((j + 1) + "." + arr.get(j));
                    output += ((j+1) + "." + arr.get(j));
                    output += "\n";
                }
                //PrintWriter writer = new PrintWriter("duke.txt", "UTF-8"));
                writer.print(output);
                writer.close();
            } else if (input.equals("done")) {
                int num = sc.nextInt();
                System.out.println("Nice! I've marked this task as done: ");
                arr.get(num - 1).markAsDone();
                System.out.println(arr.get(num));
            } else if (input.equals("todo")) {
                String descTodo = sc.nextLine();
                if (descTodo.isEmpty()) {
                    throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
                }
                arr.add(new Todo(descTodo));
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + arr.get(i));
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                i++;
                //writer.println("" + arr.get(i));
            } else if (input.equals("deadline")) {
                String rem = sc.nextLine();
                String[] descriptionNDate = rem.split("/by");
                String description = descriptionNDate[0];
                String by = descriptionNDate[1];
                arr.add(new Deadline(description, by));
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + arr.get(i));
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                i++;
                //writer.println(arr.get(i));
            } else if (input.equals("event")) {
                String rest = sc.nextLine();
                String[] descriptionNAt = rest.split("/at");
                String desc = descriptionNAt[0];
                String at = descriptionNAt[1];
                arr.add(new Event(desc, at));
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + arr.get(i));
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                i++;
                //writer.println(arr.get(i));
            } else if (input.equals("delete")) {
                int deleteNum = sc.nextInt();
                Task toRemove = arr.get(deleteNum - 1);
                arr.remove(deleteNum-1);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(" " + toRemove);
                System.out.println("Now you have " + arr.size() + " tasks in the list. ");
                i--;
            } else {
                throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }*/

    public static void main(String[] args) {
        new Duke("../../../data/tasks.txt").run();
    }

    /*public static void main(String[] args) throws IOException {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<Task> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int i = 0;
        //PrintWriter writer = new PrintWriter("duke.txt", "UTF-8");

        while(sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) { //go through the arrayList
                PrintWriter writer = new PrintWriter("duke.txt", "UTF-8");
                System.out.println("Here are the tasks in your list: ");
                String output = "";
                for (int j = 0; j < arr.size(); j++) {
                    System.out.println((j + 1) + "." + arr.get(j));
                    output += ((j+1) + "." + arr.get(j));
                    output += "\n";
                }
                //PrintWriter writer = new PrintWriter("duke.txt", "UTF-8"));
                writer.print(output);
                writer.close();
            } else if (input.equals("done")) {
                int num = sc.nextInt();
                System.out.println("Nice! I've marked this task as done: ");
                arr.get(num - 1).markAsDone();
                System.out.println(arr.get(num));
            } else if (input.equals("todo")) {
                String descTodo = sc.nextLine();
                if (descTodo.isEmpty()) {
                    throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
                }
                arr.add(new Todo(descTodo));
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + arr.get(i));
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                i++;
                //writer.println("" + arr.get(i));
            } else if (input.equals("deadline")) {
                String rem = sc.nextLine();
                String[] descriptionNDate = rem.split("/by");
                String description = descriptionNDate[0];
                String by = descriptionNDate[1];
                arr.add(new Deadline(description, by));
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + arr.get(i));
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                i++;
                //writer.println(arr.get(i));
            } else if (input.equals("event")) {
                String rest = sc.nextLine();
                String[] descriptionNAt = rest.split("/at");
                String desc = descriptionNAt[0];
                String at = descriptionNAt[1];
                arr.add(new Event(desc, at));
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + arr.get(i));
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                i++;
                //writer.println(arr.get(i));
            } else if (input.equals("delete")) {
                int deleteNum = sc.nextInt();
                Task toRemove = arr.get(deleteNum - 1);
                arr.remove(deleteNum-1);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(" " + toRemove);
                System.out.println("Now you have " + arr.size() + " tasks in the list. ");
                i--;
            } else {
                throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }*/
}

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        int count = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String input = sc.nextLine();

        while (!input.equals(null)) {
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + "." + taskList.get(i));
                }
            } else if (input.contains("done")) {
                int index = Character.getNumericValue(input.charAt(5)) - 1;
                (taskList.get(index)).setStatus();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(index));
            } else if (input.contains("todo") || input.contains("event") || input.contains("deadline")) {
                System.out.println("Got it. I've added this task:");
                Task newTask = new Task("");
                String description;
                String task;
                if (input.contains("todo")) {
                    description = input.substring(5);
                    newTask = new Todo(description);
                    taskList.add(newTask);
                } else if (input.contains("event")) {
                    description = input.substring(6);
                    String arr[] = description.split("/", 2);
                    task = arr[1].substring(3);
                    newTask = new Event(arr[0], task);
                    taskList.add(newTask);
                } else if (input.contains("deadline")) {
                    description = input.substring(9);
                    String arr[] = description.split("/", 2);
                    task = arr[1].substring(3);
                    newTask = new Deadline(arr[0], task);
                    taskList.add(newTask);
                }
                System.out.println(newTask);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else if (input.contains("delete")) {
                System.out.println("Noted. I've removed this task:");
                int index = Character.getNumericValue(input.charAt(7)) - 1;
                System.out.println(taskList.get(index));
                taskList.remove(index);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
            input = sc.nextLine();
        }
    }
}
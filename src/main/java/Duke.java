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
            } else {
                count++;
                taskList.add(new Task(input));
            }
            input = sc.nextLine();
        }
    }
}
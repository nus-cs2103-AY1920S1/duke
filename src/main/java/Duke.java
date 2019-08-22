package com.leeyiyuan;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Scanner;

import com.leeyiyuan.Task;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println(String.format(
                                "%d.[%s] %s", 
                                i + 1, 
                                task.getIsDone() ? "✓" : "✗",
                                task.getTitle()));
                }

            } else if (Pattern.matches("done [0-9]+", input)) {
                int index = Integer.parseInt(input.split(" ")[1]);
                if (index > 0 && index <= tasks.size()) {
                    Task task = tasks.get(index - 1);
                    task.setIsDone(true);
                    System.out.println(String.format(
                                "Nice! I've marked this task as done:\n[✓] %s",
                                task.getTitle()));
                } else {
                    System.out.println("Invalid item selected.");
                }
            } else {
                Task task = new Task();
                task.setTitle(input);
                tasks.add(task);
                System.out.println(String.format("added: %s", input));
            }
        }
    }
}

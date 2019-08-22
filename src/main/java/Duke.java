package com.leeyiyuan;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Scanner;

import com.leeyiyuan.Task;
import com.leeyiyuan.TodoTask;
import com.leeyiyuan.DeadlineTask;
import com.leeyiyuan.EventTask;
import com.leeyiyuan.DukeException;

public class Duke {

    public static void main(String[] args) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                boolean hasAddedTask = false;
                Task addedTask = null;
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(String.format(
                                    "%d.%s", 
                                    i + 1, 
                                    task.toString()));
                    }

                } else if (Pattern.matches("done [0-9]+", input)) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    if (index > 0 && index <= tasks.size()) {
                        Task task = tasks.get(index - 1);
                        task.setIsDone(true);
                        System.out.println(String.format(
                                    "Nice! I've marked this task as done:\n%s",
                                    task.toString()));
                    } else {
                        throw new DukeException("Invalid item selected.");
                    }
                } else if (Pattern.matches("todo ?", input)) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else if (Pattern.matches("todo .+", input)) {
                    TodoTask task = new TodoTask();
                    task.setTitle(input.split("todo ", 2)[1]);
                    tasks.add(task);
                    hasAddedTask = true;
                    addedTask = task;
                } else if (Pattern.matches("deadline ?/by.*", input)) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else if (Pattern.matches("deadline .+ /by ?", input)) {
                    throw new DukeException("The cutoff of a deadline cannot be empty.");
                } else if (Pattern.matches("deadline .+ /by .+", input)) {
                    String[] data = input.split("deadline ", 2)[1].split(" /by ", 2);
                    DeadlineTask task = new DeadlineTask();
                    task.setTitle(data[0]);
                    task.setDeadline(data[1]);
                    tasks.add(task);
                    hasAddedTask = true;
                    addedTask = task;
                } else if (Pattern.matches("event ?/at.*", input)) {
                    throw new DukeException("The description of an event cannot be empty.");
                } else if (Pattern.matches("event .+ /at ?", input)) {
                    throw new DukeException("The time of an event cannot be empty.");
                } else if (Pattern.matches("event .+ /at .+", input)) {
                    String[] data = input.split("event ", 2)[1].split(" /at ", 2);
                    EventTask task = new EventTask();
                    task.setTitle(data[0]);
                    task.setTime(data[1]);
                    tasks.add(task);
                    hasAddedTask = true;
                    addedTask = task;
                } else if (Pattern.matches("delete ?", input)) {
                    throw new DukeException("The item id of a delete command cannot be empty.");
                } else if (Pattern.matches("delete [0-9]+", input)) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    if (index > 0 && index <= tasks.size()) {
                        Task task = tasks.remove(index - 1);
                        System.out.println(String.format(
                                    "Noted. I've removed this task:\n%s",
                                    task.toString()));
                        System.out.println(String.format(
                                    "Now you have %d %s in the list.",
                                    tasks.size(),
                                    tasks.size() == 1 ? "task" : "tasks"));
                    } else {
                        throw new DukeException("Invalid item selected for deletion.");
                    }
                } else {
                    throw new DukeException("Unknown command.");
                }

                if (hasAddedTask) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("  %s", addedTask.toString()));
                    System.out.println(String.format(
                                "Now you have %d %s in the list.",
                                tasks.size(),
                                tasks.size() == 1 ? "task" : "tasks"));
                }
            } catch (DukeException e) {
                System.out.println("Caught exception: " + e.toString());
            }
        }
    }

}

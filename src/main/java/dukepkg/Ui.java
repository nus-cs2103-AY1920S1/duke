package dukepkg;
import dukepkg.exceptions.DukeException;
import dukepkg.exceptions.UnrecognizedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner input;
    public Ui() {
        input = new Scanner(System.in);
    }

    public static void showLoadingError(FileNotFoundException e) {
        System.out.println("Did you created the file for task storage? " + e.getMessage());
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int counter = 0;
        for (Task t : tasks) {
            counter++;
            System.out.println(counter + ". " + t.toString());
        }
    }

    public void showExitMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTaskDonePrompt(Task t) {
        String prompt = "Nice! I've marked this task as done:\n" +
                "    " + t.toString();
        System.out.println(prompt);
    }

    public void showTaskDeletedPrompt(Task t, int size) {
        String prompt = "Noted. I've removed this task:\n" +
                "    " + t.toString() + "\n" +
                "Now you have " + size + (size == 1 ? " task" : " tasks") + " in the list.";
        System.out.println(prompt);
    }

    public void showAddedTaskPrompt(ArrayList<Task> tasks, Task t) {
        String output = "Got it. I've added this task:\n" +
                "    " + t.toString() + "\n" +
                "Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.";
        System.out.println(output);
    }

    public void showSavingError(IOException e) {
        System.out.println("Task not saved: " + e.getMessage());
    }

    public void showGreeting() {
        String greeting = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(greeting);
    }

    public void showDukeError(DukeException e) {
        System.out.println(e);
    }

    public String readCommand() throws UnrecognizedException {
        if(input.hasNextLine()) {
            return input.nextLine();
        } else {
            throw new UnrecognizedException("☹ OOPS!!! no input.");
        }
    }
}

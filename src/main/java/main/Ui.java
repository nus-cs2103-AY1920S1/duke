package main;

import task.Task;

import java.util.ArrayList;

public class Ui {

    public String printList(ArrayList<Task> arr) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < arr.size(); i++) {
            output = output + (i + 1) + "." + arr.get(i) + "\n";
        }
        return output;
    }

    public String printAdd(ArrayList<Task> arr) {
        String output = "Got it. I've added this task: \n";
        output += arr.get(arr.size() - 1) + "\n";
        output += "Now you have " + arr.size() + " tasks in the list.\n";
        return output;
    }

    public String printDone(ArrayList<Task> arr, int index) {
        arr.get(index).markAsDone();

        String output = "Nice! I've marked this task as done: \n" + arr.get(index);
        return output;
    }

    public String printRemove(ArrayList<Task> arr, Task task) {

        String output = "Noted. I've removed this task: \n" + task + "\n";
        output = output + "Now you have " + arr.size() + " tasks in the list.\n";
        return output;
    }

    public String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String printError(String errorMessage) {
        return errorMessage;
    }

    public String printMessage(String message) {
        return message;
    }

    public String printFound(ArrayList<Task> arr) {
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < arr.size(); i++) {
            output = output + (i + 1) + "." + arr.get(i) + "\n";
        }
        return output;
    }
}

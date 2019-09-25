package main;

import task.Task;

import java.util.ArrayList;

public class UI {
    public UI(){}

    public static void start() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");

    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void newTask(ArrayList<Task> tl) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("    " + tl.get(tl.size() - 1));
        System.out.println("Now you have " + tl.size() + " tasks in the list.");

    }

    public static void removedTask(Task task, int listSize){
        System.out.println(" Noted. I've removed this task: ");
        System.out.println("    " + task);
        System.out.println("Now you have " + listSize + " tasks in the list.");

    }

    public static void numTasks(ArrayList<Task> tl) {
        System.out.println("Now you have " + tl.size() + " tasks in the list.");

    }

    public static void done(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);

    }

    public static void findStart(){
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the matching tasks in your list:");
    }

    public static void endLine() {
        System.out.println("    ____________________________________________________________");
    }

}

package duke.DirectProcessor;

import duke.Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner userCommandTaker;

    public Ui() {
        userCommandTaker = new Scanner(System.in);
    }

    public void drawLine() {
        System.out.print("    ");
        for (int i = 0; i < 70; i++) System.out.print("_");
        System.out.print("\n");
    }

    public void showWelcome() {
        drawLine();
        System.out.println("     Hello, I'm duke.Duke.");
        System.out.println("     What can I do for you?");
        drawLine();
    }

    public void showError(String s) {
        System.out.println(s);
    }

    public void showLoadingError() {
        System.out.println("Unable to load previous task list. We are starting with a new one.");
    }

    public void showAddMessage(Task t) {
        System.out.println("     Got it. I have added this task:");
        System.out.println("       " + t.taskInfo());
        System.out.println("     You have now " + Task.getTotalNumber() + " tasks in the list.");
    }

    public void showDeleteMessage(Task t) {
        System.out.println("     Noted, Noted. I've removed this task: ");
        System.out.println("       " + t.taskInfo());
        System.out.println("     Now you have " + Task.getTotalNumber() + " tasks in the list");
    }

    public void showListMessage(ArrayList<String> tl) {
        for (int i = 0; i < tl.size(); i++) {
            System.out.println("       " + tl.get(i));
        }
    }

    public void showFinishMessage(Task t) {
        System.out.println("     Nice! I have set this task as done:");
        System.out.println("       " + t.taskInfo());
    }

    public void showFindMessage(ArrayList<String> tl) {
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < tl.size(); i++) {
            System.out.println("       " + tl.get(i));
        }
    }

    public void showExitMessage() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public String takeCommand() {
        return userCommandTaker.nextLine();
    }

}

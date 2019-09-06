package duke.logic;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner reader;
    public Ui() {
        reader = new Scanner(System.in);
    }

    public String readNextLine() {
        return reader.nextLine();
    }
    public String readNext() {
        return reader.next();
    }

    public void showLoadingError() {
        System.out.println("File not found.");
    }
    public void showLine() {
        StringBuilder lineBuilder = new StringBuilder("     ");
        for(int i = 0; i < 59; i++) {
            lineBuilder.append("_");
        }
        System.out.println(lineBuilder.toString());
    }
    public void separationline() {
        System.out.println();
    }
    public void showBye() {
        this.showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        this.showLine();
    }
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?" );
    }
    public void showDone(Task task) {
        this.showLine();
        System.out.println("     Nice! I've marked this duke.task as done:");
        System.out.println("       " + task);
        this.showLine();
        this.separationline();
    }
    public void showDelete(Task task, int size) {
        this.showLine();
        System.out.println("     Nice! I've removed this duke.task:");
        System.out.println("       " + task);
        System.out.println("      Now you have " + size + " tasks in the list.");
        this.showLine();
        this.separationline();
    }
    public void showAdd(Task task, int size) {
        this.showLine();
        System.out.println("      Got it. I've added this duke.task:");
        System.out.println("       " + task);
        System.out.println("      Now you have " + size + " tasks in the list.");
        this.showLine();
        this.separationline();
    }
    public void showMatchingTasks(ArrayList<Task> listOfMatches) {
        this.showLine();
        System.out.println("      Here are the matching tasks in your list:");
        int counter = 1;
        for(Task task : listOfMatches) {
            System.out.println("     " + counter + "." + task);
            counter++;
        }
        this.showLine();
        this.separationline();
    }

}

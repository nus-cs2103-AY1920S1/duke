package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scan = new Scanner(System.in);


    public void printIntro() {
        String logo = "\t ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t Hello from\n" + logo);
        printLine();
        printDuke("Hello! I'm Duke\n");
        printDuke("What can I do for you?\n");
        printLine();
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public String scanCmd() {
        return scan.nextLine();
    }

    public void printDuke(String toPrint) {
        System.out.print("    " + toPrint);
    }

    public void printList(ArrayList<Task> list) {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());

        }
    }

    public void printError(String error) {
        printDuke(error);
    }

}

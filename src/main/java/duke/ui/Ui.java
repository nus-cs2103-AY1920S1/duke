package duke.ui;

import duke.backend.ListManager;

import java.util.Scanner;

public class Ui {

    String logo;
    String bar;
    Scanner sc;

    /**
     * constructor for UI class that stores default logo, linebreak and Scanner object.
     */
    public Ui() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        this.bar = "\t_________________________________________";
        this.sc = new Scanner(System.in);
    }

    /**
     * method that produces welcome screen of project.
     */
    public void welcome() {
        System.out.print(logo);
        this.bar();
        System.out.print("\tHello! I'm Duke\n\tWhat can I do for you?\n");
        this.bar();
    }

    public void bar() {
        System.out.println(bar);
    }

    public String inputCommand() {
        return sc.nextLine();
    }

    /**
     * method that prints message for a successful adding of task to ArrayList.
     * @param listManager to access actual list of tasks.
     */
    public void successfulAdd(ListManager listManager) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + listManager.actualList.get(listManager.actualList.size() - 1));
        System.out.println("\tNow you have " + listManager.actualList.size() + " tasks in the list.");
    }

    public void exit() {
        System.out.println("\nBye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("File not found!");
    }
}

package util;

import java.util.Scanner;

public class Ui {

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()) {
            return sc.nextLine();
        }
        return "";
    }

    public static void showLine() {
        System.out.println("______________________________________________");
    }

    public static void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void byeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listMsg() {
        System.out.println("Here are the tasks in your list:");
    }

    public static void doneMsg() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    public static void unknownMsg() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void emptyTaskMsg(String s) {
        System.out.println("☹ OOPS!!! The description of a " + s + " cannot be empty.");
    }

    public static void invalidNumMsg() {
        System.out.println("☹ OOPS!!! You have entered an invalid number");
    }

    public static void outOfBoundMsg() {
        System.out.println("☹ OOPS!!! Out of range, the task does not exist");
    }

    public static void missingDeadlineMsg() {
        System.out.println("☹ OOPS!!! You must specify a deadline date");
    }

    public static void missingEventMsg() {
        System.out.println("☹ OOPS!!! You must specify a event date");
    }

    public static void deleteEmptyMsg() {
        System.out.println("☹ OOPS!!! You cannot delete an empty entry.");
    }

    public static void doneEmptyMsg() {
        System.out.println("☹ OOPS!!! This entry does not exist");
    }

    public static void showErrorMsg(Exception error) {
        System.out.println("☹ OOPS!!!" + error.toString());
    }

}
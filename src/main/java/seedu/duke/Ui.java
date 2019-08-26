package seedu.duke;

import java.util.Scanner;

public class Ui {

    public static void greet() {
        String message = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(message);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String showLine() {
        return "____________________________________________________________";
    }

    public static void exit() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
    }

    public static void showLoadingError() {
        System.out.println("Empty storage to load from.");
    }

    public static void printList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; (j < list.getSize()) && list.getTask(j) != null; j++) {
            System.out.println(j + 1 + "." + list.getTask(j));
        }
    }

    public static void printAddMsg() {
        System.out.println("Got it. I've added this task:");
    }

    public static void printNumTask(TaskList list) {
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    public static void printRemoveMsg() {
        System.out.println("Noted. I've removed this task:");
    }

    public static void printLatest(TaskList list) {
        System.out.println(list.getTask(list.getSize()-1));
    }

    public static String emptyDescriptionMsg(String s) {
        return ("☹ OOPS!!! The description of a " + s + " cannot be empty.");
    }

    public static String invalidInputMsg() {
        return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void printErrMsg(String s) {
        System.out.println(s);
    }


}

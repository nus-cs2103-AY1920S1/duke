package ui;

import javafx.collections.ObservableList;
import tasklist.Task;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Contains all the text ui used in the program.
 */

public class TextUi {
    private static final String DIVIDER = "    ____________________________________________________________";

    public TextUi() {

    }

    /**
     * ui for printing when a new a item has been added.
     * @param task full task string including type, status, description and date(if applicable)
     * @param size total number of tasks in the list
     */
    public void printAddedItem(String task, int size) {
        System.out.println(DIVIDER + "\n"
                + "     Got it. I've added this item:\n"
                + "       " + task + "\n"
                + "     Now you have " + size + " item in the list.\n"
                + DIVIDER);
    }

    /**
     * prints confirmation message.
     */
    public void printItemList() {
        System.out.println(DIVIDER + "\n"
                + "     Here are the items in your list:");
        System.out.println(DIVIDER);
    }

    public void printNoteList(String itemName){
        System.out.println(DIVIDER + "\n"
                + "     Here are the items in " + itemName + ":");
        System.out.println(DIVIDER);
    }

    /**
     * ui for indicating that a task has been marked complete.
     * @param completedtask full task name and description of the completed task
     */
    public void printCompletedTask(String completedtask) {
        System.out.println(DIVIDER + "\n"
                + "     Nice! I've marked this item as done: \n"
                + "       " + completedtask + "\n" + DIVIDER);
    }

    /**
     * ui for indicating that a task has been removed.
     * @param task full task description
     * @param size total number of tasks on the list
     */
    public void printRemovedItem(String task, int size) {
        System.out.println(DIVIDER + "\n"
                + "     Noted. I've removed this item: \n"
                + "       " + task + "\n"
                + "     Now you have " + (size) + " items in the list.\n"
                + "    ____________________________________________________________");
    }

    /**
     * prints the error message for when the command word is wrongly formatted.
     */
    public void printErrorMsg1() {
        System.out.println(DIVIDER + "\n"
                + "     OOPS!!! I'm sorry, but I don't know that command :-(\n"
                + DIVIDER);
    }

    /**
     * prints the error message for a general command failure.
     */
    public void printErrorMsg2() {
        System.out.println(DIVIDER + "\n"
                + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + DIVIDER);
    }

    /**
     * prints the error message for a wrongly formatted date.
     */
    public void printWrongDate() {
        System.out.println(DIVIDER + "\n"
                + "     OOPS!!! I'm sorry, but I don't understand that date :-(\n"
                + DIVIDER);
    }

    /**
     * prints the ui for listing the found tasks.
     * @param size represents number of found tasks
     */
    public void printFoundTasks(Integer size) {
        if (size == 0) {
            System.out.println(DIVIDER + "\n"
                    + "     OOPS!!! I'm sorry, but I couldn't find anything :-(\n"
                    + DIVIDER);
        } else {
            System.out.println(DIVIDER + "\n"
                    + "     There are " + size + " matching items in your list:");
            System.out.println(DIVIDER);
        }
    }

    public void printNoteRemoved(String note , String source, int size){
        System.out.println(DIVIDER + "\n"
                + "     Noted. I've removed this item: \n"
                + "       " + note + " from " + source +"\n"
                + "     Now you have " + (size) + " items in the list.\n"
                + DIVIDER);
    }

    public void printDescriptionError (){
        System.out.println(DIVIDER + "\n"
                + "     OOPS!!! I'm sorry but Description should not be empty");
        System.out.println(DIVIDER);
    }

}
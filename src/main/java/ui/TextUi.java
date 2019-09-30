package ui;

/**
 * Contains all the text ui used in the program.
 */

public class TextUi {
    private static final String DIVIDER = "    ________________________________________________________________________";

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

    /**
     * prints out the notes in the specified item.
     * @param itemName name of the parent item
     */
    public void printNoteList(String itemName) {
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
                + "     OOPS!!! I'm sorry, but I don't know what that means :-(\n"
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

    /**
     * message for removal of notes.
     * @param note note removed
     * @param source item that the removed note was from
     * @param size the remaining number of notes
     */
    public void printNoteRemoved(String note, String source, int size) {
        System.out.println(DIVIDER + "\n"
                + "     Noted. I've removed this item: \n"
                + "       " + note + " from " + source + "\n"
                + "     Now you have " + (size) + " items in the list.\n"
                + DIVIDER);
    }

    /**
     * Message for empty descriptions.
     */
    public void printDescriptionError() {
        System.out.println(DIVIDER + "\n"
                + "     OOPS!!! I'm sorry but Description should not be empty");
        System.out.println(DIVIDER);
    }

    /**
     * message for help.
     */
    public void printhelp() {
        System.out.println(DIVIDER + "\n"
                + "     add todo: `todo DESCRIPTION`\n"
                + "     add deadline: `deadline DESCRIPTION /by DATE`\n"
                + "     add event: `event DESCRIPTION /at DATE`\n"
                + "     add notebook: `notebook DESCRIPTION`\n"
                + "     find a task/notebook: `find KEYWORD(S)`\n"
                + "     mark a task as done: `done [TASKINDEX]`\n"
                + "     delete: `delete [TASKINDEX]`\n"
                + "     add a note:\n"
                + "          - `addnotes [TASKINDEX] CATEGORY | DESCRIPTION`\n"
                + "          - `addnotes [TASKINDEX] CATEGORY | DESCRIPTION /by DATE`\n"
                + "          - `addnotes [TASKINDEX] CATEGORY | DESCRIPTION /at DATE`\n"
                + "     delete note: `deletenotes [TASKINDEX] {NOTEINDEX}`\n"
                + "     find note: `findnotes KEYWORD(S)`\n"
                + "     list task/notebook: `list`\n"
                + "     list notes: `shownotes [TASKINDEX]`\n"
                + "     list of commands: `help`\n"
                + "     Please visit this website to view the full manual: https://lzw12345.github.io/duke/");
        System.out.println(DIVIDER);
    }

}
/**
 * Text Ui of the application.
 */
public class Ui {

    /** UI interface divider lines.**/
    private static final String DIVIDER_LINES = "    ____________________________________________";


    /**
     * Generates and print the welcome message upon the start of the application.
     * @return show welcome UI
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        logo += ("Hello! I'm Duke\n");
        logo += ("What can I do for you?\n");
        return logo;
    }

    /**
     * show the divider lines.
     * @return divider lines string
     */
    public String showLine() {
        return (DIVIDER_LINES);
    }

    /**
     * show loading error.
     * @return show loading error
     */
    public String showLoadingError() {
        return ("Loading Error. New tasklist will be created");
    }

    /**
     * list of commands available in this Duke program.
     * @return help command UI
     */
    public String helpcommand() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("======\n");
        stringBuilder.append("             *Help Command*\n");
        stringBuilder.append("1. todo (desc): input your todo task.\n");
        stringBuilder.append("2. event (desc) /at (place): input your event with description at a place\n");
        stringBuilder.append("3. deadline (desc) /by (time format: DD/MM/YYYY 24hour: input your task with"
                + " deadline with 24 hour format.\n");
        stringBuilder.append("4. list: list out all the current tasks recorder in Duke\n");
        stringBuilder.append("5. find (keywords): find a task by input some keywords.\n");
        stringBuilder.append("6. delete (task number): delete a task as indicated by task number.\n");
        stringBuilder.append("7. done (task number): mark a task as done, indicated by task number.\n");
        stringBuilder.append("8. sort desc/type: sort a task according to desc lexicographically or"
                + " sort a task according to type, todo first then event then deadline task.\n");
        stringBuilder.append("9. bye: terminate the program safely.\n");
        stringBuilder.append("10. help: Check commands.\n");
        stringBuilder.append("======\n");
        return stringBuilder.toString();
    }
}
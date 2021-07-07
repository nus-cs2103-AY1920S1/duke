public class HelpCommand extends Command {

    public HelpCommand(String command) {
        super(command);
    }

    /**
     * Execute help command. Display help command list on the display.
     * @param task Tasklist data
     * @param ui Ui interfaces and strings
     * @param storage stored file
     * @return help command display
     * @throws AssertionError if any of raw values are invalid
     */
    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws AssertionError {
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
        stringBuilder.append(ui.showLine());
        return stringBuilder.toString();
    }
}

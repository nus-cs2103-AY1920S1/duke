public class Duke {
    private static TaskList taskList;
    private static Storage storage = new Storage();
    private static Ui ui = new Ui();

    /**
     * the body of the main method, where Tasklist is initialised and the code runs.
     * @param input the next user input which decides what the code does.
     */
    public static String run(String input) {
        assert input != null: "userinput should not be null";
        Task[] tasks = new Task[100];
        taskList = new TaskList(tasks);
        storage.readSavedList(taskList);
        return Parser.parse(input, taskList, ui);
    }


    /**
     * Generate a response to user input.
     * @param input the next user input which decides what the response will be.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            System.exit(0);
        }
        String output = run(input);
        storage.save(taskList);
        assert !output.equals(""): "output should not be empty";
        return output;
    }
}
public class Duke {
    private static TaskList taskList;

//    public static void main(String[] args) throws DukeException {
//        run();
//    }

    /**
     * the body of the main method, where Tasklist is initialised and the code runs.
     */
    public static String run(String input) {
        Task[] tasks = new Task[100];
        taskList = new TaskList(tasks);
        Storage.readSavedList(taskList);
        return Parser.Parse(input, taskList);
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output = run(input);
        return output;
    }
}
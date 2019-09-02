import java.io.FileNotFoundException;

public class Parser {
    /**
     * storage and tasks variables dictates where to store and output
     */
    private Storage storage;
    private TaskList tasks;

    /**
     * constructor
     * @param storage is object is store and retrieve data for text file
     * @param tasks is object containing all tasks
     */
    public Parser(Storage storage, TaskList tasks){
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * clears the DukeOutput in case anything goes wrong, but does not clear list
     * @throws FileNotFoundException
     */
    public void EmptyOutput() throws FileNotFoundException {
        storage.emptyOutput();
        System.out.println("Output Emptied");
    }

    /**
     * marks tasks as done and prints the output
     * @param command is the string input that is processed
     */
    public void done(String command){
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            tasks.taskDone(val-1);
            System.out.println("Nice! I've marked this task as done: \n" + tasks.taskPrint(val-1));
            storage.printToOutput(tasks);
        } catch (Exception e) {
            System.out.println("Error, you have entered an invalid number");
        }
    }

    /**
     * deletes the task from TaskList
     * @param command is the string input that is processed
     */
    public void delete(String command){
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            System.out.println("Noted. I've removed this task:"+ "\n" + tasks.taskPrint(val-1) +
                    "\n"+ "Now you have " + (tasks.size()-1) + " tasks in the list.");
            tasks.remove(val - 1);
            storage.printToOutput(tasks);
        } catch (Exception e) {
            System.out.println("Error, you have entered an invalid number");
        }
    }

    /**
     * finds for the search results use inputs
     * @param command is the user input string
     */
    public void find(String command){
        String[]splitWords = command.split(" ",2);
        String wordToFind = splitWords[1];
        TaskList findResults = new TaskList();

        for(int i = 0; i < tasks.size(); i++){
            String taskCommand = tasks.get(i).getCommand();
            if (isSubstring(wordToFind, taskCommand) ){
                findResults.add(tasks.get(i));
            }else{}
        }
        if(findResults.isEmpty()){
            System.out.println("Sorry, we couldn't find any results!");
        }else{
            System.out.println("Here are the matching tasks in your list:");
            findResults.printForList();
        }

    }

    /**
     * to be used for the method find
     * @param s1 is the shorter string to be checked
     * @param s2 is the longer string that might contain s1
     * @return boolean value, whether s1 is a substring of s2
     */
    private boolean isSubstring(String s1, String s2) {
        int M = s1.length();
        int N = s2.length();

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (s2.charAt(i + j) != s1.charAt(j)) {
                    break;
                } else {}
            }
            if (j == M) {
                return true;
            } else {}
        }
        return false;
    }

    /**
     * Creates a new ToDo task
     * @param command is the user string input to be processed
     * @throws Exception in case user inputs in an incorrect format
     */
    public void createTodo(String command) throws Exception {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        if (midCommand.length() != 0) {
            tasks.add(new ToDo(midCommand));
            storage.printToOutput(tasks);
        } else {
            throw new Exception();
        }
    }

    /**
     * Creates a new deadline task
     * @param command is the user string input to be processed
     * @throws Exception in case user inputs in an incorrect format
     */
    public void createDeadline(String command) throws Exception {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        if (midCommand.length() != 0) {
            tasks.add(new Deadline(midCommand));
            storage.printToOutput(tasks);
        } else {
            throw new Exception();
        }
    }

    /**
     * Creates a new event task
     * @param command is the user string input to be processed
     * @throws Exception in case user inputs in an incorrect format
     */
    public void createEvent(String command) throws Exception {
        String[]splitWords = command.trim().split("\\s",2);
        String midCommand = splitWords[1].trim();

        if (midCommand.length() != 0) {
            tasks.add(new Event(midCommand));
            storage.printToOutput(tasks);
        } else {
            throw new Exception();
        }
    }
}

import java.io.FileNotFoundException;

public class Parser {
    /**
     * storage and tasks variables dictates where to store and output
     */
    private Storage storage; private TaskList tasks;
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
        String[]words = command.split(" ");
        try {
            int val = Integer.parseInt(words[1]);
            tasks.taskDone(val-1);
            System.out.println("Nice! I've marked this task as done: \n" + tasks.taskPrint(val-1));
            storage.printToOutput(tasks);
        } catch(Exception e) {
            System.out.println("Error, you have entered an invalid number");
        }
    }

    /**
     * deletes the task from TaskList
     * @param command is the string input that is processed
     */
    public void delete(String command){
        String[]words = command.split(" ");
        try {
            int val = Integer.parseInt(words[1]);
            System.out.println("Noted. I've removed this task:"+ "\n" + tasks.taskPrint(val-1) +
                    "\n"+ "Now you have " + (tasks.size()-1) + " tasks in the list.");
            tasks.remove(val - 1);
            storage.printToOutput(tasks);
        }catch(Exception e){
            System.out.println("Error, you have entered an invalid number");
        }
    }

    /**
     * Creates a new ToDo task
     * @param command is the user string input to be processed
     * @throws Exception in case user inputs in an incorrect format
     */
    public void todo(String command) throws Exception {
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
    public void deadline(String command) throws Exception {
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
    public void event(String command) throws Exception {
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

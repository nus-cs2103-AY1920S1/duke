
public class Parser {
    private static TaskList taskManager = new TaskList();
    private static Storage storageManager = new Storage("task.txt");

    /**
     * Checks if a command is valid  and responds to a command.
     *
     * @param input a command for Duke
     *
     */
    public static String checkCommand(String input) throws NoValidCommandException {

        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case "bye":
            return "Bye. Hope to see you again soon!";

        case "list":
            return taskManager.getTaskList();

        case "done":
            int doneIndex = Integer.parseInt(words[1]) - 1;
            String msgD = taskManager.markDone(doneIndex);
            storageManager.saveTaskUtil(taskManager);
            return msgD;

        case "delete":
            int delIndex = Integer.parseInt(words[1]) - 1;
            String msgDel = taskManager.deleteTask(delIndex);
            storageManager.saveTaskUtil(taskManager);
            return msgDel;

        case "deadline":
            String msg = taskManager.addDeadLine(words[1]);
            storageManager.saveTaskUtil(taskManager);
            return msg;

        case "event":
            String msgE;
            try {
                msgE = taskManager.addEvent(words[1]);
            } catch (IndexOutOfBoundsException ex) {
                msgE = "Either event description or event specific timing is missing.";
            }
            storageManager.saveTaskUtil(taskManager);
            return msgE;

        case "todo":
            String msgT;
            try {
                msgT = taskManager.addToDo(words[1]);
            } catch (IndexOutOfBoundsException ex) {
                msgT = "☹ OOPS!!! The description of a todo cannot be empty.";
            }
            storageManager.saveTaskUtil(taskManager);
            return msgT;

        default:
            throw new NoValidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}

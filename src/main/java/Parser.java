
public class Parser {
    private static TaskList taskManager = new TaskList();
    private static Storage storageManager = new Storage("task.txt");

    /**
     * understands and responds to a command
     *
     * @param input a command for Duke
     *
     */
    public static boolean checkCommand(String input) throws NoValidCommandException{

        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case "bye":
            System.out.println("Bye. Hope to " +
                    "see you again soon!");
            return false;

        case "list":
            taskManager.printTaskList();
            break;

        case "done":
            int doneIndex = Integer.parseInt(words[1]) - 1;
            taskManager.markDone(doneIndex);
            storageManager.saveTaskUtil(taskManager);
            break;

        case "delete":
            int delIndex = Integer.parseInt(words[1]) - 1;
            taskManager.deleteTask(delIndex);
            storageManager.saveTaskUtil(taskManager);
            break;

        case "deadline":
            taskManager.addDeadLine(words[1]);
            storageManager.saveTaskUtil(taskManager);
            break;

        case "event":
            try{
                taskManager.addEvent(words[1]);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Either event description or event specific timing is missing.");
            }
            storageManager.saveTaskUtil(taskManager);
            break;

        case "todo":
            try {
                taskManager.addToDo(words[1]);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            storageManager.saveTaskUtil(taskManager);
            break;

        default:
            throw new NoValidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }


}

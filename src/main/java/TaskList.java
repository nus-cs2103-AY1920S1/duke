import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> listOfTasks = new ArrayList<Task>();
    //protected static String file = "todo.txt";

    public TaskList() {

    }

    public TaskList(ArrayList<Task> list) {
        listOfTasks = list;
    }

    public void deleteCommand(String text) throws IOException {
        int num = text.indexOf(" ");
        int taskNumber = Integer.parseInt(text.substring(num + 1, num + 2));
        Task.printRemove();
        Ui.printDelete(taskNumber);
        System.out.println("before removing from listoftask: " + listOfTasks.get(1).toString());
        listOfTasks.remove(taskNumber - 1);
        System.out.println("before overwriting file and for loop: " + listOfTasks.get(1).toString());
        Storage.writeToFile(Storage.file, "");
        for (Task task : listOfTasks) {
            System.out.println("before storing to file again: " + task.toString());
            Storage.addToFile(Storage.file, task.toString());
        }
        Task.printNumOfTasks();
    }

    /**
     * Deletes everything off the task list.
     *
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void deleteAllCommand(String text) throws IOException {
        Storage.writeToFile(Storage.file, "");
        listOfTasks.clear();
        Ui.printLine();
        Ui.printIndent();
        System.out.println("Everything in your list has been removed! Add more tasks to get started again!!!");
        Ui.printLine();
    }

    public void toDoCommand(String text) throws IOException {
        Task.printGI();
        Ui.printIndent();
        int num = text.indexOf(" ");
        Task task = new Todo(text.substring(num + 1));
        System.out.println("  " + task.toString());
        listOfTasks.add(task);
        Storage.addToFile(Storage.file, task.toString());
        Task.printNumOfTasks();
    }

    public void eventCommand(String text) throws DukeException, IOException {
        int num = text.indexOf("/");
        int num1 = text.indexOf(" ");
        int dayDate = Integer.parseInt(text.substring(num + 4, num + 6));
        if (dayDate > 0 && dayDate <= 31) {
            Task task = new Event(text.substring(num1, num - 1),
                    text.substring(num + 4));
            String taskers = task.toString();
            if (!taskers.equals("Invalid date format!")) {

                Task.printGI();
                Ui.printIndent();
                System.out.println("  " + taskers);
                listOfTasks.add(task);
                Storage.addToFile(Storage.file, taskers);
                Task.printNumOfTasks();
            } else {
                Ui.printIndent();
                throw new DukeException(taskers);
            }
        } else {
            Ui.printLine();
            Ui.printIndent();
            throw new DukeException("Hmmm?? I'm sorry but there are at most 31 days in a month! " +
                    "And remember, no negative dates allowed as well!!! :)");
        }
    }

    public void deadlineCommand(String text) throws DukeException, IOException {
        int num = text.indexOf("/");
        int num1 = text.indexOf(" ");
        int dayDate = Integer.parseInt(text.substring(num + 4, num + 6));
        if (dayDate > 0 && dayDate <= 31) {
            Task task = new Deadline(text.substring(num1, num - 1),
                    text.substring(num + 4));
            String taskers = task.toString();
            if (!taskers.equals("Invalid date format!")) {
                Task.printGI();
                Ui.printIndent();
                System.out.println("  " + taskers);
                listOfTasks.add(task);
                Storage.addToFile(Storage.file, taskers);
                Task.printNumOfTasks();
            } else {
                Ui.printIndent();
                throw new DukeException(taskers);
            }
        } else {
            Ui.printLine();
            Ui.printIndent();
            throw new DukeException("Hmmm?? I'm sorry but there are at most 31 days in a month! " +
                    "And remember, no negative dates allowed as well!!! :)");
        }
    }
}

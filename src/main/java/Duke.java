import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException, DukeException {

        UI ui = new UI();
        ui.printGreeting();

        ArrayList<Task> taskList = new ArrayList<Task>();

        Storage storage = new Storage(taskList);
        storage.LoadFile();

        Scanner sc = new Scanner(System.in);
        String TaskLine = sc.nextLine();

        outLoop:
        while (TaskLine != null) {
            String[] words = TaskLine.split(" ");
            switch (words[0]) {
            case ("bye") :
                ui.printBye();
                break outLoop;

            case ("list") :
                ui.printList(taskList);
                TaskLine = sc.nextLine();
                break;

            case ("done") :
                int taskNo = Integer.parseInt(TaskLine.substring(5));
                taskList.get(taskNo - 1).markAsDone(taskList.get(taskNo - 1));
                ui.printDone(taskList.get(taskNo - 1));
                TaskLine = sc.nextLine();
                break;

            case ("todo") :
                if (TaskLine.length() == 4) {
                    ui.throwInputError("todo");
                } else {
                    Task todoTask = new Todo(TaskLine.substring(5));
                    taskList.add(todoTask);
                    ui.printAddTask(todoTask,taskList.size());
                }
                TaskLine = sc.nextLine();
                break;

            case ("deadline") :
                if (TaskLine.length() == 8) {
                    ui.throwInputError("deadline");
                } else {
                    int indexOfSlash = TaskLine.indexOf("/");
                    Task deadlineTask = new Deadline(TaskLine.substring(9, indexOfSlash - 1), TaskLine.substring(indexOfSlash + 4));
                    taskList.add(deadlineTask);
                    ui.printAddTask(deadlineTask,taskList.size());
                }
                TaskLine = sc.nextLine();
                break;

            case ("event") :
                if (TaskLine.length() == 5) {
                    ui.throwInputError("event");
                } else {
                    int indexOfSlash = TaskLine.indexOf("/");
                    Task eventTask = new Event(TaskLine.substring(6, indexOfSlash - 1), TaskLine.substring(indexOfSlash + 4));
                    taskList.add(eventTask);
                    ui.printAddTask(eventTask,taskList.size());
                }
                TaskLine = sc.nextLine();
                break;

            case ("delete") :
                int taskNoDelete = Integer.parseInt(TaskLine.substring(7));
                Task deletedTask = taskList.get(taskNoDelete - 1);
                ui.printDelete(deletedTask, taskList.size());
                taskList.remove(taskNoDelete - 1);
                TaskLine = sc.nextLine();
                break;

            default :
                ui.printIDK();
                TaskLine = sc.nextLine();
                break;
            }
        }
        storage.UpdateFile();
    }
}

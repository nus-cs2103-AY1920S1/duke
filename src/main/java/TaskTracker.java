import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

class TaskTracker {

    private ArrayList<Task> taskList = new ArrayList<>();

    TaskTracker(File data) {
        try {
            Scanner scanFile = new Scanner(data);
            while (scanFile.hasNext()) {
                String[] taskItem = scanFile.nextLine().split(" | ");
                processData(taskItem);
            }
        } catch (Exception err) {
            System.err.println(err);
        }
    }

    private void processData(String[] task) {
        String action = task[0];
        boolean isDone = task[2].equals("1");
        switch (action) {
        case "T":
            Task todo = new Todo(task[4]);
            if (isDone) {
                todo.markAsDone();
            }
            taskList.add(todo);
            break;
        case "E":
            Task event = new Event(task[4], task[6]);
            if (isDone) {
                event.markAsDone();
            }
            taskList.add(event);
            break;
        case "D":
            Task deadline = new Deadline(task[4], task[6]);
            if (isDone) {
                deadline.markAsDone();
            }
            taskList.add(deadline);
            break;
        }
    }

    void process(String command) {
        String action = command.split(" ")[0];
        try {
            switch (action) {
            case "list":
                list();
                break;
            case "todo":
                processTodo(command);
                break;
            case "event":
                processEvent(command);
                break;
            case "deadline":
                processDeadline(command);
                break;
            case "done":
                done(command);
                break;
            case "delete":
                delete(command);
                break;
            default:
                throw new DukeException("\n    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "    ____________________________________________________________");
            }
        } catch (DukeException err) {
            System.err.println(err);
        }
    }

    private void delete(String command) {
        int numberInt = Integer.parseInt(command.substring(7)) - 1;
        Task toBeRemoved = taskList.get(numberInt);
        taskList.remove(numberInt);
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "       " + toBeRemoved + "\n" +
                "     Now you have " + taskList.size() + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    private void list() {
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    private void done(String command) {
        int numberInt = Integer.parseInt(command.substring(5)) - 1;
        taskList.get(numberInt).markAsDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       " + taskList.get(numberInt) + "\n" +
                "    ____________________________________________________________");
    }

    private void processTodo(String command) throws DukeException {
        try {
            String item = command.substring(5); // remove "todo "
            Task task = new Todo(item);
            taskList.add(task);
            notifyAdded(task);
        } catch (Exception err) {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
    }

    private void processEvent(String command) {
        String removeCommand = command.substring(6);
        String item = removeCommand.split(" /at ")[0];
        String time = removeCommand.split(" /at ")[1];
        Task task = new Event(item, time);
        taskList.add(task);
        notifyAdded(task);
    }

    private void processDeadline(String command) {
        String removeCommand = command.substring(9);
        String item = removeCommand.split(" /by")[0];
        String time = removeCommand.split(" /by ")[1];
        Task task = new Deadline(item, time);
        taskList.add(task);
        notifyAdded(task);
    }

    private void notifyAdded(Task task) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       " + task + "\n" +
                "     Now you have " + taskList.size() + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    String end() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                output.append(taskList.get(i).saveData());
                break;
            }
            output.append(taskList.get(i).saveData()).append("\n");
        }
        return output.toString();
    }
}

package duke.task;

import duke.exception.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    static SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    static SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMMM yyyy hh':'mma");

    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    private void addTask(String task, TaskType type, boolean printText) throws ParseException, EmptyEventDscDukeException, EmptyDeadlineDscDukeException, NoDateDukeException {
        Task addedTask;
        String date;
        switch (type) {
            case TODO:
                addedTask = new TodoTask(task);
                taskList.add(addedTask);
                if (printText) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println(addedTask);
                }
                break;
            case DEADLINE:
                if (task.indexOf('/') == -1) {
                    throw new NoDateDukeException("/ wasn't found");
                }
                if (task.indexOf('/') == 0) {
                    throw new EmptyDeadlineDscDukeException("Description for deadline is empty.");
                }
                date = task.substring(task.indexOf('/') + 1);
                if (date.equals("")) {
                    throw new NoDateDukeException("No date provided");
                }
                date = date.substring(date.indexOf(" ") + 1);
                addedTask = new Deadline(
                    task.substring(0, task.indexOf('/') - 1),
                    TaskList.inputDateFormat.parse(date)
                );
                taskList.add(addedTask);
                if (printText) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println(addedTask);
                }
                break;
            case EVENT:
                if (task.indexOf('/') == -1) {
                    throw new NoDateDukeException("No date found");
                }
                if (task.indexOf('/') == 0) {
                    throw new EmptyEventDscDukeException("Description for event is empty.");
                }
                date = task.substring(task.indexOf('/') + 1);
                if (date.equals("")) {
                    throw new NoDateDukeException("No date provided");
                }
                date = date.substring(date.indexOf(" ") + 1);
                addedTask = new Event(
                        task.substring(0, task.indexOf('/') - 1),
                        TaskList.inputDateFormat.parse(date)
                );
                taskList.add(addedTask);
                if (printText) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println(addedTask);
                }
                break;
            default:
                break;
        }
        if (printText) {
            printTotalTasks();
        }
    }
    private void listAllTasks() {
        int total = taskList.size();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < total; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }
    public void checkTask(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.taskDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    private void deleteTask(int taskIndex) throws InvalidTaskIndexDukeException {
        if (taskIndex < 0 || taskIndex > taskList.size() - 1) {
            throw new InvalidTaskIndexDukeException(taskIndex + " exceeds the range of taskList.");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(taskIndex));
        taskList.remove(taskIndex);
        printTotalTasks();
    }

    public String saveInfo() {
        StringBuilder info = new StringBuilder();
        boolean isFirstIteration = true;
        for (Task t : taskList) {
            if (isFirstIteration) {
                info.append(t.saveInfo());
                isFirstIteration = false;
            } else {
                info.append(System.getProperty("line.separator")).append(t.saveInfo());
            }

        }
        return info.toString();
    }

    private void printTotalTasks() {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public boolean parseInput(String input, boolean printText) throws DukeException, ParseException {
        String[] splitInput = input.split(" ");
        boolean dukeIsOn = true;
        switch (splitInput[0]) {
            case "list":
                this.listAllTasks();
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                dukeIsOn = false;
//                saveDuke(this.filePath, this.saveInfo());
                break;
            case "done":
                this.checkTask(Integer.parseInt(splitInput[1]) - 1);
                break;
            case "todo":
                if (input.length() <= 4) {
                    throw (new EmptyTodoDscDukeException("todo task has empty description."));
                }
                this.addTask(input.substring(5), TaskType.TODO, printText);
                break;
            case "deadline":
                if (input.length() <= 8) {
                    throw (new EmptyDeadlineDscDukeException("deadline task has empty description."));
                }
                this.addTask(input.substring(9), TaskType.DEADLINE, printText);
                break;
            case "event":
                if (input.length() <= 5) {
                    throw (new EmptyEventDscDukeException("event task has empty description."));
                }
                this.addTask(input.substring(6), TaskType.EVENT, printText);
                break;
            case "delete":
                if (input.length() <= 6) {
                    throw new InvalidTaskIndexDukeException("No task number was given.");
                }
                this.deleteTask(Integer.parseInt(splitInput[1]) - 1);
                break;
            case "find":
                if (input.length() <= 4) {
                    throw new DukeException("no keyword to search for.");
                }
                String searchKey = input.substring(5);
                ArrayList<Task> matchedList = new ArrayList<>();
                for (Task t : taskList) {
                    String dsc = t.getTaskDetails();
                    if (dsc.indexOf(searchKey) != -1) {
                        matchedList.add(t);
                    }
                }
                int lenOfMatchedList = matchedList.size();
                System.out.println("Here are the matching tasks in your list");
                for (int i = 1; i <= lenOfMatchedList; i++) {
                    System.out.println(i + ". " + matchedList.get(i-1));
                }
                break;
            default:
                throw(new UnknownCmdDukeException(splitInput[0] + " is not a known command."));
        }
        return dukeIsOn;
    }
}

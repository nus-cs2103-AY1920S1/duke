package duke;

import duke.task.Task;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Represents an interface for user interaction.
 */
public class Ui {

    private String line = "    ____________________________________________________________";

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private void formatPrint(String[] lists) {
        System.out.println(line);
        for (String s : lists) {
            System.out.println(format(s));
        }
        System.out.println(line);
    }

    private void formatPrint(String s) {
        System.out.println(line);
        System.out.println(format(s));
        System.out.println(line);
    }

    private String format(String s) {
        return "     " + s;
    }

    private void echo(String s) {
        formatPrint(s);
    }

    /**
     * Sends greetings to user.
     */
    String greet() {
        String response = "Hello! I'm Duke \n     What can I do for you?";
        formatPrint(response);
        return response;
    }

    /**
     * Says goodbye to user.
     */
    public String bye() {
        String response = "Bye. Hope to see you again soon!";
        formatPrint(response);
        return response;
    }

    /**
     * Prints all tasks.
     * @param list a TaskList object that contains a list of tasks
     * @throws DukeException if description of tasks cannot be parsed
     */
    public String printList(TaskList list) throws DukeException {
        ArrayList<Task> tasks = list.tasks;
        StringBuilder response = new StringBuilder();
        if (tasks.size() == 0) {
            response.append("There is no task in your list");
            throw new DukeException("There is no task in your list");
        }
        try {
            System.out.println(line);
            System.out.println(format("Here are the tasks in your list:"));
            response.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                response.append(i + 1).append(".").append(t.repr()).append("\n");
                System.out.println("  " + format(i + 1 + "." + t.repr()));
            }
            System.out.println(line);
        } catch (ParseException e) {
            response.append("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
            throw new DukeException("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
        } finally {
            return String.valueOf(response);
        }
    }

    /**
     * Tells user some tasks have been marked as done.
     * @param list a TaskList object that contains a list of tasks
     * @param nums numbers of tasks to be marked as done
     * @throws ParseException if description of task connot be parsed
     */
    public String done(TaskList list, Integer... nums) throws ParseException {
        ArrayList<String> sentences = getSentences(list, nums, "Nice! I've marked these tasks as done: ");
        formatPrint(sentences.toArray(new String[0]));
        return getResponse(sentences);
    }

    private String getResponse(ArrayList<String> sentences) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < sentences.size(); i++) {
            response.append(sentences.get(i));
            if (i == sentences.size() - 1) {
                break;
            }
            response.append("\n");
        }
        return response.toString();
    }

    private ArrayList<String> getSentences(TaskList list, Integer[] nums, String start) throws ParseException {
        ArrayList<Task> tasks = list.tasks;
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add(start);
        for (Integer num : nums) {
            String description = tasks.get(num - 1).repr();
            sentences.add("  " + description);
        }
        return sentences;
    }

    /**
     * Tells user some tasks have been deleted.
     * @param list a TaskList object that contains a list of tasks
     * @param nums numbers of tasks to be deleted
     * @throws ParseException if description of task connot be parsed
     */
    public String delete(TaskList list, Integer... nums) throws ParseException, DukeException {
        for (int i : nums) {
            if (i <= 0 || i > list.tasks.size()) {
                throw new DukeException("Task number out of range");
            }
        }
        ArrayList<String> sentences = getSentences(list, nums, "Noted. I've removed these tasks:");
        int count = list.tasks.size() - nums.length;
        switch (count) {
        case 0:
            sentences.add("Now you have no task in the list.");
            break;
        case 1:
            sentences.add("Now you have 1 task in the list.");
            break;
        default:
            sentences.add("Now you have " + count + " tasks in the list.");
            break;
        }
        formatPrint(sentences.toArray(new String[0]));
        return getResponse(sentences);
    }

    /**
     * Tells user a task has been added.
     * @param list a TaskList object that contains a list of tasks
     * @throws DukeException if description of task connot be parsed
     */
    public String add(TaskList list) throws DukeException {
        String response = "";
        ArrayList<Task> tasks = list.tasks;
        try {
            Task newTask = tasks.get(tasks.size() - 1);
            String str = format(newTask.repr());
            System.out.println(line);
            System.out.println(format("Got it. I've added this task: "));
            System.out.println("  " + str);
            response += "Got it. I've added this task: \n  " + str + "\n";
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | ParseException ex) {
            response = "Task description should be of format \"context /prep dd/MM/yyyy HHmm\"";
            throw new DukeException(response);
        }
        if (tasks.size() == 1) {
            System.out.println(format("Now you have 1 task in the list."));
            response += "Now you have 1 task in the list.";
        } else {
            System.out.println(format("Now you have " + tasks.size() + " tasks in the list."));
            response += "Now you have " + tasks.size() + " tasks in the list.";
        }
        System.out.println(line);
        return response;
    }


    /** Prints matching list.
     * @param targets a TaskList of matching tasks
     * @throws DukeException if there is no matching task or task description cannot be parsed
     */
    public String printFind(TaskList targets) throws DukeException {
        StringBuilder response = new StringBuilder();
        ArrayList<Task> tasks = targets.tasks;
        if (tasks.size() == 0) {
            response.append("There is no matching task in your list");
            throw new DukeException(response.toString());
        }
        try {
            System.out.println(line);
            System.out.println(format("Here are the matching tasks in your list:"));
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                System.out.println("  " + format(i + 1 + "." + t.repr()));
                response.append("  ").append(i + 1).append(".").append(t.repr()).append("\n");
            }
            System.out.println(line);
        } catch (ParseException e) {
            response = new StringBuilder("Task description should be of format \"context /prep dd/MM/yyyy HHmm\"");
            throw new DukeException(response.toString());
        } finally {
            return response.toString();
        }
    }

    /**
     * Tells user tasks connot be loaded from hard disk.
     */
    public String showLoadingError() {
        String response = "Cannot load files";
        formatPrint(response);
        return response;
    }

    /**
     * Tells user a DukeException has occurred.
     * @param ex the exception whose message is to be printed
     */
    public String showDukeException(DukeException ex) {
        String response = ex.getMessage();
        formatPrint(response);
        return response;
    }

    /**
     * Tells user a ParseError has occurred.
     */
    public String showParseError() {
        String response = "Task description should be of format \"context /prep dd/MM/yyyy HHmm\"";
        formatPrint(response);
        return response;
    }

}
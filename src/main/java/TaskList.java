import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TaskList {
    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy HHmm");
    private List<Task> taskList;
    private UI ui;

    public TaskList(UI ui) {
        this.taskList = new ArrayList<>();
        this.ui = ui;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public int getSize() {
        return taskList.size();
    }

    public void addTask(String command) throws DukeException{
        ui.horizontalLine();
        List<String> commandList = new ArrayList<>(Arrays.asList(command.split(" ")));
        String stringHolder = (commandList.remove(0));
        List<String> listHolder = new ArrayList<>(commandList);
        if (stringHolder.startsWith("todo")) {
            stringHolder = (String.join(" ", commandList));
            if (!stringHolder.isEmpty()) {
                Task taskHolder = new ToDo(stringHolder, false);
                taskList.add(taskHolder);
                System.out.println("Got it. I've added this task:");
                System.out.println("  [T][\u2718] " + stringHolder);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        } else if (stringHolder.startsWith("deadline")) {
            stringHolder = commandList.remove(0);
            listHolder.remove(0);
            for (String i : listHolder) {
                if (i.equals("/by")) {
                    commandList.remove(0);
                    break;
                } else {
                    stringHolder = stringHolder + " " + commandList.remove(0);
                }
            }

            String date = commandList.remove(0);
            listHolder.clear();
            listHolder.addAll(commandList);
            for (String i : listHolder) {
                date = date + " " + commandList.remove(0);
            }

            Date dateHolder;
            try {
                dateHolder = DATE_FORMAT.parse(date);
            } catch (ParseException e) {
                throw new IODukeException("Please enter date in this format: d/m/y HHmm");
            }

            Task taskHolder = new Deadline(stringHolder, dateHolder, false);
            taskList.add(taskHolder);

            System.out.println("Got it. I've added this task:");
            System.out.println("  [D][\u2718] " + stringHolder + " (by: " + date + ")");
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
            stringHolder = commandList.remove(0);
            listHolder.remove(0);
            for (String i : listHolder) {
                if (i.equals("/at")) {
                    commandList.remove(0);
                    break;
                } else {
                    stringHolder = stringHolder + " " + commandList.remove(0);
                }
            }

            String date = commandList.remove(0);
            listHolder.clear();
            listHolder.addAll(commandList);
            for (String i : listHolder) {
                date = date + " " + commandList.remove(0);
            }

            Date dateHolder;
            try {
                dateHolder = DATE_FORMAT.parse(date);
            } catch (ParseException e) {
                throw new IODukeException("Please enter date in this format: 2/12/2019 1800");
            }

            Task taskHolder = new Event(stringHolder, dateHolder, false);
            taskList.add(taskHolder);

            System.out.println("Got it. I've added this task:");
            System.out.println("  [E][\u2718] " + stringHolder + " (at: " + date + ")");
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
        ui.horizontalLine();
        System.out.println();
    }

    public void doneTask(int index) {
        index = index - 1;
        taskList.get(index).changeStatusTrue();
        ui.horizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + taskList.get(index).getType() + "]" + "[" + taskList.get(index).getStatusIcon() + "] " + taskList.get(index).getDescription() + taskList.get(index).getDate());
        ui.horizontalLine();
        System.out.println();
    }

    public void deleteTask(int index) {
        index = index - 1;
        Task taskHolder = taskList.remove(index);
        ui.horizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  [" + taskHolder.getType() + "]" + "[" + taskHolder.getStatusIcon() + "] " + taskHolder.getDescription() + taskHolder.getDate());
        ui.horizontalLine();
        System.out.println();
    }
}

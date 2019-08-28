import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        try {
            duke.run();
        } catch (DukeException e) {
            System.out.println(e);
        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    public void run() throws DukeException, FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();
        File taskFile = new File("task.txt");

        if (taskFile.exists()) {
            Scanner scannerTask = new Scanner(taskFile);
            while (scannerTask.hasNext()) {
                String taskText = scannerTask.nextLine();
                parseTextToTask(taskText, list);
            }
        } else {
            taskFile.createNewFile();
        }

        while (sc.hasNext()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                if (list.size() == 0) {
                    System.out.println("Nothing added yet");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println("" + i + "." + list.get(i - 1));
                    }
                }
            } else if (str.substring(0, 4).equals("done")) {
                Integer taskNum = Integer.valueOf(str.substring(5));
                Task currTask = list.get(taskNum - 1);
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:" + "\n" + "[\u2713] " + currTask.getDescription());
                updateFile(taskFile, list);
            } else if (str.substring(0, 4).equals("todo")) {
                if (str.length() == 4) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    addTodoTaskAndPrintAndUpdateFile(str, list, taskFile);
                }
            } else if (str.length() >= 8 && str.substring(0, 8).equals("deadline")) {
                if (str.length() == 8) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    addDeadlineTaskAndPrintAndUpdateFile(str, list, taskFile);
                }
            } else if (str.length() >= 5 && str.substring(0, 5).equals("event")) {
                addEventTaskAndPrintAndUpdateFile(str, list, taskFile);
            } else if (str.equals("blah")) {
                throw new DukeException("OOPS!!! I'm sorry, but i don't know what that means :-(");
            } else if (str.substring(0, 6).equals("delete")) {
                Integer index = Integer.valueOf(str.substring(7));
                System.out.println("Noted. I've removed this task:");
                System.out.println(list.get(index));
                list.remove((int)index);
                System.out.println("Now you have " + list.size() + " tasks in the list");
                updateFile(taskFile, list);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void addDeadlineTaskAndPrintAndUpdateFile(String input, ArrayList<Task> list, File file) throws IOException {
        String message = "Got it. I've added this task:";
        int index = input.indexOf('/') + 1;
        Task deadlineTask = new Task(input.substring(9, index - 2), "deadline");
        list.add(deadlineTask);
        String preposition = input.substring(index, index + 2);
        String time = "(" + preposition + ": " + input.substring(index + 3) + ")";
        deadlineTask.addTime(time);
        System.out.println(message);
        System.out.println(" " + deadlineTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        appendToFile(file, deadlineTask);
    }

    public void addTodoTaskAndPrintAndUpdateFile(String input, ArrayList<Task> list, File file) throws IOException {
        String message = "Got it. I've added this task:";
        Task toDoTask = new Task(input.substring(5), "todo");
        list.add(toDoTask);
        System.out.println(message);
        System.out.println(" " + toDoTask);
        System.out.println("Now you have " + list.size() + " tasks in the list");
        appendToFile(file, toDoTask);
    }

    public void addEventTaskAndPrintAndUpdateFile(String input, ArrayList<Task> list, File file) throws IOException{
        String message = "Got it. I've added this task:";
        int index = input.indexOf('/') + 1;
        Task eventTask = new Task(input.substring(6, index - 2), "event");
        list.add(eventTask);
        String preposition = input.substring(index, index + 2);
        String time = "(" + preposition + ": " + input.substring(index + 3) + ")";
        eventTask.addTime(time);
        System.out.println(message);
        System.out.println(" " + eventTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        appendToFile(file, eventTask);
    }

    public void parseTextToTask(String taskText, ArrayList<Task> list) {
        if (taskText.substring(0, 1).equals("T")) {
            Task task = new Task(taskText.substring(8), "todo");
            if (taskText.substring(3,4).equals("1")) {
                task.markAsDone();
            }
            list.add(task);
        } else if (taskText.substring(0, 1).equals("D")) {
            Task task = new Task(taskText.substring(8), "deadline");
            if (taskText.substring(3,4).equals("1")) {
                task.markAsDone();
            }
            list.add(task);
        } else if (taskText.substring(0, 1).equals("E")) {
            Task task = new Task(taskText.substring(8), "event");
            if (taskText.substring(3,4).equals("1")) {
                task.markAsDone();
            }
            list.add(task);
        }
    }

    private void appendToFile(File file, Task task) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        String isDone = task.isDone() ? "1" : "0";
        String text = task.getTypeOfTask() + " | " + isDone + " | " + task.getDescription();
        if (!task.getTime().equals("")) {
            text = text + " " + task.getTime();
        }
        fw.write(text + "\n");
        fw.close();
    }

    private void updateFile(File file, ArrayList<Task> list) throws IOException {
        FileWriter writer = new FileWriter(file, false);
        String text = "";
        for(Task task: list) {
            String isDone = task.isDone() ? "1" : "0";
            text = text + task.getTypeOfTask() + " | " + isDone + " | " + task.getDescription();
            if (!task.getTime().equals("")) {
                text = text + " " + task.getTime();
            }
            text = text + System.lineSeparator();
        }
        writer.write(text);
        writer.close();
    }

}

class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

}

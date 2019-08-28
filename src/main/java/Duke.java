import java.io.IOException;
import java.text.ParseException;
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
            System.out.println(e.getMessage());
        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.print(e);
        } catch (ParseException e) {
            System.out.print(e);
        }
    }

    public void run() throws DukeException, FileNotFoundException, IOException, ParseException {
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
                    String message = "Got it. I've added this task:";
                    Task toDoTask = new Todo(str.substring(5));
                    list.add(toDoTask);
                    System.out.println(message);
                    System.out.println(" " + toDoTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                    appendToFile(taskFile, toDoTask);
                }
            } else if (str.length() >= 8 && str.substring(0, 8).equals("deadline")) {
                if (str.length() == 8) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String message = "Got it. I've added this task:";
                    int index = str.indexOf('/') + 1;
                    String time = str.substring(index + 3);
                    Task deadlineTask = new Deadline(str.substring(9, index - 2),time);
                    list.add(deadlineTask);
                    System.out.println(message);
                    System.out.println(" " + deadlineTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    appendToFile(taskFile, deadlineTask);
                }
            } else if (str.length() >= 5 && str.substring(0, 5).equals("event")) {
                String message = "Got it. I've added this task:";
                int index = str.indexOf('/') + 1;
                String time = str.substring(index + 3);
                Task eventTask = new Event(str.substring(6, index - 2), time);
                list.add(eventTask);
                System.out.println(message);
                System.out.println(" " + eventTask);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                appendToFile(taskFile, eventTask);
            } else if (str.equals("blah")) {
                throw new DukeException("OOPS!!! I'm sorry, but i don't know what that means :-(");
            } else if (str.substring(0, 6).equals("delete")) {
                Integer index = Integer.valueOf(str.substring(7));
                System.out.println("Noted. I've removed this task:");
                System.out.println(list.get(index - 1));
                list.remove((int)index - 1);
                System.out.println("Now you have " + list.size() + " tasks in the list");
                updateFile(taskFile, list);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void parseTextToTask(String taskText, ArrayList<Task> list) throws ParseException {
        if (taskText.substring(0, 1).equals("T")) {
            Task task = new Todo(taskText.substring(8));
            if (taskText.substring(3,4).equals("1")) {
                task.markAsDone();
            }
            list.add(task);
        } else if (taskText.substring(0, 1).equals("D")) {
            String descriptionAndTime = taskText.substring(8);
            int index = descriptionAndTime.indexOf('|');
            String description = descriptionAndTime.substring(0, index - 1);
            String time = descriptionAndTime.substring(index + 2);
            Task task = new Deadline(description, time);
            if (taskText.substring(3,4).equals("1")) {
                task.markAsDone();
            }
            list.add(task);
        } else if (taskText.substring(0, 1).equals("E")) {
            String descriptionAndTime = taskText.substring(8);
            int index = descriptionAndTime.indexOf('|');
            String description = descriptionAndTime.substring(0, index - 1);
            String time = descriptionAndTime.substring(index + 2);
            Task task = new Event(description, time);
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
        if (task.getTypeOfTask().equals("D")) {
            text = text + " | " + ((Deadline)task).getTime() + System.lineSeparator();
        } else if (task.getTypeOfTask().equals("E")) {
            text = text + " | " + ((Event)task).getTime() + System.lineSeparator();
        } else {
            text = text + System.lineSeparator();
        }
        fw.write(text);
        fw.close();
    }

    private void updateFile(File file, ArrayList<Task> list) throws IOException {
        FileWriter writer = new FileWriter(file, false);
        String text = "";
        for(Task task: list) {
            String isDone = task.isDone() ? "1" : "0";
            text = text + task.getTypeOfTask() + " | " + isDone + " | " + task.getDescription();
            if (task.getTypeOfTask().equals("D")) {
                text = text + " | " + ((Deadline)task).getTime() + System.lineSeparator();
            } else if (task.getTypeOfTask().equals("E")) {
                text = text + " | " + ((Event)task).getTime() + System.lineSeparator();
            } else {
                text = text + System.lineSeparator();
            }
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

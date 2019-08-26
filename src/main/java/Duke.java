import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> myList = new ArrayList<>();

    public static void main(String[] args) {
        loadTaskList();
        Scanner in = new Scanner(System.in);
        printMessage("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = in.nextLine();
            try {
                if (input.equals("bye")) {
                    printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (input.contains("todo")) {
                    String taskName;
                    try {
                        taskName = input.substring(5);
                    } catch (StringIndexOutOfBoundsException e) {
                        taskName = "";
                    }
                    addTask(new Todo(taskName));
                } else if (input.contains("deadline")) {
                    String taskName;
                    try {
                        if (input.contains("/")) {
                            taskName = input.substring(9, input.indexOf("/") - 1);
                        } else {
                            taskName = input.substring(9);
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        taskName = "";
                    }
                    String param;
                    if (input.contains("/by")) {
                        param = input.substring(input.indexOf("/by") + 4);
                    } else {
                        param = "";
                    }
                    addTask(new Deadline(taskName, parseDate(param)));
                } else if (input.contains("event")) {
                    String taskName;
                    try {
                        if (input.contains("/")) {
                            taskName = input.substring(6, input.indexOf("/") - 1);
                        } else {
                            taskName = input.substring(6);
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        taskName = "";
                    }
                    String param;
                    if (input.contains("/at")) {
                        param = input.substring(input.indexOf("/at") + 4);
                    } else {
                        param = "";
                    }
                    addTask(new Event(taskName, parseDate(param)));
                } else if (input.equals("list")) {
                    listTask();
                } else if (input.contains("done")) {
                    markTaskAsDone(Integer.valueOf(input.substring(5)));
                } else if (input.contains("delete")) {
                    deleteTask(Integer.valueOf(input.substring(7)));
                } else {
                    printException(new DukeException("I'm sorry, but I don't know what that means :-("));
                }
            } catch (DukeException e) {
                printException(e);
            }
        }
        in.close();
    }

    private static void listTask() {
        StringBuilder myBuilder = new StringBuilder();
        myBuilder.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= myList.size(); i++) {
            Task myTask = myList.get(i - 1);
            myBuilder.append(i + "." + myTask);
            if (i < myList.size()) {
                myBuilder.append("\n");
            }
        }
        printMessage(myBuilder.toString());
    }

    private static void addTask(Task myTask) {
        myList.add(myTask);
        saveTaskList();
        printMessage("Got it. I've added this task: \n  " + myTask + "\nNow you have " + pluralize("task", myList.size()) + " in the list.");
    }

    private static void deleteTask(int myNum) throws DukeException {
        try {
            Task myTask = myList.get(myNum - 1);
            printMessage("Noted. I've removed this task: \n  " + myTask + "\nNow you have " + pluralize("task", myList.size() - 1) + " in the list.");
            myList.remove(myNum - 1);
            saveTaskList();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds.");
        }
    }

    private static void markTaskAsDone(int myNum) throws DukeException {
        try {
            Task myTask = myList.get(myNum - 1);
            myTask.markAsDone();
            printMessage("Nice! I've marked this task as done:\n  " + myTask);
            saveTaskList();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds.");
        }
    }

    private static void printMessage(String message) {
        String[] messages = message.split("\n");
        System.out.println("    ____________________________________________________________");
        for (String line : messages) {
            System.out.println("     " + line);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    private static void printException(Exception e) {
        printMessage("☹ OOPS!!! " + e.getMessage());
    }

    private static String pluralize(String item, Integer quantity) {
        if (quantity == 1) {
            return "1 " + item;
        } else {
            return quantity + " " + item + "s";
        }
    }

    private static void saveTaskList() {
        File file = new File("data/duke.txt");
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter("data/duke.txt");
            for (Task t : myList) {
                writer.write(t.toExportFormat() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            printException(e);
        }
    }

    private static void loadTaskList() {
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            return;
        }
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                myList.add(Task.fromImportFormat(fileScanner.nextLine()));
            }
            fileScanner.close();
        } catch (IOException e) {
            printException(e);
        } catch (DukeException e) {
            printException(new DukeException("Load file failed :-("));
        }
    }

    protected static Date parseDate(String rawDate) throws DukeException {
        try {
            Date newDate = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(rawDate);
            return newDate;
        } catch (ParseException e) {
            throw new DukeException("Cannot parse date/time.");
        }
    }

    protected static String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").format(date);
    }
}

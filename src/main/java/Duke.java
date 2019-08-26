import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) {
        welcomeMessage();
        Scanner sc = new Scanner(System.in);
        if (!historyExists()) {
            //no history
            createFile();
        } else {
            // read past history
            retrieveHistory();
        }
        while (true) {
            String input = sc.nextLine();
            String[] inputParts = input.split(" ", 2);
            String command = inputParts[0];
            if (command.equals("bye")) {
                saveHistory();
                byeMessage();
                break;
            } else if (command.equals("list")) {
                displayList();
            } else if (command.equals("done")) {
                markItemComplete(Integer.parseInt(inputParts[1]));
            } else if (command.equals("delete")) {
                deleteItem(Integer.parseInt(inputParts[1]));
            } else {
                registerNewTask(inputParts);
            }
        }
        sc.close();
    }

    static void registerNewTask(String[] inputParts) {
        try {
            checkCommandValidity(inputParts[0]);
            Task t = addToList(inputParts[1], inputParts[0]);
            echo(t);
        } catch (DukeException e) {
            printErrorMessage(e.getMessage());
        }
    }

    static void retrieveHistory() {
        try {
            Path filePath = Paths.get("./saved/taskList_history.txt");
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath));
            for (String line : lines) {
                count++;
                String[] parts = line.split("\\|");
                if (parts[0].equals("T")) {
                    taskList.add(new Todo(parts[2], count, parts[1].equals("1")));
                } else if (parts[0].equals("D")) {
                    taskList.add(new Deadline(parts[2], count, parts[3], parts[1].equals("1")));
                } else if (parts[0].equals("E")) {
                    taskList.add(new Event(parts[2], count, parts[3], parts[1].equals("1")));
                }
            }
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    static void saveHistory() {
        try {
            String result = "";
            for (Task t : taskList) {
                String type = "";
                String date = "";
                if (t instanceof Todo) {
                    type = "T";
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    type = "D";
                    date = d.getDate();
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    type = "E";
                    date = e.getDate();
                }
                String completed = t.isCompleted() ? "1" : "0";
                String title = t.getName();
                if (t instanceof Todo) {
                    String currentTask = type + "|" + completed + "|" + title + "\n";
                    result += currentTask;
                } else {
                    String currentTask = type + "|" + completed + "|" + title + "|" + date + "\n";
                    result += currentTask;
                }
            }
            Path filePath = Paths.get("./saved/taskList_history.txt");
            Files.write(filePath, result.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void checkCommandValidity(String type) throws DukeException {
        if ( !type.equals("todo") && !type.equals("deadline") && !type.equals("event")) {
            throw new DukeException("I don't know what that means :(");
        }
    }

    static boolean historyExists() {
        Path filePath = Paths.get("./saved/taskList_history.txt");
        return Files.exists(filePath);
    }

    static void createFile() {
        try {
            Path filePath = Paths.get("./saved/taskList_history.txt");
            Path folderPath = Paths.get("./saved/");
            Files.createDirectory(folderPath);
            Files.createFile(filePath);
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    static void drawLine() {
        String line = "    ________________________"
                + "____________________________________";
        System.out.println(line);
    }

    static void deleteItem(int index) {
        try {
            if (index <= 0 || index > count) {
                throw new DukeException("Invalid task number!");
            }
            index--;
            Task t = taskList.get(index);
            taskList.remove(index);
            drawLine();
            System.out.println("     Noted. I've removed this task:");
            System.out.println("      " + t);
            count--;
            String message =
                    count == 1
                            ? "     Now you have 1 task in the list"
                            : "     Now you have " + count + " tasks in the list";
            System.out.println(message);
            drawLine();
            System.out.println("\n");
        } catch (DukeException e) {
            printErrorMessage(e.getMessage());
        }
    }

    static void printErrorMessage(String e) {
        drawLine();
        System.out.println("     " + e);
        drawLine();
        System.out.println("\n");
    }

    static void displayList() {
        drawLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            int taskListNumber = i + 1;
            System.out.println("    " + taskListNumber + "." + taskList.get(i));
        }
        drawLine();
        System.out.println("\n");
    }

    static void markItemComplete(int index) {
        try {
            if (index <= 0 || index > count) {
                throw new DukeException("Invalid task number!");
            }
            Task t = taskList.get(index - 1);
            t.complete();
            drawLine();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("     " + t);
            drawLine();
            System.out.println("\n");
        } catch (DukeException e) {
            printErrorMessage(e.getMessage());
        }
    }

    static Task addToList(String s, String type) throws DukeException {
        String trimmed = s.replaceAll("^\\s+", "");
        if (trimmed.equals("")) {
            throw new DukeException("Description cannot be empty!");
        }
        if (type.equals("todo")) {
            taskList.add(new Todo(s, count + 1));
        } else if (type.equals("deadline")) {
            String[] parts = s.split("\\/" + "by");
            if (parts.length < 2) {
                String message = "Date required! ";
                message += "Format: deadline YOUR_TASK_NAME /by YOUR_DEADLINE";
                throw new DukeException(message);
            } else if (parts.length != 2) {
                String message = "Format: deadline YOUR_TASK_NAME /by YOUR_DEADLINE";
                throw new DukeException(message);
            }
            taskList.add(new Deadline(
                    parts[0].substring(0, parts[0].length() - 1),
                    count + 1,
                    createDateAndTime(parts[1].substring(1))
            ));
        } else if (type.equals("event")) {
            String[] parts = s.split("\\/" + "at");
            if (parts.length < 2) {
                String message = "Date required! ";
                message += "Format: event YOUR_TASK_NAME /at YOUR_EVENT_DATE";
                throw new DukeException(message);
            } else if (parts.length != 2) {
                String message = "Format: event YOUR_TASK_NAME /at YOUR_EVENT_DATE";
                throw new DukeException(message);
            }
            taskList.add(new Event(
                    parts[0].substring(0, parts[0].length() - 1),
                    count + 1,
                    createDateAndTime(parts[1].substring(1))
            ));
        }
        count++;
        return taskList.get(count - 1);
    }

    static String createDateAndTime(String s) {
        String[] parts = s.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("/")) {
                parts[i] = createDate(parts[i]);
            } else if (is24hrFormat(parts[i])) {
                parts[i] = createTime(parts[i]);
            }
        }
        String result = "";
        for (String part : parts) {
            result += " " + part;
        }
        return result.substring(1);
    }

    static boolean is24hrFormat(String time) {
        return isInteger(time) && time.length() == 4 && Integer.parseInt(time) < 2400;
    }

    static String createTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        String min = time.substring(2, 4);
        String timeOfDay = hour > 11 ? "pm" : "am";
        hour = (hour > 12)
                ? (hour - 12)
                : ((hour == 0) ? 12 : hour);
        return hour + ":" + min + timeOfDay;
    }

    static String createDate(String date) {
        String[] parts = date.split("/");
        String[] month = {
                "",
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };
        boolean validDate = true;
        if (parts.length == 3) {
            for (int i = 0; i < 3; i++) {
                if (!isInteger(parts[i])) {
                    validDate = false;
                } else if (i == 1
                        && (Integer.parseInt(parts[i]) < 1 || Integer.parseInt(parts[i]) > 12)) {
                    validDate = false;
                }
            }
        } else {
            validDate = false;
        }
        if (validDate) {
            if (parts[2].length() == 4) {
                return parts[0] + " " + month[Integer.parseInt(parts[1])] + " " + parts[2];
            } else {
                return parts[2] + " " + month[Integer.parseInt(parts[1])] + " " + parts[0];
            }
        } else {
            return date;
        }
    }

    static boolean isInteger(String n) {
        try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static void welcomeMessage() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        drawLine();
        System.out.println("    Hello from\n" + logo);
        System.out.println("    What can I do for you?");
        drawLine();
        System.out.println("\n");
    }

    static void byeMessage() {
        drawLine();
        System.out.println("     Bye. Hope to see you again soon!");
        drawLine();
        System.out.println("\n");
    }

    public static void echo(Task t) {
        drawLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + t);
        if (count == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            String message = "     Now you have " + count + " tasks in the list.";
            System.out.println(message);
        }
        drawLine();
        System.out.println("\n");
    }
}

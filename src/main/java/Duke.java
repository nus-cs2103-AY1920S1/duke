import textfiles.ReadFile;
import textfiles.WriteFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<String> textArr;
        String file_name = "../../../data/duke.txt";

        greet();
        try {
            ReadFile file = new ReadFile(file_name);
            textArr = file.OpenFile();

            for (int i = 0; i < textArr.size(); i++) {
                String[] str = textArr.get(i).split(" \\| ");
                Task task;

                if (str[0].equals("T")) {
                    task = new ToDo(str[2]);
                } else if (str[0].equals("D")) {
                    task = new Deadline(str[2], str[3]);
                } else {
                    task = new Event(str[2], str[3]);
                }

                if (str[1].equals("\u2713")) {
                    task.markAsDone();
                }

                System.out.println("     " + (i + 1) + "." + task.toString());

                taskList.add(task);
            }

            printLine();
            System.out.println();
        } catch (IOException e) {
            ioErrorMessage();
            return;
        }

        while (run && sc.hasNext()) {
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");

            try {
                checkCommand(commandArr);
                WriteFile data = new WriteFile(file_name, true);
                switch (commandArr[0]) {
                    case "list":
                        printArray(taskList);
                        break;

                    case "done":
                        int indexDone = Integer.parseInt(commandArr[1]) - 1;
                        Task currTask = taskList.get(indexDone);
                        currTask.markAsDone();
                        taskComplete(currTask);
                        replaceNthLine(file_name, indexDone, currTask);
                        break;

                    case "delete":
                        int index = Integer.parseInt(commandArr[1]) - 1;
                        deleteComplete(taskList.size(), taskList.get(index));
                        taskList.remove(index);
                        removeNthLine(file_name, index);
                        break;

                    case "todo":
                        String todoDescription = getDescription(commandArr);
                        Task todo = new ToDo(todoDescription);
                        taskList.add(todo);
                        data.writeToFile("T | \u2718 | " + todoDescription);
                        printTask(todo, taskList.size());
                        break;

                    case "event":
                        String eventDescription = getDescription(commandArr);
                        String eventTime = getTime(commandArr);
                        eventTime = checkTime(eventTime);
                        Task event = new Event(eventDescription, eventTime);
                        taskList.add(event);
                        data.writeToFile("E | \u2718 | " + eventDescription + " | " + eventTime);
                        printTask(event, taskList.size());
                        break;

                    case "deadline":
                        String deadlineDescription = getDescription(commandArr);
                        String deadlineTime = getTime(commandArr);
                        deadlineTime = checkTime(deadlineTime);
                        Task deadline = new Deadline(deadlineDescription, deadlineTime);
                        taskList.add(deadline);
                        data.writeToFile("D | \u2718 | " + deadlineDescription + " | " + deadlineTime);
                        printTask(deadline, taskList.size());
                        break;

                    case "bye":
                        exit();
                        run = false;
                        break;

                    default:
                        Task task = new Task(command);
                        taskList.add(task);
                        echo("added: " + command);
                        break;
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } catch (IndexOutOfBoundsException ex) {
                indexErrorMessage(taskList.size());
            } catch (NumberFormatException ex) {
                numberErrorMessage();
            } catch (IOException ex) {
                ioErrorMessage();
            }
        }

        sc.close();
    }

    private static void removeNthLine(String f, int toRemove) throws IOException {

        File tmp = File.createTempFile("tmp", ".txt");

        BufferedReader br = new BufferedReader(new FileReader(f));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));

        for (int i = 0; i < toRemove; i++)
            bw.write(String.format("%s%n", br.readLine()));

        br.readLine();

        String l;
        while (null != (l = br.readLine()))
            bw.write(String.format("%s%n", l));

        br.close();
        bw.close();

        File oldFile = new File(f);
        if (oldFile.delete()) {
            //noinspection ResultOfMethodCallIgnored
            tmp.renameTo(oldFile);
        }
    }

    private static void replaceNthLine(String f, int toReplace, Task currTask) throws IOException {

        File tmp = File.createTempFile("tmp", ".txt");

        BufferedReader br = new BufferedReader(new FileReader(f));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));

        for (int i = 0; i < toReplace; i++) {
            bw.write(String.format("%s%n", br.readLine()));
        }

        if (currTask instanceof ToDo) {
            bw.write("T | " + "\u2713" + " | " + currTask.getDescription() + "\n");
        } else if (currTask instanceof Deadline) {
            bw.write("D | " + "\u2713" + " | " + currTask.getDescription()
                + " | " + ((Deadline) currTask).getBy() + "\n");
        } else {
            bw.write("E | " + "\u2713" + " | " + currTask.getDescription()
                    + " | " + ((Event) currTask).getAt() + "\n");
        }

        br.readLine();

        String l;
        while (null != (l = br.readLine()))
            bw.write(String.format("%s%n", l));

        br.close();
        bw.close();

        File oldFile = new File(f);
        if (oldFile.delete())
            //noinspection ResultOfMethodCallIgnored
            tmp.renameTo(oldFile);
    }

    private static void ioErrorMessage() {
        printLine();
        System.out.println("     Sorry there is no text file to read or write data.");
        printLine();
        System.out.println();
    }

    private static String checkTime(String time) throws DukeException {
        String[] timeArr = time.split(" ");
        String[] month = {"NIL", "January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December"};
        StringJoiner sj = new StringJoiner(" ");

        for (String str : timeArr) {
            if (str.matches("([0-9]?[0-9])/([0-9]?[0-9])/([0-9]{4})")) {
                if (validateDate(str)) {
                    String[] date = str.split("/");
                    int day = Integer.parseInt(date[0]);
                    int last = day % 10;
                    int monthCurr = Integer.parseInt(date[1]);
                    int year = Integer.parseInt(date[2]);

                    if (last == 1) {
                        sj.add(day + "st of");
                    } else if (last == 2) {
                        sj.add(day + "nd of");
                    } else if (last == 3) {
                        sj.add(day + "rd of");
                    } else {
                        sj.add(day + "th of");
                    }

                    sj.add(month[monthCurr]);
                    sj.add(year + ",");
                } else {
                    throw new DukeException("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! Please type in a valid date: DD/MM/YYYY\n" +
                            "    ____________________________________________________________\n");
                }
            } else if (str.matches("[0-9]{4}")) {
                if (validateTime(str)) {
                    int hour = Integer.parseInt(str.substring(0, 2));
                    int min = Integer.parseInt(str.substring(2, 4));

                    if (hour == 12) {
                        sj.add(String.format("%d:%02dpm", hour, min));
                    } else if (hour > 12) {
                        sj.add(String.format("%d:%02dpm", hour - 12, min));
                    } else if (hour == 0) {
                        sj.add(String.format("12:%02dam", min));
                    } else {
                        sj.add(String.format("%d:%02dam", hour, min));
                    }
                } else {
                    throw new DukeException("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! Please type in a valid 24hrs timing\n" +
                            "    ____________________________________________________________\n");
                }
            } else {
                sj.add(str);
            }
        }
        return sj.toString();
    }

    private static boolean validateTime(String str) {
        boolean result = true;
        int hour = Integer.parseInt(str.substring(0, 2));
        int min = Integer.parseInt(str.substring(2, 4));

        if (min < 0 || min > 59) {
            result = false;
        }

        if (hour < 0 || hour > 23) {
            result = false;
        }

        return result;
    }

    private static boolean validateDate(String str) {
        String[] date = str.split("/");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        boolean result = true;
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (month > 0 && month < 13) {
            int end = days[Integer.parseInt(date[1])];
            if ((year % 100 == 0 && year % 400 == 0) && year % 4 == 0 && month == 2) {
                end = 29;
            }

            if (day < 0 || day > end) {
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }



    private static void deleteComplete(int size, Task currTask) {
        printLine();
        System.out.println("     Noted. I've removed this task: \n       " +
                currTask +
                "\n     Now you have " + (size - 1) + " tasks in the list.");
        printLine();
        System.out.println();
    }

    private static void numberErrorMessage() {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! Please type in a valid index from 1 to 100\n" +
                "    ____________________________________________________________\n");
    }

    private static void indexErrorMessage(int len) {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! Index out of bounds for task list of length " + len + "\n" +
                "    ____________________________________________________________\n");
    }

    private static void checkCommand(String[] commandArr) throws DukeException {
        if (!commandArr[0].matches("todo|deadline|event|done|list|bye|delete")) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________\n");
        }

        if (commandArr[0].matches("list|bye")) {
            if (commandArr.length > 1) {
                throw new DukeException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "    ____________________________________________________________\n");
            }
        }
    }

    private static String getDescription(String[] commandArr) throws DukeException {
        StringJoiner description = new StringJoiner(" ");
        int index;
        if (commandArr[0].equals("todo")) {
            index = commandArr.length;
        } else if (commandArr[0].equals("deadline")) {
            index = Arrays.asList(commandArr).indexOf("/by");
        } else {
            index = Arrays.asList(commandArr).indexOf("/at");
        }

        for (int i = 1; i < index; i++) {
            description.add(commandArr[i]);
        }

        if (index == -1) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The timing of a " + commandArr[0] + " cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        } else if (description.toString().equals("")) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a " + commandArr[0] + " cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }

        return description.toString();
    }

    private static String getTime(String[] commandArr) throws DukeException {
        StringJoiner timing = new StringJoiner(" ");
        int index;
        if (commandArr[0].equals("deadline")) {
            index = Arrays.asList(commandArr).indexOf("/by");
        } else {
            index = Arrays.asList(commandArr).indexOf("/at");
        }

        for (int i = index + 1; i < commandArr.length; i++) {
            timing.add(commandArr[i]);
        }

        if (index == -1 || timing.toString().equals("")) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The timing of a " + commandArr[0] + " cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }

        return timing.toString();
    }

    private static void printTask(Task task, int size) {
        printLine();
        System.out.println("     Got it. I've added this task: \n       " + task);
        System.out.printf("     Now you have %d tasks in the list.\n", size);
        printLine();
        System.out.println();
    }

    private static void taskComplete(Task currTask) {
        printLine();
        System.out.println("     Nice! I've marked this task as done: \n       " + currTask);
        printLine();
        System.out.println();
    }

    private static void printArray(ArrayList<Task> taskList) {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i));
        }
        printLine();
        System.out.println();
    }

    //Greet the user when starting up Duke
    private static void greet() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        printLine();
        System.out.println(logo);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        printLine();
        printLine();
        System.out.println("     Here are the tasks that you have now:");
    }

    // Echo commands entered by users
    private static void echo(String command) {
        printLine();
        System.out.println("     " + command);
        printLine();
        System.out.println();
    }

    //Exits when the user types bye
    private static void exit() {
        printLine();
        System.out.println("      Bye. Hope to see you again soon!");
        printLine();
        System.out.println();
    }

    // Print indented line
    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}

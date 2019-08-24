package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Duke {

    private static String divider = "    ____________________________________________________________\n";
    private static List<Task> taskList = new ArrayList<>();
    private static File file;

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.print(divider);
        System.out.print("\t Hello! I'm Duke\n");
        System.out.print("\t What can I do for you?\n");
        System.out.print(divider);

//        loadData();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String action = sc.next();
            if (action.equals("bye")) {
                System.out.print(divider);
                System.out.print("     Bye. Hope to see you again soon!\n");
                System.out.print(divider);
                break;

            } else if (action.equals("list")) {
                System.out.print(divider);
                listTasks();
                System.out.print(divider);

            } else if (action.equals("done")) {
                System.out.print(divider);
                markTaskAsDone(sc);
                System.out.print(divider);


            } else if (action.equals("todo")) {
                System.out.print(divider);
                onTodoActionCalled(sc);
                System.out.print(divider);

            } else if (action.equals("deadline")) {
                System.out.print(divider);
                onDeadlineActionCalled(sc);
                System.out.print(divider);

            } else if (action.equals("event")) {
                System.out.print(divider);
                onEventActionCalled(sc);
                System.out.print(divider);

            } else if (action.equals("delete")) {
                System.out.print(divider);
                deleteTask(sc);
                System.out.print(divider);

            } else {
                System.out.print(divider);
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    sc.nextLine();
                    System.out.print("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
                System.out.print(divider);
            }
        }

    }

    private static void listTasks() {
        if (taskList.isEmpty()) {
            System.out.print("    You have no task at the moment.\n");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("    %d.", i+1);
                System.out.printf("%s\n", taskList.get(i));
            }
        }
    }

    private static void markTaskAsDone(Scanner sc) {
        try {
            int idx = sc.nextInt() - 1;
            taskList.get(idx).setDone();
            System.out.print("     Nice! I've marked this task as done:\n");
            System.out.printf("       %s\n", taskList.get(idx));

        } catch (IndexOutOfBoundsException e) {
            if (taskList.isEmpty()) {
                System.out.printf("     ☹ OOPS!!! You have no task at the moment.\n", 1, taskList.size());
            } else {
                System.out.printf("     ☹ OOPS!!! Task index number must be a number from %d to %d.\n",
                        1,
                        taskList.size());
            }
        } catch (InputMismatchException e) {
            System.out.printf("     ☹ OOPS!!! Task index number must be a number from %d to %d.\n",
                    1,
                    taskList.size());
            sc.nextLine();
        }
    }

    private static void deleteTask(Scanner sc) {
        try {
            int idx = sc.nextInt() - 1;
            Task task = taskList.remove(idx);
            System.out.print("     Noted. I've removed this task:\n");
            System.out.printf("       %s\n", task);
            System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());

        } catch (IndexOutOfBoundsException e) {
            if (taskList.isEmpty()) {
                System.out.printf("     ☹ OOPS!!! You have no task at the moment.\n", 1, taskList.size());
            } else {
                System.out.printf("     ☹ OOPS!!! Task index number must be a number from %d to %d.\n",
                        1,
                        taskList.size());
            }

        } catch (InputMismatchException e) {
            System.out.printf("     ☹ OOPS!!! Task index number must be a number from %d to %d.\n",
                    1,
                    taskList.size());
            sc.nextLine();
        }
    }

    private static void onTodoActionCalled(Scanner sc) {
        String taskName = sc.nextLine().trim();
        try {
            if (taskName.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            Task newTask = addNewTodo(taskName, false);
            printAddTaskMessage(newTask);

        } catch (EmptyDescriptionException e) {
            System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", "todo");
        }

    }

    private static void onDeadlineActionCalled(Scanner sc) {
        String taskName = sc.nextLine().trim();
        String[] taskInfo = taskName.split("\\s*/by\\s*");
        try {

            if (taskName.isEmpty() || taskInfo[0].trim().isEmpty()) {
                throw new EmptyDescriptionException();
            }
            Task newTask = addNewDeadline(taskInfo[0], taskInfo[1], false);
            printAddTaskMessage(newTask);

        } catch (EmptyDescriptionException e) {
            System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", "deadline");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("     ☹ OOPS!!! There must be exactly one argument before and\n" +
                    "     one argument after the keyword %s.\n", "/by");
        } catch (ParseException e) {
            System.out.print("     ☹ OOPS!!! Date must be in the format \"dd/MM/yyyy HHmm\"\n");
        }
    }

    private static void onEventActionCalled(Scanner sc) {
        String taskName = sc.nextLine().trim();
        String[] taskInfo = taskName.split("\\s*/at\\s*");
        try {
            if (taskName.isEmpty() || taskInfo[0].trim().isEmpty()) {
                throw new EmptyDescriptionException();
            }
            Task newTask = addNewEvent(taskInfo[0], taskInfo[1], false);
            printAddTaskMessage(newTask);
        } catch (EmptyDescriptionException e) {
            System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", "event");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("     ☹ OOPS!!! There must be exactly one argument before and\n" +
                    "     one argument after the keyword %s.\n", "/at");
        }
    }

    private static Task addNewTodo(String taskName, boolean isDone) {
        Task newTask = new Todo(taskName);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        taskList.add(newTask);
        return newTask;
    }

    private static Task addNewEvent(String taskName, String additionalInfo, boolean isDone) {
        Task newTask = new Event(taskName, additionalInfo);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        taskList.add(newTask);
        return newTask;
    }

    private static Task addNewDeadline(String taskName, String additionalInfo, boolean isDone)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(additionalInfo));
        Task newTask = new Deadline(taskName, additionalInfo);
        if (isDone) {
            newTask.setDone();
        } else {
            newTask.setNotDone();
        }
        taskList.add(newTask);
        return newTask;
    }


    private static void printAddTaskMessage(Task task) {
        System.out.print("     Got it. I've added this task:\n");
        System.out.printf("       %s\n", task);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());
    }

    private static void loadData() throws FileNotFoundException, ParseException {
        file = new File("data/fruits.txt");
        if (file.exists()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] taskInfo = sc.nextLine().split("\\s*|\\s*");
                switch (taskInfo[0]) {
                    case "T":
                        addNewTodo(taskInfo[2], taskInfo[1].equals("1"));
                        break;

                    case "D":
                        addNewDeadline(taskInfo[2], taskInfo[3], taskInfo[1].equals("1"));
                        break;

                    case "E":
                        addNewEvent(taskInfo[2], taskInfo[3], taskInfo[1].equals("1"));
                        break;
                }
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

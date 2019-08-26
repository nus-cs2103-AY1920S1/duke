package utils;

import exception.EmptyDescriptionException;
import task.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Parser {

    private Scanner sc;

    public Parser() {
        sc = new Scanner(System.in);
    }

    public void setScanner(Scanner sc) {
        this.sc = sc;
    }

    public void nextLine() {
        sc.nextLine();
    }

    public boolean hasNext() {
        return sc.hasNext();
    }

    public String getNextAction() {
        return sc.next();
    }

    public String[] parseEventDetail() {
        try {
            String taskName = sc.nextLine().trim();
            String[] taskInfo = taskName.split("\\s*/at\\s*");
            if (taskName.isEmpty() || taskInfo[0].trim().isEmpty() || taskInfo[1].equals("")) {
                throw new EmptyDescriptionException();
            }
            return taskInfo;
        } catch (EmptyDescriptionException e) {
            System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", "event");
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("     ☹ OOPS!!! There must be exactly one argument before and\n" +
                    "     one argument after the keyword %s.\n", "/at");
            return null;
        }
    }

    public String[] parseDeadlineDetail() {
        try {
            String taskName = sc.nextLine().trim();
            String[] taskInfo = taskName.split("\\s*/by\\s*");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
            sdf.setLenient(false);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(taskInfo[1]));

            if (taskName.isEmpty() || taskInfo[0].trim().isEmpty()) {
                throw new EmptyDescriptionException();
            }
            return taskInfo;

        } catch (EmptyDescriptionException e) {
            System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", "deadline");
            return null;

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("     ☹ OOPS!!! There must be exactly one argument before and\n" +
                    "     one argument after the keyword %s.\n", "/by");
            return null;

        } catch (ParseException e) {
            System.out.print("     ☹ OOPS!!! Date must be in the format \"dd/MM/yyyy HHmm\"\n + " +
                    "     and must be valid.");
            return null;
        }
    }


    public String parseTodoDetail() {
        try {
            String taskName = sc.nextLine().trim();
            if (taskName.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            return taskName;
        } catch (EmptyDescriptionException e) {
            System.out.printf("     ☹ OOPS!!! The description of a %s cannot be empty.\n", "todo");
            return null;
        }
    }

    public Integer getTaskIdx() {

        TaskList taskList = TaskList.newInstance();
        try {
            int idx = Integer.parseInt(sc.next()) - 1;
            sc.nextLine();
            taskList.get(idx);
            return idx;

        } catch (IndexOutOfBoundsException e) {
            if (taskList.isEmpty()) {
                System.out.printf("     ☹ OOPS!!! You have no task at the moment.\n", 1, taskList.size());
            } else {
                System.out.printf("     ☹ OOPS!!! Task index number must be a number from %d to %d.\n",
                        1,
                        taskList.size());
            }
            return null;

        } catch (NumberFormatException e) {
            System.out.printf("     ☹ OOPS!!! Task index number must be a number from %d to %d.\n",
                    1,
                    taskList.size());
            return null;
        }
    }
}

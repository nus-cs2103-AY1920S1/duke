import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.ParseException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File("data/duke.txt");
        try {
            Scanner sc2 = new Scanner(f);
            while (sc2.hasNext()) {
                String type = sc2.next();
                switch (type) {
                case "T":
                    String todo = sc2.nextLine().trim();
                    String[] todoArray = todo.split("\\|");
                    Task todoTask = new Todo(todoArray[2].trim());
                    if (todoArray[1].trim().equals("1")) {
                        todoTask.markAsDone();
                    }
                    tasks.add(todoTask);
                    break;
                case "D":
                    String deadline = sc2.nextLine().trim();
                    String[] deadlineArray = deadline.split("\\|");
                    Task deadlineTask = new Deadline(deadlineArray[2].trim(), convertDateAndTime(deadlineArray[3].trim()));
                    if (deadlineArray[1].trim().equals("1")) {
                        deadlineTask.markAsDone();
                    }
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    String event = sc2.nextLine().trim();
                    String[] eventArray = event.split("\\|");
                    Task eventTask = new Event(eventArray[2].trim(), convertDateAndTime(eventArray[3].trim()));
                    if (eventArray[1].trim().equals("1")) {
                        eventTask.markAsDone();
                    }
                    tasks.add(eventTask);
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }

        while (sc.hasNext()) {
            String command = sc.next();
            switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return;
            case "list":
                System.out.println("Here are the " + (tasks.size() == 1 ? "task" : "tasks") + " in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i - 1));
                }
                break;
            case "done":
                try {
                    int number = sc.nextInt();
                    tasks.get(number - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(number - 1));
                    String s = "";
                    Scanner scanFile = new Scanner(f);
                    for (int i = 1; i <= tasks.size(); i++) {
                        if (i == number) {
                            if (i == tasks.size()) {
                                s = s + scanFile.nextLine().replaceFirst("0", "1");
                            } else {
                                s = s + scanFile.nextLine().replaceFirst("0", "1") + System.lineSeparator();
                            }
                        } else {
                            if (i == tasks.size()) {
                                s = s + scanFile.nextLine();
                            } else {
                                s = s + scanFile.nextLine() + System.lineSeparator();
                            }
                        }
                    }
                    FileWriter fw = new FileWriter("data/duke.txt");
                    fw.write(s);
                    fw.close();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid task name.");
                    sc.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid task number.");
                } catch (FileNotFoundException e) {
                    System.out.println("File does not exist");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "todo":
                try {
                    String taskname = sc.nextLine().trim();
                    if (taskname.equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(taskname);
                    FileWriter append = new FileWriter("data/duke.txt", true);
                    if (tasks.size() == 0) {
                        append.write("T | 0 | " + taskname);
                    } else {
                        append.write("\n" + "T | 0 | " + taskname);
                    }
                    append.close();
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "deadline":
                try {
                    String deadline = sc.nextLine().trim();
                    if (deadline.equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] arrDeadline = deadline.split("/by");
                    String timeDeadline = convertDateAndTime(arrDeadline[1].trim());
                    Task t = new Deadline(arrDeadline[0].trim(), timeDeadline);
                    FileWriter append = new FileWriter("data/duke.txt", true);
                    if (tasks.size() == 0) {
                        append.write("D | 0 | " + arrDeadline[0].trim() + " | " + arrDeadline[1].trim());
                    } else {
                        append.write("\n" + "D | 0 | " + arrDeadline[0].trim() + " | " + arrDeadline[1].trim());
                    }
                    append.close();
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "event":
                try {
                    String event = sc.nextLine().trim();
                    if (event.equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String[] arrEvent = event.split("/at");
                    String time = convertDateAndTime(arrEvent[1].trim());
                    Task task = new Event(arrEvent[0].trim(), time);
                    FileWriter append = new FileWriter("data/duke.txt", true);
                    if (tasks.size() == 0) {
                        append.write("E | 0 | " + arrEvent[0].trim() + " | " + arrEvent[1].trim());
                    } else {
                        append.write("\n" + "E | 0 | " + arrEvent[0].trim() + " | " + arrEvent[1].trim());
                    }
                    append.close();
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                try {
                    int deletionNumber = sc.nextInt();
                    String s = "";
                    Scanner scanFile = new Scanner(f);
                    for (int i = 1; i <= tasks.size(); i++) {
                        if (i == deletionNumber) {
                            scanFile.nextLine();
                        } else if (i == tasks.size()) {
                            s = s + scanFile.nextLine();
                        } else if (i == tasks.size() - 1 && tasks.size() == deletionNumber) {
                            s = s + scanFile.nextLine();
                        } else {
                            s = s + scanFile.nextLine() + System.lineSeparator();
                        }
                    }

                    FileWriter fw = new FileWriter("data/duke.txt");
                    fw.write(s);
                    fw.close();
                    Task toDelete = tasks.get(deletionNumber - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + toDelete);
                    tasks.remove(deletionNumber - 1);
                    System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task does not exist.");
                } catch (FileNotFoundException e) {
                    System.out.println("File does not exist");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static String convertDateAndTime(String time) { // in the form of dd/mm/yyyy hhmm
        String[] array = time.split(" ");
        String day = "";
        if (array.length == 2) {
            String[] dateArray = array[0].split("/");
            if (dateArray.length == 3) {
                if ((dateArray[0].equals("1") || dateArray[0].equals("21") || dateArray[0].equals("31"))) {
                    day = dateArray[0] + "st";
                } else if ((dateArray[0].equals("2") || dateArray[0].equals("22"))) {
                    day = dateArray[0] + "nd";
                } else if ((dateArray[0].equals("3") || dateArray[0].equals("23"))) {
                    day = dateArray[0] + "rd";
                } else {
                    day = dateArray[0] + "th";
                }
                switch (dateArray[1]) {
                case "1":
                    day = day + " of January ";
                    break;
                case "2":
                    day = day + " of February ";
                    break;
                case "3":
                    day = day + " of March ";
                    break;
                case "4":
                    day = day + " of April ";
                    break;
                case "5":
                    day = day + " of May ";
                    break;
                case "6":
                    day = day + " of June ";
                    break;
                case "7":
                    day = day + " of July ";
                    break;
                case "8":
                    day = day + " of August ";
                    break;
                case "9":
                    day = day + " of September ";
                    break;
                case "10":
                    day = day + " of October ";
                    break;
                case "11":
                    day = day + " of November ";
                    break;
                case "12":
                    day = day + " of December ";
                    break;
                }
                day = day + dateArray[2] + ", ";
                try {
                    String rawTime = array[1];
                    String[] timeArray = rawTime.split("-");
                    if (timeArray.length == 1) { // deadline
                        SimpleDateFormat input = new SimpleDateFormat("HHmm");
                        SimpleDateFormat output = new SimpleDateFormat("hh:mmaa");
                        Date date = input.parse(rawTime);
                        String outputString = output.format(date);
                        if (outputString.charAt(0) == '0') {
                            day = day + outputString.substring(1).toLowerCase();
                        } else {
                            day = day + outputString.toLowerCase();
                        }
                    } else if (timeArray.length == 2) { // event
                        SimpleDateFormat input = new SimpleDateFormat("HHmm");
                        SimpleDateFormat output = new SimpleDateFormat("hh:mmaa");
                        Date start = input.parse(timeArray[0]);
                        Date end = input.parse(timeArray[1]);
                        String startTime = output.format(start).toLowerCase();
                        String endTime = output.format(end).toLowerCase();
                        if (startTime.charAt(0) == '0') {
                            startTime = startTime.substring(1);
                        }
                        if (endTime.charAt(0) == '0') {
                            endTime = endTime.substring(1);
                        }
                        day = day + startTime + " to " + endTime;
                    }
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                return time;
            }
        } else {
            return time;
        }
        return day;
    }
}





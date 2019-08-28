import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String filePath = "data/duke.txt";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner scn = new Scanner(System.in);

        String input = scn.nextLine();
        List<Task> tasks = new ArrayList<Task>();
        try {
            loadFileContents(filePath, tasks);
        } catch (FileNotFoundException ex){
            System.out.println("Cannot load saved file");
        }

        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    int index = 0;
                    for (Task t : tasks) {
                        System.out.println((index + 1) + "." + t.toString());
                        index++;
                    }
                } else if (input.substring(0, 4).equals("done")) {
                    int do_Index = Integer.parseInt(input.substring(5)) - 1;
                    Task chosen_Task = tasks.get(do_Index);
                    chosen_Task.markAsDone();
                    try {
                        updateTaskInFile(filePath, do_Index + 1);
                    } catch (IOException ex) {
                        System.out.println("Can't update task in the file");
                    }

                    System.out.println("Nice! I've marked this task as done:\n  " +
                            chosen_Task.toString());
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    int delete_Index = Integer.parseInt(input.substring(7)) - 1;
                    Task chosen_Task = tasks.get(delete_Index);
                    tasks.remove(delete_Index);
                    try {
                        deleteFromFile(filePath, delete_Index + 1);
                        System.out.println("Noted. I've removed this task:\n  " + chosen_Task.toString());
                        System.out.println("Now you have " + tasks.size() +
                                (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                    } catch (IOException ex) {
                        System.out.print("No saved files");
                    }

                } else {
                    if (input.substring(0, 4).equals("todo")) {
                        if (input.length() == 4) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasks.add(new ToDo(input.substring(5)));
                    } else if (input.substring(0, 5).equals("event")) {
                        if (input.length() == 5) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        int i = input.indexOf('/');
                        String dateAndTime = input.substring(i + 4);
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
                        tasks.add(new Event(input.substring(6, i - 1), dateTime));
                    } else if (input.substring(0, 8).equals("deadline")) {
                        if (input.length() == 8) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        int i = input.indexOf('/');
                        String dateAndTime = input.substring(i + 4);
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
                        tasks.add(new Deadline(input.substring(9, i - 1), dateTime));
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    try {
                        addToFile(filePath, tasks.get(tasks.size() - 1).toSaveString());
                    } catch (IOException ex) {
                        System.out.println("Cannot save new task in file");
                    }

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() +
                            (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            catch (DateTimeParseException ex) {
                System.out.println("Invalid date and time format\n"
                        + "Please enter date and time as 'dd/MM/yyyy HHmm'");
            }

            input = scn.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void loadFileContents(String filePath, List<Task> list) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String taskString = s.nextLine();
            String taskType = taskString.substring(0, 1);
            String isDone = taskString.substring(4, 5);
            if (taskType.equals("T")) {
                String desc = taskString.substring(8);
                ToDo task = new ToDo(desc);
                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                list.add(task);
            } else {
                int i = taskString.lastIndexOf("|");
                String desc = taskString.substring(8, i - 1);
                String time = taskString.substring(i + 2, i + 12) + taskString.substring(i + 13);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(time, format);
                if (taskType.equals("D")) {
                    Deadline task = new Deadline(desc, dateTime);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    list.add(task);
                } else {
                    Event task = new Event(desc, dateTime);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    list.add(task);
                }
            }
        }
    }

    private static void addToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        File file = new File(filePath);
        Scanner scn = new Scanner(file);
        if (scn.hasNext()) {
            fw.write(System.lineSeparator());
        }
        fw.write(textToAdd);
        fw.close();
    }

    private static void deleteFromFile(String filePath, int taskNum) throws IOException {
        File file =  new File(filePath);
        Scanner scn = new Scanner(file);
        List<String> list = new ArrayList<String>();
        int lineNum = 1;
        while (scn.hasNext()) {
            String s = scn.nextLine();
            if (lineNum != taskNum) {
                list.add(s);
            }
            lineNum++;
        }
        FileWriter fw = new FileWriter(filePath);
        for (String s : list) {
            fw.write(s + System.lineSeparator());
        }
        fw.close();
    }

    private static void updateTaskInFile(String filePath, int taskNum) throws IOException {
        File file =  new File(filePath);
        Scanner scn = new Scanner(file);
        List<String> list = new ArrayList<String>();
        int lineNum = 1;
        while (scn.hasNext()) {
            String s = scn.nextLine();
            if (lineNum == taskNum) {
                s = s.substring(0, 4) + "1" + s.substring(5);
            }
            list.add(s);
            lineNum++;
        }
        FileWriter fw = new FileWriter(filePath);
        for (String s : list) {
            fw.write(s + System.lineSeparator());
        }
        fw.close();
    }

}
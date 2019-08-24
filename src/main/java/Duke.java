import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static boolean isNum(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm\n" + logo);
        System.out.println("What can I do for you?");

        Scanner s = new Scanner(System.in);
        File file = new File("../../../data/duke.txt");
        ArrayList<Task> list = new ArrayList<>();
        Scanner sFile;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            file.createNewFile();
            sFile = new Scanner(file);
            while (sFile.hasNextLine()) {
                String ln = sFile.nextLine();
                String[] lnSplit = ln.split(",");
                if (lnSplit[0].equals("T")) {
                    list.add(new Todo(lnSplit[2]));
                } else if (lnSplit[0].equals("E")) {
                    String[] fromTo = lnSplit[2].split(" to ");
                    list.add(new Event(lnSplit[1], LocalDateTime.parse(fromTo[0], formatter), LocalDateTime.parse(fromTo[1], formatter)));
                } else if (lnSplit[0].equals("D")) {
                    list.add(new Deadline(lnSplit[2], LocalDateTime.parse(lnSplit[3], formatter)));
                }
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String echo = s.nextLine();
        while (true) {
            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (echo.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }
            } else if (echo.startsWith("done")) {
                String[] echoSplit = echo.split(" ");
                if (echoSplit.length < 2) {
                    try {
                        throw new DukeException("No task number done");
                    } catch (DukeException e) {
                        System.out.println("\u2639 OOPS!!! Please specify a task number to mark as done.");
                    }
                } else if (!isNum(echoSplit[1])) {
                    try {
                        throw new DukeException("Second word is not number done");
                    } catch (DukeException e) {
                        System.out.println("\u2639 OOPS!!! Please enter a task number after done.");
                    }
                } else {
                    int item = Integer.parseInt(echoSplit[1]) - 1;
                    if (item >= list.size()) {
                        try {
                            throw new DukeException("Task does not exist");
                        } catch (DukeException e) {
                            System.out.println("\u2639 OOPS!!! The task number does not exist.");
                        }
                    } else {
                        list.get(item).setAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(item));
                    }
                }
            } else if (echo.startsWith("delete")) {
                String[] echoSplit = echo.split(" ");
                if (echoSplit.length < 2) {
                    try {
                        throw new DukeException("No task number delete");
                    } catch (DukeException e) {
                        System.out.println("\u2639 OOPS!!! Please specify a task number to delete.");
                    }
                } else if (!isNum(echoSplit[1])) {
                    try {
                        throw new DukeException("Second word is not number delete");
                    } catch (DukeException e) {
                        System.out.println("\u2639 OOPS!!! Please enter a task number after delete.");
                    }
                } else {
                    int item = Integer.parseInt(echoSplit[1]) - 1;
                    if (item >= list.size()) {
                        try {
                            throw new DukeException("Task does not exist");
                        } catch (DukeException e) {
                            System.out.println("\u2639 OOPS!!! The task number does not exist.");
                        }
                    } else {
                        Task deleted = list.remove(item);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deleted);
                    }
                }
            } else {
                if (echo.startsWith("todo")) {
                    if (echo.length() <= 5) {
                        try {
                            throw new DukeException("todo desc empty");
                        } catch (DukeException e) {
                            System.out.println("\u2639 OOPS!!! The description of a todo cannot be empty.");
                            echo = s.nextLine();
                            continue;
                        }
                    } else {
                        list.add(new Todo(echo.substring(5)));
                    }
                } else if (echo.startsWith("deadline")) {
                    if (echo.length() <= 9) {
                        try {
                            throw new DukeException("deadline desc empty");
                        } catch (DukeException e) {
                            System.out.println("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                            echo = s.nextLine();
                            continue;
                        }
                    } else {
                        echo = echo.substring(9);
                        String[] echoSplit = echo.split(" /by ");
                        if (echoSplit.length < 2) {
                            try {
                                throw new DukeException("deadline by empty");
                            } catch (DukeException e) {
                                System.out.println("\u2639 OOPS!!! The by date of a deadline cannot be empty.");
                                echo = s.nextLine();
                                continue;
                            }
                        } else {
                            list.add(new Deadline(echoSplit[0], LocalDateTime.parse(echoSplit[1], formatter)));
                        }
                    }
                } else if (echo.startsWith("event")) {
                    if (echo.length() <= 6) {
                        try {
                            throw new DukeException("event desc empty");
                        } catch (DukeException e) {
                            System.out.println("\u2639 OOPS!!! The description of an event cannot be empty.");
                            echo = s.nextLine();
                            continue;
                        }
                    } else {
                        echo = echo.substring(6);
                        String[] echoSplit = echo.split(" /at ");
                        if (echoSplit.length < 2) {
                            try {
                                throw new DukeException("event at empty");
                            } catch (DukeException e) {
                                System.out.println("\u2639 OOPS!!! The at date/time of an event cannot be empty.");
                                echo = s.nextLine();
                                continue;
                            }
                        } else {
                            String[] fromTo = echoSplit[1].split(" to ");
                            list.add(new Event(echoSplit[0], LocalDateTime.parse(fromTo[0], formatter), LocalDateTime.parse(fromTo[1], formatter)));
                        }
                    }
                } else {
                    try {
                        throw new DukeException("invalid input");
                    } catch (DukeException e) {
                        System.out.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    echo = s.nextLine();
                    continue;
                }
                System.out.println("Got it. I've added this task: ");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            echo = s.nextLine();
        }
        try {
            FileWriter fw = new FileWriter(file);
            for (Task t : list) {
                fw.write(t.toFile() + "\n");
            }
            fw.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}

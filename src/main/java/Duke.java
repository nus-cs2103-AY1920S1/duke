import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    public static void main(String[] args) {
        File f = new File("src/main/java/data/duke.txt");
        try {
            FileWriter fw = new FileWriter("src/main/java/data/duke.txt", true);
            Scanner scanner = new Scanner(System.in);
            Scanner sc = new Scanner(f);
            ArrayList<Task> commands = new ArrayList<Task>();
            System.out.println("Hello! I'm Duke\nWhat can I do for you?\nHere are the current tasks in your list:");
            int counter = 1;
            while(sc.hasNextLine()) {
                String[] data = sc.nextLine().split("/");
                if (data[0].equals("T")) {
                    Todo t = new Todo(data[2]);
                    if (data[1].equals("1")) {
                        t.markAsDone();
                    }
                    commands.add(t);
                    System.out.println(counter + ". " + t);
                    counter++;
                } else if (data[0].equals("D")) {
                    Deadline d = new Deadline(data[2], new DateTime(data[3]));
                    if (data[1].equals("1")) {
                        d.markAsDone();
                    }
                    commands.add(d);
                    System.out.println(counter + ". " + d);
                    counter++;
                } else if (data[0].equals("E")){
                    Event e = new Event(data[2], new DateTime(data[3]));
                    if (data[1].equals("1")) {
                        e.markAsDone();
                    }
                    commands.add(e);
                    System.out.println(counter + ". " + e);
                    counter++;
                }
            }
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] inputArr = input.split(" ");
                String command = inputArr[0];
                try {
                    if (command.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        scanner.close();
                        fw.close();
                        break;
                    } else if (command.equals("list")) {
                        System.out.println("Here are the tasks in your list:");
                        int count = 1;
                        for (Task s : commands) {
                            System.out.println(count + ". " + s);
                            count++;
                        }
                    } else if (command.equals("done")) {
                        try {
                            int index = Integer.parseInt(inputArr[1]) - 1;
                            try {
                                Task doneTask = commands.get(index);
                                doneTask.markAsDone();
                                System.out.println("Nice! I've marked this task as done:\n  " + doneTask);
                                int i = 1;
                                index++;
                                FileReader fr = new FileReader("src/main/java/data/duke.txt");
                                BufferedReader br = new BufferedReader(fr);
                                FileWriter ff = new FileWriter("src/main/java/data/temp.txt", true);
                                String x;
                                while ((x = br.readLine()) != null) {
                                    if (i == index) {
                                        ff.write(doneTask.storageString() + System.lineSeparator());
                                    } else {
                                        ff.write(x + System.lineSeparator());
                                    }
                                    i++;
                                }
                                ff.close();
                                Files.copy(Paths.get("src/main/java/data/temp.txt"), Paths.get("src/main/java/data/duke.txt"), StandardCopyOption.REPLACE_EXISTING);
                                Files.delete(Paths.get("src/main/java/data/temp.txt"));

                            } catch (IndexOutOfBoundsException e) {
                                throw new DukeException("OOPS!!! Index for done does not exist in the list.");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Index for done cannot be empty.");
                        }
                    } else if (command.equals("deadline")) {
                        try {
                            String dL = input.split(" ", 2)[1];
                            try {
                                String[] taskDeadLine = dL.split(" /by ");
                                String taskD = taskDeadLine[0];
                                String by = taskDeadLine[1];
                                Task tt = new Deadline(taskD, new DateTime(by));
                                commands.add(tt);
                                System.out.println("Got it. I've added this task:\n  " + tt
                                        + "\nNow you have " + commands.size() + " tasks in the list.");
                                fw.append(tt.storageString() + System.lineSeparator());
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("OOPS!!! The format for deadline is wrong. Please follow: <description> /by <time>");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The description of deadline cannot be empty.");
                        }
                    } else if (command.equals("event")) {
                        try {
                            String eEvent = input.split(" ", 2)[1];
                            try {
                                String[] taskEvent = eEvent.split(" /at ");
                                String taskE = taskEvent[0];
                                String at = taskEvent[1];
                                Task ee = new Event(taskE, new DateTime(at));
                                commands.add(ee);
                                System.out.println("Got it. I've added this task:\n  " + ee
                                        + "\nNow you have " + commands.size() + " tasks in the list.");
                                fw.append(ee.storageString() + System.lineSeparator());
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("OOPS!!! The format for event is wrong. Please follow: <description> /at <time>");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The description of event cannot be empty.");
                        }
                    } else if (command.equals("todo")) {
                        try {
                            String todoT = input.split(" ", 2)[1];
                            Task t = new Todo(todoT);
                            commands.add(t);
                            System.out.println("Got it. I've added this task:\n  " + t
                                    + "\nNow you have " + commands.size() + " tasks in the list.");
                            fw.append(t.storageString() + System.lineSeparator());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                    } else if (command.equals("delete")) {
                        try {
                            int i = Integer.parseInt(inputArr[1]) - 1;
                            try {
                                Task tt = commands.remove(i);
                                System.out.println("Noted. I've removed this task:\n  " + tt +
                                        "\nNow you have " + commands.size() + " tasks in the list.");
                                FileReader fr = new FileReader("src/main/java/data/duke.txt");
                                BufferedReader br = new BufferedReader(fr);
                                FileWriter ff = new FileWriter("src/main/java/data/temp.txt", true);
                                int j = 1;
                                i++;
                                String x;
                                while ((x = br.readLine()) != null) {
                                    if (i != j) {
                                        ff.write(x + System.lineSeparator());
                                        ff.flush();
                                    }
                                    j++;
                                }
                                ff.close();
                                Files.copy(Paths.get("src/main/java/data/temp.txt"), Paths.get("src/main/java/data/duke.txt"), StandardCopyOption.REPLACE_EXISTING);
                                Files.delete(Paths.get("src/main/java/data/temp.txt"));
                            } catch (IndexOutOfBoundsException e) {
                                throw new DukeException("OOPS!!! Index for delete does not exist in the list.");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Index for delete cannot be empty.");
                        }
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}

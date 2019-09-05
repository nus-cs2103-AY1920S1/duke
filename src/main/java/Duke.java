import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {

    private static ArrayList<Task> repeatList = new ArrayList();

    public static void main(String[] args) throws DukeException, IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        File f = new File("." + File.separator + "list.txt");
        if (f.exists()) {
            loadList();
        }

        if (repeatList.size() != 0) {
            System.out.println("Loaded " + repeatList.size() + " items.");
        }

        boolean byeHit = false;
        String repeatStr = "";

        while (!byeHit) {
            repeatStr = sc.nextLine();
            if (repeatStr.equals("bye")) {
                byeHit = true;
                saveList();
                System.out.println("Bye. Hope to see you again soon!");
            } else if (repeatStr.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= repeatList.size(); i++) {
                    System.out.println(i + ". " + repeatList.get(i - 1));
                }
            } else if (repeatStr.contains("done")) {
                try {
                    int target = parseInt(repeatStr.replaceAll("\\D+", "")) - 1;
                    repeatList.get(target).done();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("  " + repeatList.get(target));
                } catch (NumberFormatException ex) {
                    System.out.println("☹ OOPS!!! Invalid Information.");
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("☹ OOPS!!! Invalid Information.");
                }
            } else if (repeatStr.contains("delete")) {
                try {
                    int target = parseInt(repeatStr.replaceAll("\\D+", "")) - 1;
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("  " + repeatList.get(target));
                    repeatList.remove(target);
                    int size = repeatList.size();
                    System.out.println("You now have " + size + (size == 1 ? " task" : " tasks") + " in the list.");
                } catch (NumberFormatException ex) {
                    System.out.println("☹ OOPS!!! Invalid Information.");
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("☹ OOPS!!! Invalid Information.");
                }
            } else {
                try {
                    String addTextDescription = "";
                    String addTextPeriod = "";
                    if (repeatStr.contains("todo")) {
                        addTextDescription = repeatStr.substring(repeatStr.lastIndexOf("todo ") + 5);
                        if (addTextDescription.length() == 0) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            repeatList.add(new Todo(addTextDescription));
                        }
                    } else if (repeatStr.contains("deadline")) {
                        if (repeatStr.lastIndexOf("/by") == -1)
                            throw new DukeException("☹ OOPS!!! You are missing information.");
                        addTextDescription = repeatStr.substring(9, repeatStr.lastIndexOf("/by")).trim();
                        addTextPeriod = repeatStr.substring(repeatStr.lastIndexOf("/by ") + 3).trim();
                        if (addTextDescription.length() == 0 || addTextPeriod.length() == 0) {
                            throw new DukeException("☹ OOPS!!! You are missing information.");
                        } else {
                            repeatList.add(new Deadline(addTextDescription, addTextPeriod));
                        }
                    } else if (repeatStr.contains("event")) {
                        if (repeatStr.lastIndexOf("/at") == -1)
                            throw new DukeException("☹ OOPS!!! You are missing information.");
                        addTextDescription = repeatStr.substring(6, repeatStr.lastIndexOf("/at")).trim();
                        addTextPeriod = repeatStr.substring(repeatStr.lastIndexOf("/at ") + 3).trim();
                        if (addTextDescription.length() == 0 || addTextPeriod.length() == 0) {
                            throw new DukeException("☹ OOPS!!! You are missing information.");
                        } else {
                            repeatList.add(new Event(addTextDescription, addTextPeriod));
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Got it. I've added this task: ");
                    int size = repeatList.size();
                    System.out.println("  " + repeatList.get(size - 1));
                    System.out.println("You now have " + size + (size == 1 ? " task" : " tasks") + " in the list.");
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    private static void saveList() throws IOException {
        FileWriter fw = new FileWriter("." + File.separator + "list.txt");
        for (Task t : repeatList) {
            fw.write(t.printSave() + System.lineSeparator());
        }
        fw.close();
    }

    private static void loadList() throws FileNotFoundException, DukeException {
        File f = new File("." + File.separator + "list.txt");
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] lineSplit = line.split(" | ");
            try {
                switch (line.charAt(0)) {
                    case 'T':
                        repeatList.add(new Todo(lineSplit[4]));
                        break;
                    case 'D':
                        repeatList.add(new Deadline(lineSplit[4], lineSplit[6]));
                        break;
                    case 'E':
                        repeatList.add(new Event(lineSplit[4], lineSplit[6]));
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! Invalid data loaded.");
                }
                if (lineSplit[2].equals("1")) {
                    repeatList.get(repeatList.size() - 1).done();
                }
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("☹ OOPS!!! Invalid data loaded.");
            }
        }
    }
}

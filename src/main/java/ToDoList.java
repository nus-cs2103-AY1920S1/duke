import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToDoList {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static ArrayList<Task> fileInitialization(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        ArrayList<Task> clone = new ArrayList<Task>();

        while (s.hasNext()) {
            String input = s.nextLine();
            String[] inputArr = input.split(" \\| ");
            boolean done;
            if (inputArr[1].equals("1")) {
                done = true;
            } else {
                done = false;
            }

            try {
                switch (inputArr[0]) {
                case "T":
                    clone.add(new ToDos(inputArr[2], done));
                    break;
                case "D":
                    clone.add(new Deadlines(inputArr[2], new DateTime(inputArr[3]), done));
                    break;
                case "E":
                    clone.add(new Events(inputArr[2], new DateTime(inputArr[3]), done));
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e);
            }

        }

        return clone;
    }

    private void addToDo(String whatToAdd, ArrayList<Task> whereToAdd, boolean isDone) {
        String message;
        message = whatToAdd.substring(whatToAdd.indexOf(' ') + 1);
        whereToAdd.add(new ToDos(message, isDone));
    }

    private void addEvent(String whatToAdd, ArrayList<Task> whereToAdd, boolean isDone) throws DukeException, NumberFormatException {
        String message;
        DateTime date;
        date = new DateTime(whatToAdd.substring(whatToAdd.indexOf("/") + 4));
        message = whatToAdd.substring(whatToAdd.indexOf(' ') + 1, whatToAdd.indexOf("/") - 1);
        whereToAdd.add(new Events(message, date, isDone));
    }

    private void addDeadline(String whatToAdd, ArrayList<Task> whereToAdd, boolean isDone) throws DukeException, NumberFormatException {
        String message;
        DateTime date;
        date = new DateTime(whatToAdd.substring(whatToAdd.indexOf("/") + 4));
        message = whatToAdd.substring(whatToAdd.indexOf(' ') + 1, whatToAdd.indexOf("/") - 1);
        whereToAdd.add(new Deadlines(message, date, isDone));
    }

    private void arrayToFile(File f, ArrayList<Task> arr) throws IOException {
        String memo = "";

        for (Task i : arr) {
            int done;
            if (i.isDone()) {
                done = 1;
            } else {
                done = 0;
            }

            if (i instanceof ToDos) {
                memo = memo + "T | " + done + " | " + i.getDescription() + "\n";
            } else if (i instanceof Deadlines) {
                memo = memo + "D | " + done + " | " + i.getDescription() + " | " + ((Deadlines) i).getDate() + "\n";
            } else {
                memo = memo + "E | " + done + " | " + i.getDescription() + " | " + ((Events) i).getDate() + "\n";
            }
        }

        writeToFile(f.getPath(), memo);
    }


    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();

        File f = new File("./todoList.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        ArrayList<Task> arr = fileInitialization(f);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                ui.printList(arr);
            } else {
                String[] temp = input.split(" ");
                if (temp.length == 0) {
                    ui.printMessage("Please input something :(");
                } else if (temp[0].equals("done")) {
                    try {
                        int index = Integer.parseInt(temp[1]) - 1;
                        ui.printDone(arr, index);
                        arrayToFile(f, arr);
                    } catch (NullPointerException e) {
                        ui.printError("Please input a valid task number.");
                    }

                } else if (temp[0].equals("delete")) { //command to delete task
                    try {
                        Task toRemove = arr.remove(Integer.parseInt(temp[1]) - 1);
                        ui.printRemove(arr, toRemove);
                        arrayToFile(f, arr);

                    } catch (NullPointerException e) {
                        ui.printError("Please input a valid task number to delete.");
                    } catch (IndexOutOfBoundsException e) {
                        ui.printError("Please input a valid task number to delete.");
                    }
                } else { //command to add task to list

                    DateTime date;
                    String message;
                    boolean added = false;

                    try {
                        switch (temp[0]) {
                        case "deadline":
                            addDeadline(input, arr, false);
                            added = true;
                            arrayToFile(f, arr);
                            break;
                        case "event":
                            addEvent(input, arr, false);
                            added = true;
                            arrayToFile(f, arr);
                            break;
                        case "todo":
                            if (temp.length < 2) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            addToDo(input, arr, false);
                            added = true;
                            arrayToFile(f, arr);
                            break;
                        }

                        if (added) {
                            ui.printAdd(arr);
                        } else {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }

                    } catch (DukeException e) {
                        ui.printError(e.getMessage());
                    } catch (StringIndexOutOfBoundsException e) {
                        ui.printError("Please input a task and date.");
                    } catch (IndexOutOfBoundsException e) {
                        ui.printError("Please input a valid date and time.");
                    } catch (NumberFormatException e) {
                        ui.printError("Please input a valid date and time.");
                    }

                }
            }

            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            ui.printBye();
        }
    }
}

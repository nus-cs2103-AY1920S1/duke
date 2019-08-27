import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class ToDoList {

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

    public void run() throws IOException {

        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        Storage storage = new Storage("./todoList.txt");

        ArrayList<Task> arr = storage.fileInitialization();

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
                        storage.arrayToFile(arr);
                    } catch (NullPointerException e) {
                        ui.printError("Please input a valid task number.");
                    }

                } else if (temp[0].equals("delete")) { //command to delete task
                    try {
                        Task toRemove = arr.remove(Integer.parseInt(temp[1]) - 1);
                        ui.printRemove(arr, toRemove);
                        storage.arrayToFile(arr);

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
                            storage.arrayToFile(arr);
                            break;
                        case "event":
                            addEvent(input, arr, false);
                            added = true;
                            storage.arrayToFile(arr);
                            break;
                        case "todo":
                            if (temp.length < 2) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            addToDo(input, arr, false);
                            added = true;
                            storage.arrayToFile(arr);
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

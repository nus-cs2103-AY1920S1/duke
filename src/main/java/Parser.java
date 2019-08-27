import java.io.IOException;

public class Parser {

    public static void parse(String input, Ui ui, TaskList task, Storage storage) throws IOException {

        if (input.equals("list")) {
            ui.printList(task.getList());
        } else {
            String[] temp = input.split(" ");
            if (temp.length == 0) {
                ui.printMessage("Please input something :(");
            } else if (temp[0].equals("done")) {
                try {
                    int index = Integer.parseInt(temp[1]) - 1;
                    ui.printDone(task.getList(), index);
                    storage.arrayToFile(task.getList());
                } catch (NullPointerException e) {
                    ui.printError("Please input a valid task number.");
                }

            } else if (temp[0].equals("delete")) { //command to delete task
                try {
                    Task toRemove = task.getList().remove(Integer.parseInt(temp[1]) - 1);
                    ui.printRemove(task.getList(), toRemove);
                    storage.arrayToFile(task.getList());

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
                        task.addDeadline(input, false);
                        added = true;
                        storage.arrayToFile(task.getList());
                        break;
                    case "event":
                        task.addEvent(input, false);
                        added = true;
                        storage.arrayToFile(task.getList());
                        break;
                    case "todo":
                        if (temp.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        task.addToDo(input, false);
                        added = true;
                        storage.arrayToFile(task.getList());
                        break;
                    }

                    if (added) {
                        ui.printAdd(task.getList());
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

    }
}

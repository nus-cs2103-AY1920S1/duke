import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {

        ui = new Ui();
        storage = new Storage(filePath);

        try {
            ui.showWelcomeScreen();
            taskList = new TaskList(storage.load());
        } catch(Exception e) {
            ui.printErrorMessage(e);
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            try {
                String[] inputs = Parser.breakDownString(sc.nextLine(), " ");
                String name = "";
                String[] time;

                if (inputs[0].equals("bye")) {
                    storage.save(taskList);

                    sc.close();

                    ui.showGoodByeScreen();

                    return;

                } else if (inputs[0].equals("list")) {
                    System.out.println(taskList.toString());
                } else if (inputs[0].equals("done")) {
                    if (inputs.length < 2) {
                        throw new DukeException("The task Number cannot be empty.");
                    }

                    int index = Integer.parseInt(inputs[1]) - 1;
                    if (index >= taskList.getSize() || index < 0) {
                        throw new DukeException("Invalid task number!");
                    }

                    taskList.replace(index, taskList.getAtIndex(index).changeToCompletedStatus());

                    ui.printDoneAcknowledgement(taskList.getAtIndex(index));

                    storage.save(taskList);

                } else if (inputs[0].equals("todo")) {
                    if (inputs.length < 2) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }

                    for (int i = 1; i < inputs.length; i++) {
                        name = name + inputs[i] + " ";
                    }
                    name = name.substring(0, name.length() - 1);
                    Todo newTask = new Todo(name);
                    taskList.add(newTask);

                    ui.printAddedAcknowledgement(newTask, taskList.getSize());

                    storage.save(taskList);
                } else if (inputs[0].equals("deadline") || inputs[0].equals("event")) {
                    String inputType = inputs[0];

                    if (inputs.length < 2) {
                        if (inputType.equals("deadline")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                    }

                    inputs = Parser.breakDownIntoNameAndTime(inputs);

                    if (inputs[1].equals("")) {
                        if (inputType.equals("deadline")) {
                            throw new DukeException("The end time of a deadline cannot be empty.");
                        } else {
                            throw new DukeException("The time of an event cannot be empty.");
                        }
                    }

                    name = inputs[0].substring(0, inputs[0].length() - 1);

                    LocalDateTime dateAndTime = Parser.getDateAndTimeFromString(inputs[1].substring(0, inputs[1].length() - 1));

                    Task newTask;

                    if (inputType.equals("deadline")) {
                        newTask = new Deadline(name, dateAndTime);
                    } else {
                        newTask = new Event(name, dateAndTime);
                    }
                    taskList.add(newTask);

                    ui.printAddedAcknowledgement(newTask, taskList.getSize());

                    storage.save(taskList);
                } else if (inputs[0].equals("delete")) {
                    if (inputs.length < 2) {
                        throw new DukeException("The task Number cannot be empty.");
                    }

                    int index = Integer.parseInt(inputs[1]) - 1;

                    Task deletedTask = taskList.deleteAt(index);

                    ui.printRemovedAcknowledgement(deletedTask, taskList.getSize());

                    storage.save(taskList);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                ui.printErrorMessage(e);
            }
        }
    }
    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "\\data\\Duke.txt").run();
    }
}
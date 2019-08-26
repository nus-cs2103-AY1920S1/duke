import java.util.Scanner;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private ArrayList<Task> listOfInputs;
    private Storage storage;
    private Ui ui;

    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.listOfInputs = new ArrayList<>(100);
    }

    private void todoCheck(String[] tasks) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private void deadlineCheck(String[] tasks, String userInput) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!userInput.contains("/by")) {
            throw new DukeException("OOPS!!! Deadline must include /by (date to complete task).");
        } else if (userInput.substring(userInput.indexOf("/by") + 3).equals("")
                || userInput.substring(userInput.indexOf("/by") + 4).equals("")) {
            throw new DukeException("OOPS!!! Please include the date to complete task after /by command.");
        }
    }

    private void eventCheck(String[] tasks, String userInput) throws DukeException {
        if (tasks.length <= 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        } else if (!userInput.contains("/at")) {
            throw new DukeException("OOPS!!! Event must include /at (time of event).");
        } else if (userInput.substring(userInput.indexOf("/at") + 3).equals("")
                || userInput.substring(userInput.indexOf("/at") + 4).equals("")) {
            throw new DukeException("OOPS!!! Please include the time of event after /at.");
        }
    }

    private void run() {
        try {
            this.listOfInputs = this.storage.loadFromFile();
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
        ui.showWelcome();
        while (true) {
            String userInput = ui.userInput();
            if (userInput.equals("bye")) {
                ui.printBye();
                break;
            } else if (userInput.equals("list")) {
                ui.printTaskList(this.listOfInputs);
            } else {
                String[] task = userInput.split(" ");
                String instruction = task[0];
                if (instruction.equals("done")) {
                    int taskNumber = Integer.parseInt(task[1]);
                    listOfInputs.get(taskNumber - 1).markedAsDone();
                    ui.printDone(listOfInputs.get(taskNumber - 1).toString());
                } else if (instruction.equals("delete")) {
                    int taskNumber = Integer.parseInt(task[1]);
                    ui.printRemoveMessage(this.listOfInputs.remove(taskNumber - 1).toString(),
                            this.listOfInputs.size());
                } else {
                    try {
                        switch (instruction) {
                        case "todo": {
                            try {
                                todoCheck(task);
                                Task todo = new Todo(userInput.substring(5));
                                listOfInputs.add(todo);
                                ui.printAddTaskMessage(todo.toString(), this.listOfInputs.size());
                            } catch (DukeException ex) {
                                System.out.println(ex.getMessage());
                            } finally {
                                break;
                            }
                        }
                        case "deadline": {
                            try {
                                deadlineCheck(task, userInput);
                                Task deadline = new Deadline(userInput.substring(9, userInput.indexOf("/by")),
                                        dateFormatter(userInput.substring(userInput.indexOf("/by") + 4)));
                                listOfInputs.add(deadline);
                                ui.printAddTaskMessage(deadline.toString(), this.listOfInputs.size());
                            } catch (DukeException ex) {
                                System.out.println(ex.getMessage());
                            } finally {
                                break;
                            }
                        }
                        case "event": {
                            try {
                                eventCheck(task, userInput);
                                Task event = new Event(userInput.substring(6, userInput.indexOf("/at")),
                                        dateFormatter(userInput.substring(userInput.indexOf("/at") + 4)));
                                listOfInputs.add(event);
                                ui.printAddTaskMessage(event.toString(), this.listOfInputs.size());
                            } catch (DukeException ex) {
                                System.out.println(ex.getMessage());
                            } finally {
                                break;
                            }
                        }
                        default: {
                            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        }
                    } catch (DukeException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
        try {
            this.storage.saveToFile(this.listOfInputs);
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    private Date dateFormatter(String date) throws DukeException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date parseDate = formatter.parse(date);
            return parseDate;
        } catch (ParseException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

}



import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Duke {
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            taskList = new TaskList();
        } catch (FileNotFoundException f) {
            System.out.println(f);
        } catch (ParseException p) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        new Duke("/Users/michelleyong/Desktop/duke/data/duke.txt").run();
    }

    public void run() throws IOException, ParseException {
        ui.printHello();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc);

        while (sc.hasNext()) {
            String text = parser.nextCommand();
            String[] commandArr = parser.breakDownCommand(text);
            String command = parser.getCommand(commandArr);
            if (command.equals("todo")) {
                try {
                    if (text.length() <= 4) {
                        throw new DukeException();
                    }
                    Todo todo = parser.getTodo(text);
                    taskList.addTask(todo);
                    ui.printTaskAdded(todo, taskList.getSize());
                    storage.appendTaskToFile(todo);
                } catch (DukeException e) {
                    ui.printTodoError();
                }
            } else if (command.equals("deadline")) {
                try {
                    if (text.length() <= 8) {
                        throw new DukeException();
                    }
                    String[] descArr = parser.breakDownDescription(text);
                    Date date = parser.getDate(descArr, storage);
                    Deadline deadline = new Deadline(parser.getDeadlineDesc(descArr), date);
                    taskList.addTask(deadline);
                    ui.printTaskAdded(deadline, taskList.getSize());
                    storage.appendTaskToFile(deadline);
                } catch (DukeException e) {
                    ui.printDeadlineError();
                }
            } else if (command.equals("event")) {
                try {
                    if (text.length() <= 5) {
                        throw new DukeException();
                    }
                    String[] descArr = parser.breakDownDescription(text);
                    Date date = parser.getDate(descArr, storage);
                    Event event = new Event(parser.getEventDesc(descArr), date);
                    taskList.addTask(event);
                    ui.printTaskAdded(event, taskList.getSize());
                    storage.appendTaskToFile(event);
                } catch (DukeException e) {
                    ui.printEventError();
                }
            } else if (command.equals("list")) {
                taskList.printList();
            } else if (command.equals("done")) {
                try {
                    if (text.length() <= 4) {
                        throw new DukeException();
                    }
                    int num = parser.getTaskNum(commandArr);
                    if (num >= taskList.getSize()) {
                        throw new DukeException();
                    }
                    Task task = taskList.markTaskAsDone(num);
                    ui.printTaskDone(task);
                    storage.updateTaskInFile(taskList.getList());
                } catch (DukeException e) {
                    ui.printNoSuchTaskError();
                }
            } else if (command.equals("bye")) {
                ui.printBye();
                break;
            } else if (command.equals("delete")) {
                try {
                    if (text.length() <= 6) {
                        throw new DukeException();
                    }
                    int num = parser.getTaskNum(commandArr);
                    if (num >= taskList.getSize()) {
                        throw new DukeException();
                    }
                    Task removed = taskList.removeTask(num);
                    ui.printTaskRemoved(removed, taskList.getSize());
                    storage.updateTaskInFile(taskList.getList());
                } catch (DukeException e) {
                    ui.printNoSuchTaskError();
                }
            } else {
                try {
                    throw new DukeException();
                } catch (DukeException e) {
                    ui.printUnknownCommandError();
                }
            }
        }
        sc.close();
    }
}
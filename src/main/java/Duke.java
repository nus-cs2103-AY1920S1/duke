import java.util.Scanner;
import java.io.IOException;

/**
 * Represents an application that that manages <code>ToDo</code>s, <code>Event</code>s, and <code>Deadline</code>s.
 * A Duke object can add <code>Task</code>s, delete them, mark them as Done, and maintain a history of Tasks entered
 * during earlier execution.
 */
public class Duke {
    private Ui UI;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs a Duke object that saves Tasks in the provided filePath.
     * @param filePath the path of the text file which is a directory for the Tasks.
     */
    public Duke(String filePath) {
        UI = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException de) {
            UI.showLoadingError();
            tasks = new TaskList();
        } catch (DukeException de) {
            UI.showException(de);
        }
    }

    /**
     * Integrates <code>Parser</code>, <code>TaskList</code>, <code>Storage</code>, and <code>Ui</code> to
     * deal with User's commands and accordingly manage database.
     */
    public void run(){
        Scanner sc = new Scanner(System.in);
        UI.showWelcome();
        while(true) {
            String command = sc.nextLine();
            UI.printLine();
            try {
                Parser commandAnalyzer = new Parser(command);
                if (commandAnalyzer.isValid()) {
                    if (commandAnalyzer.getType().equals("done")) {
                        int index = Integer.parseInt(commandAnalyzer.getList().get(0)) - 1;
                        try {
                            if (index >= tasks.size() || index < 0) {
                                throw new DukeException(" :( OOPS!!! Requested task number is not available");
                            }
                            Task temp = tasks.get(index);
                            temp.markAsDone();
                            UI.showTaskDone(temp);
                            storage.update(tasks);
                        } catch (DukeException | IOException de) {
                            UI.showException(de);
                        } catch (NumberFormatException nfe) {
                            UI.showNumberFormatError("done");
                        }
                    } else if (command.equals("bye")) {
                        UI.showGoodBye();
                        break;
                    } else if (command.equals("list")) {
                        UI.showTasks(tasks);
                    } else {
                        if (commandAnalyzer.getType().equals("todo")) {
                            try {
                                Task temp = new ToDo(commandAnalyzer);
                                tasks.add(temp);
                                UI.showTaskCreated(temp, tasks.size()); //change arr to TaskList
                                storage.update(tasks);
                            } catch (IOException de) {
                                UI.showException(de);
                            }
                        } else if (commandAnalyzer.getType().equals("deadline")) {
                            try {
                                Task temp = new Deadline(commandAnalyzer);
                                tasks.add(temp);
                                UI.showTaskCreated(temp, tasks.size());
                                storage.update(tasks);
                            } catch (IOException de) {
                                UI.showException(de);
                            }
                        } else if (commandAnalyzer.getType().equals("event")) {
                            try {
                                Task temp = new Event(commandAnalyzer);
                                tasks.add(temp);
                                UI.showTaskCreated(temp, tasks.size());
                                storage.update(tasks);
                            } catch (IOException de) {
                                UI.showException(de);
                            }
                        } else if (commandAnalyzer.getType().equals("delete")) {
                            try {
                                int index = Integer.parseInt(commandAnalyzer.getList().get(0)) - 1;
                                if (index >= tasks.size() || index < 0) {
                                    throw new DukeException(" :( OOPS!!! Task to be deleted is not available");
                                } else {
                                    Task temp = tasks.remove(index);
                                    UI.showTaskDeleted(temp, tasks.size());
                                }
                                storage.update(tasks);
                            } catch (DukeException | IOException de) {
                                UI.showException(de);
                            } catch (NumberFormatException nfe) {
                                UI.showNumberFormatError("delete");
                            }
                        }
                    }
                } else {
                    try {
                        throw new DukeException(" :( OOPS!!! I'm sorry but I don't know what that means :-(");
                    } catch (DukeException de) {
                        UI.showException(de);
                    }
                }
            } catch (DukeException de) {
                UI.showException(de);
            } finally {
                UI.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:/Users/mtg-1/OneDrive/Documents/NUS/Y2S1/CS2103/repos/dukerepo/src/main/java/history.txt").run();
    }
}

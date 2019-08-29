import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * <h1>Duke</h1>
 * The Duke program allows users to manage and organise their to-do, deadlines, and events
 * all in one application. Users can add their tasks, marking them as done, list the tasks
 * they have, and even delete them after they are done.
 */
public class Duke { // handles all input and output
    private DukeData _myData;
    private Ui _ui;
    private TaskList _tasks;

    public Duke() {
        this._ui = new Ui();
        this._myData = new DukeData();
        this._tasks = new TaskList();
    }

    /**
     * This main method handles all input and outputs which the Duke program uses.
     * @param args Unused.
     */
    public static void main(String[] args) { // handles all input and output
        Duke theDuke = new Duke();
        Scanner sc = new Scanner(System.in);
        System.out.println(_ui.showIntro());

        while (sc.hasNext()) {
            String cmdWord = sc.next(); // extract first only
            String cmdLine = sc.nextLine(); // extract the rest of the line

            try {
                switch (cmdWord) {
                case "list":
                    System.out.println(_ui.showList());
                    break;
                case "data":
                    _myData.printDataFile();
                    break;
                case "bye":
                    _myData.exit();
                    System.out.println(_ui.showFarewell());
                    break;
                case "done":
                    int taskNo = Integer.parseInt(cmdLine.substring(1));
                    String doned = done(taskNo);
                    _myData.taskDone(taskNo, _task.get(taskNo - 1));
                    System.out.println(doned);
                    break;
                case "delete":
                    int taskNum = Integer.parseInt(cmdLine.substring(1));
                    _myData.removeTask(taskNum);
                    System.out.println(delete(taskNum));
                    break;
                case "todo":
                    if (cmdLine.isEmpty()) {
                        throw new ToDoException(cmdLine);
                    } else {
                        ToDo td = new ToDo(cmdLine);
                        int index = _task.size() + 1;
                        _myData.addTask(index, td); // since index is -1 of size
                        System.out.println(newTask(td));
                    }
                    break;
                case "deadline":
                    if (cmdLine.isEmpty()) {
                        throw new DeadlineException(cmdLine, 3);
                    } else if (!cmdLine.contains("/by")) {
                        throw new DeadlineException(cmdLine, 1);
                    } else {
                        String[] cmdSplit = cmdLine.split("/by ");
                        if (cmdSplit.length <= 1) {
                            throw new DeadlineException(cmdLine, 3);
                        } else if (cmdSplit[0].equals(" ")) {
                            throw new DeadlineException(cmdLine, 2);
                        } else {
                            Deadline dl = new Deadline(cmdSplit[0], cmdSplit[1]);
                            int index = _task.size() + 1;
                            _myData.addTask(index, dl); // since index is -1 of size
                            System.out.println(newTask(dl));
                        }
                    }
                    break;
                case "event":
                    if (cmdLine.isEmpty()) {
                        throw new EventException(cmdLine, 3);
                    } else if (!cmdLine.contains("/at")) {
                        throw new EventException(cmdLine, 1);
                    } else {
                        String[] cmdSplitt = cmdLine.split("/at ");
                        if (cmdSplitt.length <= 1) {
                            throw new EventException(cmdLine, 3);
                        } else if (cmdSplitt[0].equals(" ")) {
                            throw new EventException(cmdLine, 2);
                        } else {
                            Event ev = new Event(cmdSplitt[0], cmdSplitt[1]);
                            int index = _task.size() + 1;
                            _myData.addTask(index, ev); // since index is -1 of size
                            System.out.println(newTask(ev));
                        }
                    }
                    break;
                default: // if it is not any of the above commands
                    throw new DukeException(cmdWord + cmdLine);
                }
            } catch (DukeException e) {
                System.err.println(Ui.addLines(e.getMessage()));
            } catch (ToDoException e) {
                System.err.println(Ui.addLines(e.getMessage()));
            } catch (DeadlineException e) {
                System.err.println(Ui.addLines(e.getMessage()));
            } catch (EventException e) {
                System.err.println(Ui.addLines(e.getMessage()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}

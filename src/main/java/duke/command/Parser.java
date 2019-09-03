package duke.command;

import duke.Duke;
import duke.data.DukeData;
import duke.data.TaskList;
import duke.task.*;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private Ui _ui;
    private Duke _duke;
    private DukeData _data;
    private final Scanner sc;

    public Parser(Duke parseDuke) {
        this._duke = parseDuke;
        this._data = parseDuke.getData();
        this._ui = new Ui();
        this.sc = new Scanner(System.in);
        System.out.println(_ui.showIntro());
    }

    public void parse() {
        TaskList tasks = this._data.load();
        while (sc.hasNext()) {
            String cmdWord = sc.next(); // extract first only
            String cmdLine = sc.nextLine(); // extract the rest of the line

            try {
                switch (cmdWord) {
                    case "list":
                        System.out.println(_ui.showList(this._duke.getTaskList()));
                        break;
                    case "data":
                        this._ui.showData(this._data);
                        break;
                    case "bye":
                        this._data.exit();
                        System.out.println(_ui.showFarewell());
                        break;
                    case "done":
                        int taskNo = Integer.parseInt(cmdLine.substring(1));
                        this._data.taskDone(taskNo - 1);
                        String doned = this._ui.showDone(taskNo, tasks);
                        System.out.println(doned);
                        break;
                    case "delete":
                        int taskNum = Integer.parseInt(cmdLine.substring(1));
                        String deleted = this._ui.showDelete(taskNum - 1, tasks);
                        this._data.removeTask(taskNum - 1);
                        System.out.println(deleted);
                        break;
                    case "todo":
                        if (cmdLine.isEmpty()) {
                            throw new ToDoException(cmdLine);
                        } else {
                            ToDo td = new ToDo(cmdLine);
                            int index = tasks.getSize() + 1;
                            this._data.addTask(index, td); // since index is -1 of size
                            System.out.println(this._ui.showTaskAdded(td, tasks));
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
                                int index = tasks.getSize() + 1;
                                this._data.addTask(index, dl); // since index is -1 of size
                                System.out.println(this._ui.showTaskAdded(dl, tasks));
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
                                int index = tasks.getSize() + 1;
                                this._data.addTask(index, ev); // since index is -1 of size
                                System.out.println(this._ui.showTaskAdded(ev, tasks));
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
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }
}

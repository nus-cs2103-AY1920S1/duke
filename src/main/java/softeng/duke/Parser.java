package softeng.duke;

import softeng.tasks.TaskList;
import softeng.dukeExceptions.DukeException;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Represents a parser of user commands in Duke chat bot.
 */
public class Parser {
    //protected Scanner sc;

    public Parser() {

    }

    /**
     * Reads and react to user command
     * @param tasks Current task list.
     */
    public String parse(TaskList tasks, String input, Storage storage, Statistics stats) {
        String cmd = input;
        Scanner cmdSc = new Scanner(cmd);
        if (!cmdSc.hasNext()) {
            return "You didn't enter anything!";
        }
        try {
            assert cmdSc.hasNext() : "You didn't enter anything!";
        } catch (AssertionError err) {
            return err.getMessage();
        }
        if (cmd.toLowerCase().equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else {
            String cmdWord = cmdSc.next().toLowerCase();
            switch (cmdWord) {
                case "todo":
                    try {
                        if (cmdSc.hasNext()) {
                            String addToDo = tasks.addToDo(cmdSc.nextLine());
                            storage.save(tasks);
                            return addToDo;
                        } else {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                    } catch (DukeException exp) {
                        return "OOPS!!! " + exp.getMessage();
                    }
                    //break;
                case "find":
                    try {
                        assert cmdSc.hasNext() : "OOPS!!! The keyword is empty!";
                        return tasks.find(cmdSc.nextLine());
                    } catch (AssertionError err) {
                        return err.getMessage();
                    }
                    //break;
                //list
                case "list":
                    return tasks.list();
                    //break;

                case "listdone":
                    return stats.listDone();
                //deadline
                case "deadline":
                    try {
                        if (cmdSc.hasNext()) {
                            String tskBy = cmdSc.nextLine();
                            assert tskBy.contains("/by") : "You didn't enter the due date properly! User /by";
                            String addDeadline = tasks.addDeadline(tskBy);
                            storage.save(tasks);
                            return addDeadline;
                        } else {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                    } catch (DukeException exp) {
                        return "OOPS!!! " + exp.getMessage();
                    } catch (AssertionError err) {
                        return err.getMessage();
                    }
                //event
                case "event":
                    try {
                        if (cmdSc.hasNext()) {
                            String tskAt = cmdSc.nextLine();
                            assert tskAt.contains("/at") : "You didn't enter the date properly! use /at";
                            String addEvent = tasks.addEvent(tskAt);
                            storage.save(tasks);
                            return addEvent;
                        } else {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                    } catch (DukeException exp) {
                        return "OOPS!!! " + exp.getMessage();
                    } catch (AssertionError err) {
                        return err.getMessage();
                    }
                //done
                case "done":
                    try {
                        String done = tasks.done(Integer.parseInt(cmdSc.next()) - 1, stats);
                        storage.save(tasks);
                        stats.save();
                        return done;
                    } catch (NoSuchElementException exp) {
                        return "You didn't enter what task you have done!";
                    }
                //delete
                case "delete":
                    try {
                        String delete = tasks.delete(Integer.parseInt(cmdSc.next()) - 1);
                        storage.save(tasks);
                        return delete;
                    } catch (NoSuchElementException exp) {
                        return "You didn't enter what task you want to delete!";
                    }

                default:
                    try {
                        throw new DukeException("I'm sorry, but I don't know what " + input + " means :-(");
                    } catch (DukeException exp) {
                        return "OOPS!!!" + exp.getMessage();
                    }
            }
        }

    }
}

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
    public String parse(TaskList tasks, String input, Storage storage) {
        String cmd = input;
        Scanner cmdSc = new Scanner(cmd);
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
                    return tasks.find(cmdSc.nextLine());
                    //break;
                //list
                case "list":
                    return tasks.list();
                    //break;
                //deadline
                case "deadline":
                    try {
                        if (cmdSc.hasNext()) {
                            String tskBy = cmdSc.nextLine();
                            String addDeadline = tasks.addDeadline(tskBy);
                            storage.save(tasks);
                            return addDeadline;
                        } else {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                    } catch (DukeException exp) {
                        return "OOPS!!! " + exp.getMessage();
                    }
                //event
                case "event":
                    try {
                        if (cmdSc.hasNext()) {
                            String tskAt = cmdSc.nextLine();
                            String addEvent = tasks.addEvent(tskAt);
                            storage.save(tasks);
                            return addEvent;
                        } else {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                    } catch (DukeException exp) {
                        return "OOPS!!! " + exp.getMessage();
                    }
                //done
                case "done":
                    String done = tasks.done(Integer.parseInt(cmdSc.next()) - 1);
                    storage.save(tasks);
                    return done;
                //delete
                case "delete":
                    String delete = tasks.delete(Integer.parseInt(cmdSc.next()) - 1);
                    storage.save(tasks);
                    return delete;

                default:
                    try {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException exp) {
                        return "OOPS!!!" + exp.getMessage();
                    }
            }
        }

    }
}

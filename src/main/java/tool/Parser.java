package tool;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;


public class Parser {
    protected TaskList commands;
    protected Storage storage;
    protected Ui ui;


    public Parser(TaskList commands, Storage storage, Ui ui) {
        this.commands = commands;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses the user's input to make sense of all the commands, and hands it over
     * to tool.TaskList
     * @param command
     */
    public String parse(String command) {
            assert command.isEmpty() : "Input cannot be empty";
            String[] inputArr = command.split(" ");
            String userCommand = inputArr[0];
            String dukeText;
            try {
                if (userCommand.equals("bye")) {
                    this.storage.close(this.commands);
                    dukeText = this.ui.bye();
                } else if (userCommand.equals("list")) {
                    dukeText = this.ui.list(this.commands);
                } else if (userCommand.equals("done")) {
                    dukeText = parseDone(inputArr);
                } else if (userCommand.equals("deadline")) {
                    dukeText = parseTask(command, "d");
                } else if (userCommand.equals("event")) {
                    dukeText = parseTask(command, "e");
                } else if (userCommand.equals("todo")) {
                    dukeText = parseTask(command, "t");
                } else if (userCommand.equals("delete")) {
                    dukeText = parseDelete(inputArr);
                } else if (userCommand.equals("find")) {
                    dukeText = parseFind(inputArr);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                dukeText = e.getMessage();
            }

            return dukeText;

    }

    private String parseTask(String command, String type) throws DukeException {
        try {
            String taskString = command.split(" ", 2)[1];
            String delimiter = "";
            Task tt = null;
            if (type.equals("e")) {
                delimiter = " /at ";
            } else if (type.equals("d")) {
                delimiter = " /by ";
            } else {
                tt = new Todo(taskString);
            }
            try {
                String[] taskArr = taskString.split(delimiter);
                String des = taskArr[0];
                String dateTime = taskArr[1];
                if (type.equals("e")) {
                    tt = new Event(des, new DateTime(dateTime));
                } else if (type.equals('d')) {
                    tt = new Deadline(des, new DateTime(dateTime));
                }
                this.commands.add(tt);
                storage.save(tt);
                return this.ui.addTask(tt, this.commands.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                String messageError = type.equals("d") ? "OOPS!!! The format for deadline is wrong. Please follow: <description> /by <time>"
                                                       : type.equals("e") ? "OOPS!!! The format for event is wrong. Please follow: <description> /at <time>"
                                                                          : "OOPS!!! The description of a todo cannot be empty.";
                throw new DukeException(messageError);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("OOPS!! Description for the task cannot be empty.");
        }
    }

    private String parseDelete(String[] inputArr) throws DukeException {
        try {
            int i = Integer.parseInt(inputArr[1]) - 1;
            assert i <= commands.size() : "Index for delete is out of bounds";
            try {
                Task tt = this.commands.delete(i);
                storage.delete(i + 1);
                return this.ui.delete(tt, this.commands.size());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Index for delete does not exist in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Index for delete cannot be empty.");
        }
    }

    private String parseFind(String[] inputArr) {
        assert inputArr.length < 2 : "Missing word to find";
        String word = inputArr[1];
        return this.ui.find() + "\n" + this.commands.find(word);
    }

    private String parseDone(String[] inputArr) throws DukeException {
        try {
            int index = Integer.parseInt(inputArr[1]) - 1;
            try {
                Task doneTask = this.commands.done(index);
                this.storage.done(doneTask, index + 1);
                return this.ui.done(doneTask);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Index for done does not exist in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Index for done cannot be empty.");
        }

    }
}

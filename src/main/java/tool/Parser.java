package tool;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;


public class Parser {
    private TaskList commands;
    private Storage storage;
    private Ui ui;


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
                switch (userCommand) {
                    case "bye":
                        dukeText = this.ui.bye();
                        break;
                    case "list":
                        dukeText = this.ui.list(this.commands);
                        break;
                    case "done":
                        dukeText = parseDone(inputArr);
                        break;
                    case "deadline":
                        dukeText = parseTask(command, "d");
                        break;
                    case "event":
                        dukeText = parseTask(command, "e");
                        break;
                    case "todo":
                        dukeText = parseTask(command, "t");
                        break;
                    case "delete":
                        dukeText = parseDelete(inputArr);
                        break;
                    case "find":
                        dukeText = parseFind(inputArr);
                        break;
                    case "edit":
                        dukeText = parseEdit(command);
                        break;
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                dukeText = e.getMessage();
            }

            return dukeText;

    }

    /**
     * Parses the task command
     * @param command
     * @param type
     * @return String output for duke
     * @throws DukeException
     */
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
                } else if (type.equals("d")) {
                    tt = new Deadline(des, new DateTime(dateTime));
                }
                this.commands.add(tt);
                storage.save(this.commands);
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

    /**
     * Parses the delete command
     * @param inputArr
     * @return String output for duke
     * @throws DukeException
     */
    private String parseDelete(String[] inputArr) throws DukeException {
        try {
            int i = Integer.parseInt(inputArr[1]) - 1;
            assert i <= commands.size() : "Index for delete is out of bounds";
            try {
                Task tt = this.commands.delete(i);
                storage.save(this.commands);
                return this.ui.delete(tt, this.commands.size());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Index for delete does not exist in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Index for delete cannot be empty.");
        }
    }

    /**
     * Parses find command
     * @param inputArr
     * @return String output for duke
     */
    private String parseFind(String[] inputArr) {
        assert inputArr.length < 2 : "Missing word to find";
        String word = inputArr[1];
        return this.ui.find() + "\n" + this.commands.find(word);
    }

    /**
     * Parses done command
     * @param inputArr
     * @return String output for duke
     * @throws DukeException
     */
    private String parseDone(String[] inputArr) throws DukeException {
        try {
            int index = Integer.parseInt(inputArr[1]) - 1;
            try {
                Task doneTask = this.commands.done(index);
                this.storage.save(this.commands);
                return this.ui.done(doneTask);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Index for done does not exist in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Index for done cannot be empty.");
        }

    }

    /**
     * Parses edit command
     * @param command
     * @return String output for duke
     * @throws DukeException
     */
    private String parseEdit(String command) throws DukeException {
        String input = command.split(" ", 2)[1];
        try {
            String[] splitInput = input.split("/", 3);
            int i = Integer.parseInt(splitInput[0]);
            Task toEdit = this.commands.getTask(i - 1);
            String oldTask = toEdit.toString();
            String attribute = splitInput[1];
            String newDes = splitInput[2];
            System.out.println("newDes: " + newDes);
            toEdit.edit(attribute, newDes);
            this.storage.save(this.commands);
            return this.ui.edit(oldTask, toEdit);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Format for edit command is wrong. Try: edit index/attribute/updated info instead.");
        }
    }
}

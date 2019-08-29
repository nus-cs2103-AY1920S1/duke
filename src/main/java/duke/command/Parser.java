package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.text.ParseException;
import java.util.ArrayList;

public class Parser {

    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void processLine(String userInput) throws DukeException, ParseException {
        ArrayList<Task> list = tasks.list;
        String[] words = userInput.split(" ");
        String firstWord = words[0];
        if (firstWord.equals("list")) {
            ui.printList(list);

        } else if (firstWord.equals("done")) {
            if (words.length != 2) {
                throw new DukeException("You need to specify a task that is done.");
            }
            if (Integer.parseInt(words[1]) > list.size() || Integer.parseInt(words[1]) <= 0) {
                throw new DukeException("Task does not exist.");
            }
            Task task = list.get(Integer.parseInt(words[1]) - 1);
            task.setDone(true);
            ui.printTaskDone(task);

        } else if (firstWord.equals("delete")) {
            if (words.length != 2) {
                throw new DukeException("You need to specify a task that is done.");
            }
            if (Integer.parseInt(words[1]) > list.size() || Integer.parseInt(words[1]) <= 0) {
                throw new DukeException("Task does not exist.");
            }
            int index = Integer.parseInt(words[1]) - 1;
            Task removed = tasks.delete(index);
            ui.printDeleteTask(removed, list);

        } else if (firstWord.equals("find")) {
            String secondWord = words[1];
            ArrayList<Task> listFound = new ArrayList<>();
            tasks.list.stream()
                    .filter(task -> task.getDescription().contains(secondWord))
                    .forEach(listFound::add);
            ui.printList(listFound);
        } else {
            Task task;
            if (firstWord.equals("todo")) {
                if (words.length < 2 || words[1].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new ToDo(userInput.split(" ", 2)[1]);
            } else if (firstWord.equals("deadline")) {
                if (words.length < 2 || words[1].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String description = userInput.split(" ", 2)[1].split(" /", 2)[0];
                String by = userInput.split(" ", 2)[1].split(" /by ", 2)[1];
                task = new Deadline(description, by);
            } else if (firstWord.equals("event")) {
                if (words.length < 2 || words[1].equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String description = userInput.split(" ", 2)[1].split(" /", 2)[0];
                String at = userInput.split(" ", 2)[1].split(" /at ", 2)[1];
                task = new Event(description, at);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            tasks.add(task);
            ui.printAddTask(task, list);
        }
    }
}

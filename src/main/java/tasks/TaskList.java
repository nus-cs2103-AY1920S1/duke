package tasks;

import exception.DukeException;
import parser.Parser;
import ui.Ui;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;
    private Ui ui = new Ui();
    private Parser parser = new Parser();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int getSize() {
        return this.list.size();
    }

    public String printList() {
        StringBuilder sb = new StringBuilder();
        int size = this.list.size();
        for (int i = 0; i < size; i++) {
            sb.append((i + 1) + "." + list.get(i) + "\n");
        }
        return sb.toString();
    }

    public String findListEntry(String action) {
        StringBuilder sb = new StringBuilder();
        int size = this.list.size();
        int index = 1;
        for (int i = 0; i < size; i++) {
            if (list.get(i).toString().contains(action)) {
                sb.append((index) + "." + (list.get(i) + "\n"));
                index += 1;
            }
        }
        if (index == 1) { // Index did not increment, no match found
            return ui.noMatch();
        }
        return sb.toString();
    }

    public String findReminders() {
        StringBuilder sb = new StringBuilder();
        int size = this.list.size();
        sb.append("Here are your upcoming deadlines:\n");
        for (int i = 0; i < size; i++) {
            String entry = list.get(i).toString();
            if (entry.contains("(") && entry.contains(")")) {
                String[] substrings = entry.split(":");
                String deadline = substrings[1].replace(")", "");
                sb.append(deadline.trim() + "\n");
            }
        }
        return sb.toString();
    }

    public String setDone(String input) {
        // Assumption: fixed format - remove first 4 characters to get index. i.e. "done"
        String value = input.substring(4);
        int size = this.list.size();
        // Get integer found in user input
        try {
            int index = Integer.parseInt(value.trim()); // Remove any blank space
            System.out.println(index + size);
            list.get(index - 1).setDone();
            return (ui.completedTask() + "\n" + (list.get(index - 1)));
        } catch (Exception e) {
            return ui.invalidEntry();
        }

    }

    public String deleteTask(String input) {
        // Assumption: fixed format - remove first 6 characters to get index. i.e. "delete"
        String value = input.substring(6);

        // Get integer found in user input
        try {
            int index = Integer.parseInt(value.trim()); // Remove any blank space
            Task delete = list.get(index - 1);
            list.remove(index - 1); //index start from 0
            int n = this.getSize();
            return (ui.deleteTask() + "\n" + delete + "\n" + ui.numTasks(n));
        } catch (Exception e) {
            return ui.invalidEntry();
        }
    }

    public String addTask(String input, String action, String description) throws DukeException {

        switch (action) {

        case "todo":
            this.list.add(new ToDo(description));
            break;

        case "deadline":
            assert input.contains("/by") : "Does not contain valid date/time of deadline";
            this.list.add(new Deadline(description, parser.parseDateTime(action, input)));
            break;

        case "event":
            assert input.contains("/at") : "Does not contain valid date/time of event";
            this.list.add(new Event(description, parser.parseDateTime(action, input)));
            break;

        default:
        }

        int n = this.getSize();
        return (this.list.get(n - 1) + "\n" + (ui.numTasks(n)));
    }
}


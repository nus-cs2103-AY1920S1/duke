package duke.core;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

public class Parser {
    private static String subString(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(words[i] + " ");
        }
        return sb.toString().trim();
    }

    private static int findIdx(String[] words, String s) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    private static Task parseTask(String[] words) {
        if (words[0].equals("todo")) {
            return new ToDo(subString(words, 1, words.length));
        } else if (words[0].equals("deadline")) {
            int i = findIdx(words, "/by");
            String description = subString(words, 1, i);
            String by = subString(words, i + 1, words.length);
            return new Deadline(description, by);
        } else {
            int i = findIdx(words, "/at");
            String description = subString(words, 1, i);
            String at = subString(words, i + 1, words.length);
            return new Event(description, at);
        }
    }

    private static void checkIllegalInstruction(String[] words) throws DukeException {
        String fw = words[0];
        if (!(fw.equals("done") || fw.equals("todo") || fw.equals("deadline") || fw.equals("event")
                    || fw.equals("delete") || fw.equals("list") || fw.equals("bye"))) {
            throw new DukeException(" \u2639  OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if ((fw.equals("todo") || fw.equals("deadline") || fw.equals("event")) && words.length < 2) {
            throw new DukeException(" \u2639  OOPS!!! The description of a " + fw + " cannot be empty.");
        }
        if ((fw.equals("deadline") && findIdx(words, "/by") == -1) || 
                (fw.equals("event") && findIdx(words, "/at") == -1)) {
            throw new DukeException(" \u2639  OOPS!!! The time of a " + fw + " cannot be empty.");
        }
        if ((fw.equals("done") || fw.equals("delete")) && words.length < 2) {
            throw new DukeException(" \u2639  OOPS!!! The task number of a " + fw + " cannot be empty.");
        }
    }

    public static Command parse(String s) throws DukeException {
        String[] words = s.split(" ");
        checkIllegalInstruction(words);
        switch (words[0]) {
        case "bye":
            return new ExitCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(words[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(words[1]));
        case "list":
            return new ListCommand();
        default:
            Task t = parseTask(words);
            return new AddCommand(t);
        }
    }
}


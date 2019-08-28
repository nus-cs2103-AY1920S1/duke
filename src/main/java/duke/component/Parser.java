package duke.component;


import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Task;

import java.time.LocalDateTime;

public class Parser {
    public static LocalDateTime getDateAndTimeFromString(String inputs){
        String[] time = inputs.split(" ");
        String[] dateInString = time[0].split("\\/");

        LocalDateTime dateAndTime = LocalDateTime.of(Integer.parseInt(dateInString[2]),
                Integer.parseInt(dateInString[1]),
                Integer.parseInt(dateInString[0]),
                Integer.parseInt(time[1]) / 100,
                Integer.parseInt(time[1]) % 100);

        return dateAndTime;
    }

    public static String[] breakDownIntoNameAndTime(String[] inputs) {
        String[] result = {"", ""};

        int index = 1;

        while (index < inputs.length && !(inputs[index].charAt(0) == '/')) {
            result[0] = result[0] + inputs[index] + " ";
            index++;
        }

        //Skip the /at or /by
        index++;

        while (index < inputs.length) {
            result[1] = result[1] + inputs[index] + " ";
            index++;
        }
        return result;
    }

    public static String[] breakDownString(String input, String separator) {
        return input.split(separator);
    }

    public static Command retrieveCommandFromString(String input) throws DukeException {
        String[] inputs = Parser.breakDownString(input, " ");
        String name = "";
        String[] time;

        if (inputs[0].equals("bye")) {
            return new ExitCommand();
        } else if(inputs[0].equals("list")) {
            return new ViewListCommand();
        } else if(inputs[0].equals("done")) {
            if (inputs.length < 2) {
                throw new DukeException("The task Number cannot be empty.");
            }

            int index = Integer.parseInt(inputs[1]) - 1;

            return new DoneCommand(index);
        } else if(inputs[0].equals("todo")) {
            if (inputs.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            for (int i = 1; i < inputs.length; i++) {
                name = name + inputs[i] + " ";
            }
            name = name.substring(0, name.length() - 1);
            Todo newTask = new Todo(name);

            return new AddCommand(newTask);
        } else if(inputs[0].equals("deadline") || inputs[0].equals("event")) {
            String inputType = inputs[0];

            if (inputs.length < 2) {
                if (inputType.equals("deadline")) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else {
                    throw new DukeException("The description of an event cannot be empty.");
                }
            }

            inputs = Parser.breakDownIntoNameAndTime(inputs);

            if (inputs[1].equals("")) {
                if (inputType.equals("deadline")) {
                    throw new DukeException("The end time of a deadline cannot be empty.");
                } else {
                    throw new DukeException("The time of an event cannot be empty.");
                }
            }

            name = inputs[0].substring(0, inputs[0].length() - 1);

            LocalDateTime dateAndTime = Parser.getDateAndTimeFromString(inputs[1].substring(0, inputs[1].length() - 1));

            Task newTask;

            if (inputType.equals("deadline")) {
                newTask = new Deadline(name, dateAndTime);
            } else {
                newTask = new Event(name, dateAndTime);
            }

            return new AddCommand(newTask);
        } else if(inputs[0].equals("delete")) {
            if (inputs.length < 2) {
                throw new DukeException("The task Number cannot be empty.");
            }

            int index = Integer.parseInt(inputs[1]) - 1;

            return new DeleteCommand(index);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}

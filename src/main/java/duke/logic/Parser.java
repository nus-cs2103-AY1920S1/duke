package duke.logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TaskWithOrder;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;

import duke.trivia.QuestionList;
import duke.trivia.TriviaQuestion;

public class Parser {
    public Ui ui = new Ui();

    public Parser() {
    }

    /**
     * parses and excecutes the command user enters
     * 
     * @param command   entered command
     * @param list      task list
     * @param formatter datetime formatter
     * @return false if user wishes to terminate program, true otherwise
     * @throws DukeException due to checking validity of command
     */
    public String parse(String command, TaskList list, DateTimeFormatter formatter, String message,
            QuestionList qList) {
        if (qList.isAsking) {
            if (command.equals("give up")) {
                message = ui.getAnswer(qList);
            }
            else {
                message = ui.checkTriviaAnswer(qList, command);
            }
        } else {
            String verificationResult = Verify.checkCommandValidity(command, list, formatter, qList);
            if (!verificationResult.equals("ok")) {
                message = verificationResult;
                return message;
            }
            if (command.equals("bye")) {
                message = ui.exit();
                return message;
            } else if (command.startsWith("list")) {
                message = ui.list(list);
            } else if (command.startsWith("done")) {
                list.get(Integer.parseInt(command.split(" ")[1])).setAsDone();
                message = ui.done(list, Integer.parseInt(command.split(" ")[1]));
            } else if (command.startsWith("find")) {
                ArrayList<TaskWithOrder> matchingTasks = list.search(command.split(" ")[1]);
                message = ui.found(matchingTasks);
            } else if (command.startsWith("delete")) {
                Task t = list.remove(Integer.parseInt(command.split(" ")[1]));
                message = ui.delete(t);
            } else if (command.startsWith("todo")) {
                list.add(new Todo(command.substring(5), 0));
                message = ui.taskAdded(list);
            } else if (command.startsWith("event")) {
                String[] cmdSplit = command.substring(6).split(" /at ");
                list.add(new Event(cmdSplit[0], 0, LocalDateTime.parse(cmdSplit[1].split(" to ")[0], formatter),
                        LocalDateTime.parse(cmdSplit[1].split(" to ")[1], formatter)));
                message = ui.taskAdded(list);
            } else if (command.startsWith("deadline")) {
                list.add(new Deadline(command.substring(9).split(" /by ")[0], 0,
                        LocalDateTime.parse(command.substring(9).split(" /by ")[1], formatter)));
                message = ui.taskAdded(list);
            } else if (command.equals("trivia")) {
                qList.isAsking = true;
                message = ui.askQuestion(qList);
            } else if (command.startsWith("trivia new")) {
                String[] cmdSplit = command.substring(10).split(" / ");
                qList.questions.add(new TriviaQuestion(cmdSplit[0], cmdSplit[1]));
                message = ui.questionAdded(qList);
            } else if (command.equals("trivia list")){
                message = ui.showQuestions(qList);
            } else if (command.startsWith("trivia delete")){
                TriviaQuestion t = qList.questions.remove(Integer.parseInt(command.substring(14))-1);
                message = ui.questionDelete(t);
            }
        }
        return message;
    }
}
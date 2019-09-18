package seedu.duke.util;

import seedu.duke.tasks.Task;
import seedu.duke.trivia.QuestionAnswer;
import seedu.duke.trivia.Trivia;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String WELCOME_MESSAGE = "Hello from\n" + logo + "\n"
            +  "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String ERROR_CANNOT_LOAD = "OOPS!!! I cannot read your file! :(";
    private static final String BYE = "Bye. Hope to see you again soon!";
    private static final String TASK_WRAPPER_UPPER = "Got it. I've added this task:\n";
    private static final String DONE = "Nice! I've marked this task as done:\n";
    private static final String TASK_WRAPPER_UPPER_DELETE = "Got it. I've removed this task:\n";
    private static final String NOW_YOU_HAVE = "Now you have ";
    private static final String TASK_WRAPPER_LOWER = " tasks in the list.\n";
    private static final String FOUND = "Here are the matching tasks in your list:\n";
    private static final String NOT_FOUND = "OOPS!!! There weren't any matching tasks in your list!\n";
    private static final String TRIVIA_QUESTION_ADDED = "Ho, I see you have added a question:\n";
    private static final String ERROR_CANNOT_LOAD_TRIVIA = "It's time to start from a clean slate. THE WORLD!!!";
    private static final String TRIVIA_START = "Answer my questions, Joestar.";
    private static final String TRIVIA_ANSWER_ADDED = "Hm, so that is the answer to this question:\n";
    private static final String TRIVIA_EXIT = "No, this cannot be, I AM DIOOOOOOOO!!!";
    private static final String TRIVIA_DELETE_QUESTION = "What?? You are erasing this knowledge from my memory???\n";
    private static final String TRIVIA_DELETE_ANSWER = "What?? I'm forgetting an answer...\n";
    private static final String TRIVIA_FINISH = "Interesting match, I will make sure to beat you next time...";
    private static final String TRIVIA_CORRECT = "Ho, you got the correct answer, Jotaro\n";
    private static final String TRIVIA_CORRECT_FINAL = "Dammit, you got the final answer correct.";
    private static final String TRIVIA_WRONG = "HAH, you have slipped up yet again!\n";
    private static final String TRIVIA_WRONG_FINAL = "WRONG! What a way to end the trivia, Joestar.";
    private static final String TRIVIA_NEXT_QUESTION = "Next question:\n";
    private static final Object TRIVIA_START_QUIZ = "It's time to begin, Jotaro.\n";

    /**
     * Prints out a welcome message.
     */
    public String greet() {
        return WELCOME_MESSAGE;
    }

    /**
     * Prints out an error if file cannot be loaded.
     */
    public String cannotLoad() {
        return ERROR_CANNOT_LOAD;
    }

    /**
     * Displays the TaskList in order of addition.
     *
     * @param taskList TaskList to print out.
     */
    public String showTaskList(TaskList taskList) {
        assert taskList != null: "taskList cannot be null";
        return taskList.toString();
    }

    /**
     * Lets the user know that a task has been marked done.
     *
     * @param task Task to be marked done.
     */
    public String showDone(Task task) {
        return DONE + task + "\n";
    }

    /**
     * Displays the Task that was operated on, other than done.
     *
     * @param task Task that was operated on
     * @param taskList TaskList of the Task that was operated on.
     * @param isAdd Type of operation that was done.
     */
    public String operateTask(Task task, TaskList taskList, boolean isAdd) {
        if (isAdd) {
            return TASK_WRAPPER_UPPER + task + "\n" + NOW_YOU_HAVE + taskList.getTaskListSize()
                    + TASK_WRAPPER_LOWER;
        } else {
            return TASK_WRAPPER_UPPER_DELETE + task + "\n" + NOW_YOU_HAVE + taskList.getTaskListSize()
                    + TASK_WRAPPER_LOWER;
        }
    }

    /**
     * Displays the found items to the user.
     *
     * @param foundItems The result of finding the taskList.
     */
    public String showFound(TaskList foundItems) {
        assert foundItems != null: "foundItems has to be non-null";
        if (foundItems.getTaskListSize() == 0) {
            return NOT_FOUND;
        } else {
            return FOUND + foundItems;
        }
    }

    /**
     * Show error to the user.
     *
     * @param e Error to be shown.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * Prints bye message.
     */
    public String bye() {
        return BYE;
    }

    public String startTrivia() {
        return TRIVIA_START;
    }

    /**
     * Prints out and error if trivia cannot be loaded.
     */
    public String cannotLoadTrivia() {
        return ERROR_CANNOT_LOAD_TRIVIA;
    }

    /**
     * Displays the answer that was added to the trivia.
     *
     * @return Message informing user that answer has been added.
     */
    public String questionAdded(String question) {
        return TRIVIA_QUESTION_ADDED + question;
    }

    public String answerAdded(String answer, String question) {
        return answer + "\n" + TRIVIA_ANSWER_ADDED + question;
    }

    public String viewAllTrivia (Trivia trivia) {
        ArrayList<QuestionAnswer> questionBank = trivia.getQuestionBank();
        String temp = "So this is all you have so far:\n";
        for (QuestionAnswer qa: questionBank) {
            temp = temp + qa.getQuestion() + "\n\n" + qa.getAnswers() + "\n";
        }
        return temp;
    }

    public String exitTrivia() {
        return TRIVIA_EXIT;
    }

    public String deleteQuestion(String deletedQuestion) {
        return TRIVIA_DELETE_QUESTION + deletedQuestion;
    }

    public String deleteAnswer(String deletedAnswer) {
        return TRIVIA_DELETE_ANSWER + deletedAnswer;
    }

    public String showQuestion(Trivia trivia) {
        return trivia.showCurrentQuestion();
    }

    public String startQuiz(Trivia trivia) {
        return TRIVIA_START_QUIZ + showQuestion(trivia);
    }

    public String finishTrivia() {
        return TRIVIA_FINISH;
    }

    public String correctAnswer(Trivia trivia) {
        return TRIVIA_CORRECT + TRIVIA_NEXT_QUESTION + trivia.showCurrentQuestion();
    }

    public String correctAnswerFinal() {
        return TRIVIA_CORRECT_FINAL;
    }

    public String wrongAnswer(Trivia trivia) {
        return TRIVIA_WRONG + TRIVIA_NEXT_QUESTION + trivia.showCurrentQuestion();
    }

    public String wrongAnswerFinal() {
        return TRIVIA_WRONG_FINAL;
    }
}

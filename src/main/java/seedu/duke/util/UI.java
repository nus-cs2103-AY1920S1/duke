package seedu.duke.util;

import seedu.duke.tasks.Task;
import seedu.duke.trivia.QuestionAnswer;
import seedu.duke.trivia.Trivia;

import java.util.ArrayList;

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
     *
     * @return Welcome message.
     */
    public String greet() {
        return WELCOME_MESSAGE;
    }

    /**
     * Prints out an error if file cannot be loaded.
     *
     * @return Error message.
     */
    public String cannotLoad() {
        return ERROR_CANNOT_LOAD;
    }

    /**
     * Displays the TaskList in order of addition.
     *
     * @param taskList TaskList to print out.
     * @return Tasklist in string form.
     */
    public String showTaskList(TaskList taskList) {
        assert taskList != null: "taskList cannot be null";
        return taskList.toString();
    }

    /**
     * Lets the user know that a task has been marked done.
     *
     * @param task Task to be marked done.
     * @return Task that was marked done.
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
     * @return Task that was operated on in with wrappers.
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
     * @return All found items in TaskList format.
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
     * @return Error message.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * Prints bye message.
     *
     * @return Bye message.
     */
    public String bye() {
        return BYE;
    }

    /**
     * Informs user that trivia has started.
     *
     * @return Trivia start message.
     */
    public String startTrivia() {
        return TRIVIA_START;
    }

    /**
     * Prints out and error if trivia cannot be loaded.
     *
     * @return Error message.
     */
    public String cannotLoadTrivia() {
        return ERROR_CANNOT_LOAD_TRIVIA;
    }

    /**
     * Displays the question that was added to the trivia.
     *
     * @param question Question that was added to trivia.
     * @return Message informing user that question has been added.
     */
    public String questionAdded(String question) {
        return TRIVIA_QUESTION_ADDED + question;
    }

    /**
     * Displays the answer that was added to the trivia.
     *
     * @param answer Answer that was added to trivia.
     * @param question Question of answer that was added to trivia.
     * @return Message informing user that answer has been added.
     */
    public String answerAdded(String answer, String question) {
        return answer + "\n" + TRIVIA_ANSWER_ADDED + question;
    }

    /**
     * Views all the questions and answers in the trivia.
     *
     * @param trivia Trivia to view questions and answers.
     * @return String that lists all questions and answers.
     */
    public String viewAllTrivia (Trivia trivia) {
        ArrayList<QuestionAnswer> questionBank = trivia.getQuestionBank();
        String temp = "So this is all you have so far:\n";
        for (QuestionAnswer qa: questionBank) {
            temp = temp + qa.getQuestion() + "\n\n" + qa.getAnswers() + "\n";
        }
        return temp;
    }

    /**
     * Informs user that trivia has been exited.
     *
     * @return Exit message.
     */
    public String exitTrivia() {
        return TRIVIA_EXIT;
    }

    /**
     * Informs the user that question has been deleted.
     *
     * @param deletedQuestion Question that was deleted.
     * @return Deleted question with message.
     */
    public String deleteQuestion(String deletedQuestion) {
        return TRIVIA_DELETE_QUESTION + deletedQuestion;
    }

    /**
     * Informs the user that answer has been deleted.
     *
     * @param deletedAnswer Answer that was deleted.
     * @return Deleted answer with message.
     */
    public String deleteAnswer(String deletedAnswer) {
        return TRIVIA_DELETE_ANSWER + deletedAnswer;
    }

    /**
     * Shows current question in quiz to the user.
     *
     * @param trivia Trivia to show the question from.
     * @return Current question in quiz.
     */
    public String showQuestion(Trivia trivia) {
        return trivia.showCurrentQuestion();
    }

    /**
     * Informs the user that quiz has started as well as show the first question.
     *
     * @param trivia Trivia to show first question from.
     * @return Quiz start message.
     */
    public String startQuiz(Trivia trivia) {
        return TRIVIA_START_QUIZ + showQuestion(trivia);
    }

    /**
     * Informs the user that quiz has ended.
     *
     * @return Quiz end message.
     */
    public String finishTrivia() {
        return TRIVIA_FINISH;
    }

    /**
     * Tells the user that they got the correct answer and to show the next question.
     *
     * @param trivia Trivia to show the next question.
     * @return Correct answer message and next question.
     */
    public String correctAnswer(Trivia trivia) {
        return TRIVIA_CORRECT + TRIVIA_NEXT_QUESTION + trivia.showCurrentQuestion();
    }

    /**
     * Tells the user that they got the last question correct.
     *
     * @return Correct last answer message.
     */
    public String correctAnswerFinal() {
        return TRIVIA_CORRECT_FINAL;
    }

    /**
     * Tells the user that they got the wrong answer and to show the next question.
     *
     * @param trivia Trivia to show the next question.
     * @return Wrong answer message and next question.
     */
    public String wrongAnswer(Trivia trivia) {
        return TRIVIA_WRONG + TRIVIA_NEXT_QUESTION + trivia.showCurrentQuestion();
    }

    /**
     * Tells the user that they got the last question wrong.
     *
     * @return Wrong last answer message.
     */
    public String wrongAnswerFinal() {
        return TRIVIA_WRONG_FINAL;
    }
}

package seedu.duke.trivia;

import seedu.duke.exceptions.TriviaException;

import java.util.ArrayList;

/**
 * Class that contains all the information of the trivia, including questions and answers.
 */
public class Trivia {
    private int currentQuestionIndex = -1;
    private ArrayList<QuestionAnswer> questionBank;

    /**
     * Constructor that initialises the trivia.
     */
    public Trivia() {
        this.questionBank = new ArrayList<>();
    }

    /**
     * Views the question specified by the index.
     *
     * @param qnIndex Question index of question to view.
     * @return Question specified by index.
     */
    public String viewQuestion(int qnIndex) {
        return questionBank.get(qnIndex).getQuestion();
    }

    public ArrayList<QuestionAnswer> getQuestionBank() {
        return questionBank;
    }

    /**
     * Adds question to trivia.
     *
     * @param question Question to add to trivia.
     */
    public void addQuestion(String question) {
        QuestionAnswer toAdd = new QuestionAnswer(question);
        questionBank.add(toAdd);
    }

    /**
     * Adds answer to question in trivia.
     *
     * @param answer Answer to add.
     * @param questionIndex Index of question to add answer to.
     */
    public void addAnswer(String answer, int questionIndex) {
        questionBank.get(questionIndex).addAnswer(answer);
    }

    /**
     * Deletes question from trivia.
     *
     * @param questionIndex Index of question to delete.
     * @return The deleted question.
     */
    public String deleteQuestion(int questionIndex) {
        return questionBank.remove(questionIndex).getQuestion();
    }

    /**
     * Deletes answer from question in trivia.
     *
     * @param questionIndex Index of question to delete answer from.
     * @param answerIndex Index of answer to delete.
     * @return The deleted answer.
     */
    public String deleteAnswer(int questionIndex, int answerIndex) {
        return questionBank.get(questionIndex).deleteAnswer(answerIndex);
    }

    /**
     * Checks the answer supplied to the trivia to existing question.
     *
     * @param input Answer supplied by user.
     * @return True if answer exists in current questionanswer.
     * @throws TriviaException Throws if question does not have answers.
     */
    public boolean checkNextAnswer(String input) throws TriviaException {
        if (!questionBank.get(currentQuestionIndex).hasAnswers()) {
            throw new TriviaException("You haven't given me any answers for this stupid question!");
        } else {
            if (currentQuestionIndex < questionBank.size() - 1 && questionBank.size() > 1) {
                boolean temp = questionBank.get(currentQuestionIndex).checkAnswer(input);
                currentQuestionIndex++;
                return temp;
            } else if (questionBank.size() == 1) {
                boolean temp = questionBank.get(currentQuestionIndex).checkAnswer(input);
                return temp;
            } else {
                boolean temp = questionBank.get(questionBank.size() - 1).checkAnswer(input);
                resetQuestionIndex();
                return temp;
            }
        }
    }

    /**
     * Shows question at current time in the quiz.
     *
     * @return String representing current question.
     */
    public String showCurrentQuestion() {
        return questionBank.get(currentQuestionIndex).getQuestion();
    }

    /**
     * Starts the question counter for the quiz.
     */
    public void startQuestionIndex() {
        currentQuestionIndex = 0;
    }

    /**
     * Resets the question index to -1 for the next quiz.
     */
    public void resetQuestionIndex() {
        currentQuestionIndex = -1;
    }

    /**
     * Checks if the current question index is the same as the final index of the question bank.
     *
     * @return True if current question index is the final question.
     */
    public boolean isFinalQuestion() {
        return currentQuestionIndex == questionBank.size() - 1;
    }
}

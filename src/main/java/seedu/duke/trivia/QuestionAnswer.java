package seedu.duke.trivia;

import java.util.ArrayList;

/**
 * Class that provides the questions and answers in a combined format to allow for easier checking.
 */
public class QuestionAnswer {
    private String question;
    private ArrayList<String> possibleAnswers;

    /**
     * Constructor that takes in a string as a question.
     *
     * @param question Question for the questionanswer object.
     */
    public QuestionAnswer(String question) {
        this.question = question;
        possibleAnswers = new ArrayList<String>();
    }

    /**
     * Adds an answer to the question.
     *
     * @param answer Answer to be added to the question.
     */
    public void addAnswer(String answer) {
        possibleAnswers.add(answer);
    }

    /**
     * Deletes and answer to the question.
     *
     * @param index Index of answer to be deleted.
     * @return String that is the answer that was removed.
     */
    public String deleteAnswer(int index) {
        return possibleAnswers.remove(index);
    }

    public String getQuestion() {
        return question;
    }

    /**
     * Checks the answer against the existing answers.
     *
     * @param answer Answer to check against answer bank.
     * @return True if the answer supplied matches the answers in the answer bank.
     */
    public boolean checkAnswer(String answer) {
        for (String ans: possibleAnswers) {
            if (answer.equals(ans)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the question has answers.
     *
     * @return True if question has at least one answer.
     */
    boolean hasAnswers() {
        return possibleAnswers.size() > 0;
    }

    public String getAnswers() {
        String temp = "";
        for (int i = 0; i < possibleAnswers.size(); i++) {
            temp = temp + (i + 1) + ". " + possibleAnswers.get(i) + "\n";
        }
        return temp;
    }

}

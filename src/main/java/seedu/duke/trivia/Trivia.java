package seedu.duke.trivia;

import java.util.ArrayList;

public class Trivia {
    private int currentQuestionIndex = -1;
    private ArrayList<QuestionAnswer> questionBank;

    public Trivia() {
        this.questionBank = new ArrayList<>();
    }



    public String viewQuestion(int qnIndex) {
        return questionBank.get(qnIndex).getQuestion();
    }

    public ArrayList<QuestionAnswer> getQuestionBank() {
        return questionBank;
    }

    public void addQuestion(String question) {
        QuestionAnswer toAdd = new QuestionAnswer(question);
        questionBank.add(toAdd);
    }

    public void addAnswer(String answer, int questionIndex) {
        questionBank.get(questionIndex).addAnswer(answer);
    }

    public String deleteQuestion(int questionIndex) {
        return questionBank.remove(questionIndex).getQuestion();
    }

    public String deleteAnswer(int questionIndex, int answerIndex) {
        return questionBank.get(questionIndex).deleteAnswer(answerIndex);
    }

    public boolean checkNextAnswer(String input) {
        if (currentQuestionIndex < questionBank.size() - 1) {
            boolean temp = questionBank.get(currentQuestionIndex).checkAnswer(input);
            currentQuestionIndex++;
            return temp;
        } else {
            boolean temp = questionBank.get(questionBank.size() - 1).checkAnswer(input);
            resetQuestionIndex();
            return temp;
        }
    }

    public String showCurrentQuestion() {
        return questionBank.get(currentQuestionIndex).getQuestion();
    }

    public void startQuestionIndex() {
        currentQuestionIndex = 0;
    }

    public void resetQuestionIndex() {
        currentQuestionIndex = -1;
    }

    public boolean isFinalQuestion() {
        return currentQuestionIndex == questionBank.size() - 1;
    }
}
